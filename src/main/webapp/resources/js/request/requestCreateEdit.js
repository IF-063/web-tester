$(function() {

  // enables tag autocomplete in 'labels' field
  $('.multipleSelect').select2({
    theme: 'bootstrap',
    width: '100%'
  });

  // enables request body field on start
  setRequestBodyDisabled(document.getElementById('requestMethod'));

  // enables request body field depending on request method change
  $('#requestMethod').on('change', function() {
    setRequestBodyDisabled(this);
  });

  // enables request body field
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
	setDisableIfRandom(this, 1);
  });
  
  // changes elements enabled depending on state of isSql checkbox 
  $(document).on('change', '.isSql', function() {
	if ($(this).is(':checked')) {
	  var isRandomCheckbox = $(this).closest('tr').find('.isRandom');
	  isRandomCheckbox.prop('checked', 0);
	  setDisableIfRandom(isRandomCheckbox);
	}
  });

  // changes elements enabled depending on state of isRandom checkbox 
  $(document).on('change', '.isRandom', function() {
	$(this).closest('tr').find('.isSql').prop('checked', 0);
	setDisableIfRandom(this);
  });
  
  // sets  elements enabled depending on state of isRandom checkbox or inputState variable
  function setDisableIfRandom(isRandomCheckbox, inputState) {
	var obj = $(isRandomCheckbox);
	var state = inputState? inputState : !obj.prop('checked');
	obj.closest('tr').find('.enableIfRandom').each(function() {
	  $(this).prop('disabled', state);
	});
  }
  
  // add new row to header's, variable's or dbvalidation's tables
  $(document).on('click', '.addButton', function() {
    if (!restrictIfEmpty(this)){
	  var row = $('#template').find('tr').eq($(this).prop('id')).clone();
	  $(this).closest('.elementContainer').find('table').append(row);
	  setVisibility($(this).closest('.elementContainer'));
	}
    return false;
  });

  // doesn't allow to add new row to header's, variable's or dbvalidation's tables in case of empty fields
  function restrictIfEmpty(button) {
	var container = $(button).closest('.elementContainer');
	var count = 0;
	container.find(':input[required]:visible:not(:disabled)').each(function() {
	  if (!$.trim($(this).val())) {
	    count++;
	  }    
	});
	return count > 0;
  }
  
  // resets page to original state	
  $('#reset').click(function(){
	location.reload();
	return false;
  });
  
  // cleans all field
  $('#clean').click(function(e){
    $('.elementContainer').find($('.dataRow')).remove();
    $('select :first-child').prop('selected','selected');//
    $('.multipleSelect').select2('val', null);
    $('input[type=text],textarea').val('');
    return false;
  });
  
  // submits form
//  $('#validate').click(function() {
//    normalizeLists();
//    $('#requests').submit();
//  });

   var validator = $('#request')
  .bootstrapValidator({
        message: 'This value is not valid',
        excluded: [':disabled', ':hidden', ':not(:visible)'],
        feedbackIcons: {
          valid: 'glyphicon glyphicon-ok',
          invalid: 'glyphicon glyphicon-remove',
          validating: 'glyphicon glyphicon-refresh'
        },
        submitButtons: 'button[id="validate"]',
        container: 'tooltip',
//  	  rules : {
//  		"headers*" : {
//  		  required : true
//  		},
//  		"dbValidations*" : {
//  		  required : true
//  		},	
//  	  },
        fields: {
          name: {
            validators: {
              notEmpty: {
              	message: 'Request name cannot be empty'
              },
              remote: {
              	url: 'create/isRequestNameFree',
              	type: 'GET',
              	data: {name : $('#name').val(), exclusionId : $('#id').val()},
              	delay: 1000,
              	message: 'Request with same name already exists'
              },
            }
          },
          description: {
            validators: {
              notEmpty: {
              	message: 'Description cannot be empty'
              }
            }
          },
          requestMethod: {
            validators: {
              notEmpty: { }
            }
          },
          application: {
            validators: {
              notEmpty: { }
            }
          },
          service: {
            validators: {
              notEmpty: { }
            }
          },
          endpoint: {
            validators: {
              notEmpty: { }
            }
          },
          responseType: {
            validators: {
              notEmpty: { }
            }
          },
          timeout: {
            validators: {
              notEmpty: {},
              integer: {},
              greaterThan: {
              	value: 1
              }
            }
          },   
          
       
        }
      }).on('success.form.bv', function(e) {
       // e.preventDefault();
        var form = $(e.target),
        bv = form.data('bootstrapValidator');
        normalizeLists();
        bv.defaultSubmit();
      });
//   
//   
   $(document).on('click', '#vvv', function() {
	  
	   $('#request input, #request checkbox, #request textarea, #request select')
	   .filter('[required]:visible:not(:disabled)').each(function() {
		   console.log(this);
		   $('#request').bootstrapValidator('addField', $(this));
		 
		});
		return false;
	  });


//  $('#validate').click(function() {
//  	$('#request').bootstrapValidator().on('success.form.bv', function(e) {
//        e.preventDefault();
//        var form = $(e.target),
//        bv = form.data('bootstrapValidator');
//        normalizeLists();
//        bv.defaultSubmit();
//      });
//  });


  
  $(document).on('click', '#buttton', function() {
	isRequestNameFree();
	return false;
  });
  
  function isRequestNameFree() {
	if ($('#name').val()) {
	  $.ajax({
	    type: 'GET',
	    url: 'create/isRequestNameFree',
	    data: {name : $('#name').val(), exclusionId : $('#id').val()},
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