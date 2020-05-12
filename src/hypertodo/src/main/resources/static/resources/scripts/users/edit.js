/**
 * @author   : Douglas Canevarollo
 * @date     : 02/04/2020
 */
$(document).ready(function() {
  var passwordInput = $('#password-input');
  var submitButton = $('#submit-button');

  passwordInput.on('keyup', function() {
    var password = passwordInput.val();

    if (password !== '') submitButton.prop('disabled', false);
    else submitButton.prop('disabled', true);
  });

  var newPasswordInput = $('#new-password-input');
  var confirmNewPasswordInput = $('#confirm-new-password-input');
  var confirmNewPasswordError = $('#confirm-new-password-error');
  var submitNewPasswordButton = $('#submit-new-password-button');

  $('#confirm-new-password-input, #new-password-input')
    .on('focus', function() {
      confirmNewPasswordError.attr('hidden', 'hidden');
    })
    .on('keyup', function() {
      var password = newPasswordInput.val();
      var confirmPassword = confirmNewPasswordInput.val();

      if (confirmPassword !== '')
        if (password !== confirmPassword) {
          confirmNewPasswordError.removeAttr('hidden');
          submitNewPasswordButton.prop('disabled', true);
        } else {
          confirmNewPasswordError.attr('hidden', 'hidden');
          submitNewPasswordButton.prop('disabled', false);
        }
    });
});
