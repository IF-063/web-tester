$(function() {

  // request's filters name array
  var filters = ['applicationFilter', 'serviceFilter', 'labelFilter'];
  
  // sets filter values after filter processing
  for (i in filters) {
	  setFilters(filters[i], getURLParameter(filters[i]));
  }
  
  // sets values of request's filters field
  function setFilters(select, values) {
	var values = getURLParameter(select);
	$('#' + select + ' option').each(function () {
	  if ($.inArray($(this).val(), values) != -1) {
		$(this).prop('selected', true);
	  };
	}); 
  }
  
  // sets value of requestNameFilter field
  $('#requestNameFilter').val(getURLParameter('requestNameFilter')[0]);

  // gets value of filter processing from URL
  function getURLParameter(sParam) {
	var sPageURL = decodeURIComponent(window.location.search.substring(1));
	var sURLVariables = sPageURL.split('&');
	var arr = [];
	for (var i = 0; i < sURLVariables.length; i++) {
	  var sParameterName = sURLVariables[i].split('=');
	  if (sParameterName[0] == sParam) {
		arr.push(sParameterName[1]);
	  }
	}
	return arr;
  }

  // resets all filters
  $('#resetFilters').click(function(){
	window.location.href=url.split('?')[0];
  });

  // enables tag autocomplete in filtering fields
  $('#applicationFilter, #serviceFilter, #labelFilter').select2({
	theme: 'bootstrap',
    width: '100%'
  });
 
  // selects all request on page
  $('#requests #selectAll').click(function() {
    $('#requests input[type="checkbox"][name="operateSelect"]').prop('checked', this.checked);
  });
  
  var requestsToSend = [];

  // performs request run
  $(document).on('click', '.run', function() {
    // $('#requestsToSend').prop('value', [$(this).prop('id')]);
	  requestsToSend = [$(this).prop('id')];
    startTest();
    return false;
  });

  // 
  $(document).on('click', '#runSelected', function() {;
    requestsToSend = [];
    $('#requests input:checked[name="operateSelect"]').each(function() {
    	requestsToSend.push($(this).prop('id'));
    });
    if (requestsToSend.length != 0) {
      startTest();
    }
    return false;
  });

  // performs run of all requests on page
  $(document).on('click', '#runAll', function() {
    requestsToSend = [];
    $('#requests input:checkbox:not(:checked)[name="disableSelect"]').each(function() {
      requestsToSend.push($(this).prop('id'));
    });
    if (requestsToSend.length != 0) {
      startTest();
    }
  });

  // shows modal window with environments
  function startTest() {
    $('#environmentModal').modal('show');
  }

  // confirm request run
  $('#confirmEnvironmentModal').click(function(e) {
    var envId = $('#environment').val();
    sendTestData(envId);
    return false;
  });

  // sends test data to the server
  function sendTestData(envId) {
    $.ajax({
      type: 'POST',
      url: contextPath + '/tests/requests/run',
      data: {
        environmentId: envId,
        requestIdArray: requestsToSend
      },
      success: function(data, textStatus, jqXHR) {
    	window.location.replace(contextPath + '/results/requests/run/'+data);
      },
      error: function(jqXHR) {
        alert(0);
      },
    });
  }

  // handles remove request button click
  $(document).on('click', '.removeInstance', function() {
    if (confirm('Do you really want to delete the request?')) {
      deleteRequests([$(this).prop('id')]);
    }
    return false;
  });

  // handles remove selected requests button click
  $(document).on('click', '#deleteSelected', function() {
    var selected = [];
    $('#requests input:checked[name="operateSelect"]').each(function() {
      selected.push($(this).prop('id'));
    });
    if (selected.length != 0 && confirm('Do you really want to delete the requests?')) {
      deleteRequests(selected);
    }
    return false;
  });

  // sends requests to delete to the server
  function deleteRequests(input) {
    $.ajax({
      type: 'DELETE',
      url: contextPath + '/tests/requests',
      contentType: 'application/json',
      data: JSON.stringify(input),
      success: function(data, textStatus, jqXHR) {
        for (var i = 0; i < input.length; i++) {
          $('#requests input[type="checkbox"][id=' + input[i] + ']').parents('tr').remove();
        }
        alert('Request deleted (code: ' + jqXHR.status + ')');
      },
      error: function(jqXHR) {
        alert('Error (code: ' + jqXHR.status + ')');
      },
    });
  };

});