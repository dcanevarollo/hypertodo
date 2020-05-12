/**
 * @author   : Douglas Canevarollo
 * @date     : 01/04/2020
 */
$(document).ready(function() {
  var toast = $('#toast');

  if (toast.hasClass('error') || toast.hasClass('success')) {
    toast.addClass('show');

    setTimeout(function() {
      toast.removeClass('show');
    }, 3000);
  }
});