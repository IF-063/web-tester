$(function() {
	
  // enables tag autocomplete in 'labels' field
  $('.multipleSelect').select2({
    theme: 'bootstrap'
  });

  //enables request body field on start
  setRequestBodyDisabled(document.getElementById('requestMethod'));

  // enables request body field depending on request method change
  $('#requestMethod').on('change', function() {
    setRequestBodyDisabled(this);
  });

  //enables request body field
  function setRequestBodyDisabled(requestMethod) {
    var enabled = requestMethod.value == 'POST' || requestMethod.value == 'PUT';
    $('#requestBody').prop('disabled', !enabled);
  }

  // clears headers, variables or dbvalidations lists
  $(document).on('click', '.clearCollection', function() {
    $(this).closest('.elementContainer').find($('.dataRow')).remove();
    $(this).css('display', 'none');
    return false;
  });

  // deletes row from headers, variables or dbvalidations table
  $(document).on('click', '.removeInstance', function() {
    var container = $(this).closest('.elementContainer');
    $(this).parents('.dataRow').remove();
    setVisibility(container);
    return false;
  });

  // sets visibility of clearCollection button in tables
  $('.clearCollection').each(function() {
    setVisibility($(this).closest('.elementContainer'));
  });

  // set visibility to container
  function setVisibility(container) {
    var value = (container.find($('.dataRow')).size() > 1) ? 'inline' : 'none';
    container.find($('.clearCollection')).css('display', value);
  }

  // sets elements enabled in variables table  
  $('input:checkbox:not(:checked)[class=isRandom]').each(function() {
    $(this).closest('tr').find('.enableIfRandom').each(function() {
      $(this).prop('disabled', 1);
    });
  });

  // changes elements enabled depending on state of isRandom checkbox 
  $(document).on('change', '.isRandom', function() {
    var obj = $(this);
    obj.closest('tr').find('.enableIfRandom').each(function() {
      $(this).prop('disabled', !obj.prop('checked'));
    });
  });
  
  // handles adding new headers
  $(document).on('click', '#addHeader', function() {
	if (!restrictIfEmpty(this)){
      var headerTableRow = $('#template').find('tr').eq(0).clone();
      console.log(headerTableRow);
      $('#headers').append(headerTableRow);
      setVisibility($(this).closest('.elementContainer'));
    }
    return false;
  });

  //handles adding new variables
  $(document).on('click', '#addVariable', function(e) {
	if (!restrictIfEmpty(this)){
      var variableTableRow = $('#template').find('tr').eq(1).clone();
      variableTableRow.find('#\\.dataType').empty().append($('#variableDataTypesTemplate').find('select').clone());
      $('#variables').append(variableTableRow);
      setVisibility($(this).closest('.elementContainer'));
	}
    return false;
  });

  //handles adding new dbValidations
  $(document).on('click', '#addDbValidation', function() {
	if (!restrictIfEmpty(this)){
      var dbValidationTableRow = $('#template').find('tr').eq(2).clone();
      $('#dbValidations').append(dbValidationTableRow);
      setVisibility($(this).closest('.elementContainer'));
	}
    return false;
  });
  
  function restrictIfEmpty(button) {
	var container = $(button).closest('.elementContainer');
	var count = 0;
	container.find(':input[required]:visible').each(function() {
	  if (!$.trim($(this).val())) {
	    count++;
	  }    
	});
	return count > 0;
  }

//      $('.isSql').click(function() {
//    	var isSqlObj = $(this);
//    	isSqlObj.closest('tr').find('.disableIfSql').each(function () {
//    	  $(this).prop('checked', 0);
//    	  $(this).prop('disabled', isSqlObj.prop('checked'));
//    	});
//      });
//      
//      $('input:checked[class=isSql]').each(function () {
//    	$(this).closest('tr').find('.disableIfSql').each(function () {
//    	  $(this).prop('checked', 0);
//    	  $(this).prop('disabled', 1);
//    	});
//      });

//   $('#request').bootstrapValidator({
//        message: 'This value is not valid',
//        feedbackIcons: {
//          valid: 'glyphicon glyphicon-ok',
//          invalid: 'glyphicon glyphicon-remove',
//          validating: 'glyphicon glyphicon-refresh'
//        },
//  	  rules : {
//  		"headers*" : {
//  		  required : true
//  		},
//  		"dbValidations*" : {
//  		  required : true
//  		},	
//  	  },
//        fields: {
//          name: {
//            validators: {
//              notEmpty: {
//              	message: 'Request name cannot be empty'
//              },
//              remote: {
//              	url: 'create/isRequestNameFree',
//              	type: 'GET',
//              	delay: 1000,
//              	message: 'Request with same name already exists'
//              },
//            }
//          },
//          description: {
//            validators: {
//              notEmpty: {
//              	message: 'Description cannot be empty'
//              }
//            }
//          },
//          requestMethod: {
//            validators: {
//              notEmpty: { }
//            }
//          },
//          application: {
//            validators: {
//              notEmpty: { }
//            }
//          },
//          service: {
//            validators: {
//              notEmpty: { }
//            }
//          },
//          endpoint: {
//            validators: {
//              notEmpty: { }
//            }
//          },
//          responseType: {
//            validators: {
//              notEmpty: { }
//            }
//          },
//          timeout: {
//            validators: {
//              notEmpty: {},
//              integer: {},
//              greaterThan: {
//              	value: 1
//              }
//            }
//          },
//        }
//      }).on('success.form.bv', function(e) {
//       // e.preventDefault();
//        var form = $(e.target),
//        bv = form.data('bootstrapValidator');
//        normalizeLists();
//        bv.defaultSubmit();
//      });

//  $('#validate').click(function() {
//  	$('#request').bootstrapValidator().on('success.form.bv', function(e) {
//        e.preventDefault();
//        var form = $(e.target),
//        bv = form.data('bootstrapValidator');
//        normalizeLists();
//        bv.defaultSubmit();
//      });
//  });

  $('#validate').click(function() {
    normalizeLists();
    $('#requests').submit();
  });
  
  $(document).on('click', '#buttton', function() {
	isRequestNameFree();
	return false;
  });
  
  function isRequestNameFree() {
	if ($('#name').length > 0) {
	  $.ajax({
	    type: 'GET',
	    url: 'create/isRequestNameFree?name=' + $('#name').val(),
	    success: function(data) {
	      alert(data);
	    },
	    error: function() {
	      alert(0);
	    },
	  });
	}
  }
  
  // sets indexes in headers, variables or dbvalidations lists
  // should be invoked BEFORE FORM SUBMITING
  function normalizeLists() {
    $('#request').find($('table')).each(function() {
      var prefix = $(this).prop('id');
      var iteration = 0;
      $(this).find('.dataRow').each(function() {
        var currentTr = $(this);
        currentTr.find($('td')).each(function() {
          var currentTd = $(this);
          var suffix = currentTd.prop('id');
          console.log('suffix: ' + suffix);
          var children = currentTd.children();
          children.each(function() {
            var child = $(this);
            var type = child.prop('type');
            if (type == 'text') {
              child.prop('id', prefix + iteration + suffix);
              child.prop('name', prefix + '[' + iteration + ']' + suffix);
            }
            if (type == 'checkbox') {
              child.prop('id', prefix + iteration + suffix + '1');
              child.prop('name', prefix + '[' + iteration + ']' + suffix);
            }
            if (type == 'hidden') {
              child.prop('id', prefix + iteration + suffix);
              child.prop('name', '_' + prefix + '[' + iteration + ']' + suffix);
            }
            if (type == 'label') {
              child.prop('for', prefix + iteration + suffix);
            }
            if (child.get(0).tagName == 'SELECT') {
              child.prop('id', prefix + iteration + suffix);
              child.prop('name', prefix + '[' + iteration + ']' + suffix);
            }
          });
        });
        iteration++;
      });
    });
  }

});