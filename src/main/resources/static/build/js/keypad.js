console.log("keypad loaded");

var display = $('.js-display');

$('.js-key').on('click', function (e) {
    e.preventDefault();

    var $this = $(this);
    display.val(display.val()+ $this.text());
});