$(function() {

  $('#user').bootstrapValidator({
    message: 'This value is not valid',
    feedbackIcons: {
      valid: 'glyphicon glyphicon-ok',
      invalid: 'glyphicon glyphicon-remove',
      validating: 'glyphicon glyphicon-refresh'
    },
    submitButtons: 'button[id="validate"]',
    fields: {
      username: {
        validators: {
          notEmpty: {
            message: 'Username cannot be empty'
          },
          emailAddress: {
            message: 'The value is not a valid email address'
          },
          remote: {
            url: 'account/isUsernameFree',
            type: 'GET',
            data: {
              id: $('#id').val(),
              username: $('#username').val()
            },
            delay: 1000,
            message: 'User with same email already exists'
          }
        }
      },
      password: {
        validators: {
          notEmpty: {
            message: 'Password cannot be empty'
          },
          stringLength: {
            min: 4,
            max: 32,
            message: 'The password must be more than 4 and less than 32 characters long'
          }
        }
      },
    }
  }).on('success.form.bv', function(e) {
    var form = $(e.target),
    bv = form.data('bootstrapValidator');
    bv.defaultSubmit();
  });

});