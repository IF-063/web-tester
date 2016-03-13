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
    disableAddButton(this, 0);
    return false;
  });

  // deletes row from headers, variables or dbvalidations table
  $(document).on('click', '.removeInstance', function() {
    var container = $(this).closest('.elementContainer');
    var row = $(this).parents('.dataRow');
    row.remove();
    disableAddButton(container);
    setVisibility(container);
    return false;
  });

  // sets state of addButton
  $(document).on('keyup', '.elementContainer input[type=text]', function(e) {
    disableAddButton(this);
  });

  // disables addButton if there are empty fields in current elementContainer
  function disableAddButton(element, inputState) {
    var status = inputState ? inputState : restrictIfEmpty(element);
    $(element).closest('.elementContainer').find('.addButton').prop('disabled', status);
  }

  // sets visibility of clearCollection button in tables
  $('.clearCollection').each(function() {
    setVisibility($(this).closest('.elementContainer'));
  });

  // sets visibility to container
  function setVisibility(container) {
    var value = (container.find($('.dataRow')).size() > 1) ? 'inline' : 'none';
    container.find($('.clearCollection')).css('display', value);
  }

  // sets elements enabled in variables table  
  $('input:checkbox:not(:checked)[class=random]').each(function() {
    setDisableIfRandom(this, 1);
  });

  // changes elements enabled depending on state of isSql checkbox 
  $(document).on('change', '.sql', function() {
    if ($(this).is(':checked')) {
      var isRandomCheckbox = $(this).closest('tr').find('.random');
      isRandomCheckbox.prop('checked', 0);
      setDisableIfRandom(isRandomCheckbox);
      disableAddButton(this);
    }
  });

  // changes elements enabled depending on state of isRandom checkbox 
  $(document).on('change', '.random', function() {
    $(this).closest('tr').find('.sql').prop('checked', 0);
    setDisableIfRandom(this);
    disableAddButton(this);
  });

  // sets elements enabled depending on state of isRandom checkbox or inputState variable
  function setDisableIfRandom(isRandomCheckbox, inputState) {
    var obj = $(isRandomCheckbox);
    var state = inputState ? inputState : !obj.prop('checked');
    obj.closest('tr').find('.enableIfRandom').each(function() {
    	
      var obj = $(this);
      if (obj.is('input[type="text"]')) {
        if (!state) {
          $('#request').validate();
          obj.rules('add', {
            min: 1
          });
        } else {
          var curTd = obj.parents('td');
          curTd.removeClass('has-error');
          curTd.find('em').remove();
          curTd.find('span').remove();
        }
      }
      
      $(this).prop('disabled', state);
    });
  }

  var counter = 0;

  // adds new row to header's, variable's or dbvalidation's tables
  $(document).on('click', '.addButton', function(e) {
    var row = $('#template').find('tr').eq($(this).prop('id')).clone();
    $(this).closest('.elementContainer').find('table').append(row);
    // row.find('input[type="text"]:not(:disabled)').each(function() {
    row.find('input[type="text"]').each(function() {
      var val = counter++;
      $(this).prop('name', $(this).prop('name') + val);
    });
    setVisibility($(this).closest('.elementContainer'));
    $(this).prop('disabled', 1);
    return false;
  });

  // doesn't allow to add new row to header's, variable's or dbvalidation's tables in case of empty fields
  function restrictIfEmpty(button) {
    var container = $(button).closest('.elementContainer');
    var count = 0;
    container.find(':input[type=text]:visible:not(:disabled)').each(function() {
      if (!$.trim($(this).val())) {
        count++;
      }
    });
    return count > 0;
  }

  // resets page to original state	
  $('#reset').click(function() {
    location.reload();
    return false;
  });

  // cleans all field
  $('#clean').click(function(e) {
    $('.elementContainer').find($('.dataRow')).remove();
    $('.clearCollection').css('display', 'none');
    $('select :first-child').prop('selected', 'selected');
    $('.multipleSelect').select2('val', null);
    $('input[type=text],textarea').val('');
    return false;
  });

  $('#request').validate({
    onkeyup: function(element) {
      $(element).valid();
    },
    rules: {
      name: {
        required: true,
        remote: {
          url: '/web-tester/tests/requests/isRequestNameFree',
          type: 'POST',
          dataType: 'json',
          data: {
            name: function() {
              return $('#name').val();
            },
            exclusionId: $('#id').val()
          }
        },
      },
      description: 'required',
      requestMethod: 'required',
      application: 'required',
      service: 'required',
      endpoint: 'required',
      responseType: 'required',
      expectedResponse: 'required',
      timeout: {
        required: true,
        min: 1
      },
    },
    messages: {
      name: {
        required: 'Name can not be empty',
        remote: 'Name should be unique'
      },
      description: {
        required: 'Description can not be empty'
      },
      requestMethod: {
        required: 'Request method can not be empty'
      },
      application: {
        required: 'Application can not be empty'
      },
      service: {
        required: 'Service can not be empty'
      },
      endpoint: {
        required: 'Endpoint can not be empty'
      },
      responseType: {
        required: 'Response type can not be empty'
      },
      expectedResponse: {
        required: 'Expected response can not be empty'
      },
      timeout: {
        required: 'Timeout can not be empty',
        min: 'Timeout must be greater than or equal to 1'
      },
    },
    errorElement: "em",
    errorPlacement: function(error, element) {
      error.addClass("help-block");
      element.parents("div,td").addClass("has-feedback");
      error.insertAfter(element);
      if (!element.next("span")[0]) {
        $("<span class='glyphicon glyphicon-remove form-control-feedback'></span>").insertAfter(element);
      }
    },
    success: function(label, element) {
      if (!$(element).next("span")[0]) {
        $("<span class='glyphicon glyphicon-ok form-control-feedback'></span>").insertAfter($(element));
      }
    },
    highlight: function(element, errorClass, validClass) {
      $(element).parent('').addClass("has-error").removeClass("has-success");
      $(element).next("span").addClass("glyphicon-remove").removeClass("glyphicon-ok");
    },
    unhighlight: function(element, errorClass, validClass) {
      $(element).parent('div,td').addClass("has-success").removeClass("has-error");
      $(element).next("span").addClass("glyphicon-ok").removeClass("glyphicon-remove");
    },
    submitHandler: function(form) {
      normalizeLists();
      form.submit();
    }
  });

  // sets indexes in headers, variables or dbvalidations lists
  // should be invoked BEFORE FORM SUBMITING
  function normalizeLists() {
    $('#request').find($('table')).each(function() {
      var prefix = $(this).prop('id');
      var iteration = 0;
      $(this).find('.dataRow').each(function() {
        var currentTr = $(this);
        currentTr.find($('td')).each(function() {
          var currentTd = this;
          var suffix = $(currentTd).prop('id');
          var children = currentTd.querySelectorAll("*");
          $(children).each(function() {
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