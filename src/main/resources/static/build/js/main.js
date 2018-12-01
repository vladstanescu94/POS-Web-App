//GLOBAL VARIABLES
var productList=[
    //format { id: id, quantity:quantity}
];




$('.btn-minus').on('click', function (e) {
    e.preventDefault();
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());
 
    if (value > 1) {
        value = value - 1;
    } else {
        value = 1;
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

$('#btn-search').on('click', function(e) {
    $searchbar=$('.keypad__display');
    getProduct($searchbar.val());
    $searchbar.val('');
});

$('.keypad__display').keypress(function(e) {
    if(e.which == 13) {
        getProduct($(this).val());
        e.preventDefault();
        $(this).val('');
    }
    else if(e.which<48||e.which>57)
    {   
        e.preventDefault();
        alert('Only numbers are allowed');
    }
});

function getProduct(code){
    var cod=$('.keypad__display').val();
    if(cod!=''){
    $.get("/getProductID",{cod:cod},function (data) {
        //TODO POPULATE THE WEBPAGE WITH RESULT
        if(data!=''){
      console.log(data);}
      else{
          alert("There is no product with code: "+cod);
        }
    });}
    else{
        alert("Please insert a code first");
    }
};