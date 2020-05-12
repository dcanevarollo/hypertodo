/**
 * @author   : Douglas Canevarollo
 * @date     : 01/04/2020
 */
$(document).ready(function() {
  var passwordInput = $('#password-input');
  var confirmPasswordInput = $('#confirm-password-input');
  var confirmPasswordError = $('#confirm-password-error');
  var submitButton = $('#submit-button');

  $('#confirm-password-input, #password-input')
    .on('focus', function() {
      confirmPasswordError.attr('hidden', 'hidden');
    })
    .on('keyup', function() {
      var password = passwordInput.val();
      var confirmPassword = confirmPasswordInput.val();

      if (confirmPassword !== '')
        if (password !== confirmPassword) {
          confirmPasswordError.removeAttr('hidden');
          submitButton.prop('disabled', true);
        } else {
          confirmPasswordError.attr('hidden', 'hidden');
          submitButton.prop('disabled', false);
        }
    });
});
