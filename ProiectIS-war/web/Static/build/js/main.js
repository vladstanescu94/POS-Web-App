$('.btn-minus').on('click', function (e) {
    e.preventDefault();
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());
 
    if (value > 1) {
        value = value - 1;
    } else {
        value = 0;
    }
 
  $input.val(value);
});

$('.btn-plus').on('click', function(e) {
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());
 
    if (value < 100) {
        value = value + 1;
    } else {
        value =100;
    }
 
    $input.val(value);
});

$('.btn-delete').on('click', function(e) {
    e.preventDefault();
    var $this = $(this);
    var $item = $this.closest('li');
    
    $item.remove();
});

$('.js-delete-all').on('click', function(e) {
    e.preventDefault();
    var $this = $(this);
    var $list = $('.products__list');
    
    $list.empty();
});