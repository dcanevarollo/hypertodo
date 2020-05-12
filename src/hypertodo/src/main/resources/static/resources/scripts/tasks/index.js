/**
 * @author   : Douglas Canevarollo
 * @date     : 02/04/2020
 */
$(document).ready(function() {
  var titleInput = $('#title-input');
  var submitButton = $('#submit-button');

  titleInput.on('keyup', function() {
    var title = titleInput.val();

    if (title !== '') submitButton.prop('disabled', false);
    else submitButton.prop('disabled', true);
  });
});
