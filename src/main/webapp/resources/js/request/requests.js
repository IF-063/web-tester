$(function() {

  var filters = ['applicationFilter', 'serviceFilter', 'labelFilter'];
  
  for (i in filters) {
	  setFilters(filters[i], getURLParameter(filters[i]));
  }
  
  function setFilters(select, values) {
	var values = getURLParameter(select);
	$('#' + select + ' option').each(function () {
	  if ($.inArray($(this).val(), values) != -1) {
		$(this).prop('selected', true);
	  };
	}); 
  }
  
  $('#requestNameFilter').val(getURLParameter('requestNameFilter')[0]);

  function getURLParameter(sParam) {
	var sPageURL = window.location.search.substring(1);
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
	
  $('#resetFilters').click(function(){
	window.location.href=url.split('?')[0];
  });

  $('#applicationFilter, #serviceFilter, #labelFilter').select2({
	theme: 'bootstrap'
  });
 
  $('#requests #selectAll').click(function() {
    $('#requests input[type="checkbox"][name="operateSelect"]').prop('checked', this.checked);
  });

  $(document).on('click', '.run', function() {
    $('#requestsToSend').prop('value', [$(this).prop('id')]);
    startTest();
    return false;
  });

  $(document).on('click', '#runSelected', function() {
    var selected = [];
    $('#requests input:checked[name="operateSelect"]').each(function() {
      selected.push($(this).prop('id'));
    });
    if (selected.length != 0) {
      $('#requestsToSend').prop('value', selected);
      startTest();
    }
    return false;
  });

  $(document).on('click', '#runAll', function() {
    var selected = [];
    $('#requests input:checkbox:not(:checked)[name="disableSelect"]').each(function() {
      selected.push($(this).prop('id'));
    });
    if (selected.length != 0) {
      $('#requestsToSend').prop('value', selected);
      startTest();
    }

  });

  function startTest() {
    $('#environmentModal').modal('show');
    return false;
  }

  $('#confirmEnvironmentModal').click(function(e) {
    var envId = $('#environment').val();
    sendTestData(envId);
    return false;
  });

  function sendTestData(envId) {
    $.ajax({
      type: 'POST',
      url: '/web-tester/tests/requests/run',
      data: {
        environmentId: envId,
        requestIdArray: $('#requestsToSend').val().split(',')
      },
      success: function(data, textStatus, jqXHR) {
        alert(data);
      },
      error: function(jqXHR) {
        alert(0);
      },
    });
  }

  $(document).on('click', '.removeInstance', function() {
    if (confirm('Do you really want to delete the request?')) {
      deleteRequests([$(this).prop('id')]);
    }
    return false;
  });

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

  function deleteRequests(input) {
    $.ajax({
      type: 'DELETE',
      url: '/web-tester/tests/requests',
      contentType: 'application/json',
      data: JSON.stringify(input),
      success: function(data, textStatus, jqXHR) {
        for (var i = 0; i < input.length; i++) {
          $('#requests input[type="checkbox"][id=' + input[i] + ']').parents('tr').remove();
        }
        alert('code: ' + jqXHR.status);
      },
      error: function(jqXHR) {
        alert('oyva.. code: ' + jqXHR.status);
      },
    });
  };

});