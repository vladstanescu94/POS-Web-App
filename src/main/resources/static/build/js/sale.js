
$(document).on('click', '.btn-minus', function (e) {
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


$(document).on('click', '.btn-plus', function (e) {
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());

    if (value < 100) {
        value = value + 1;
    } else {
        value = 100;
    }

    $input.val(value);
});


//TODO IF MORE THAN 1 IN QUANTITY ASK IF HE WANTS TO REMOVE ALL PRODUCTS
$(document).on('click', ".btn-delete", function (e) {
    e.preventDefault();
    var $this = $(this);
    var $item = $this.closest('li');

    $item.remove();

    console.log("delete");
});

$(document).on('click', '.js-delete-all', function (e) {
    e.preventDefault();
    var $this = $(this);
    var $list = $('.products__list');

    $list.empty();
    productList.empty();
});

$('.js-btn-search').on('click', function (e) {
    $searchbar = $('.keypad__display');
    getProduct($searchbar.val());
    $searchbar.val('');
});

$('.keypad__display').keypress(function (e) {

    if (e.which === 13) {
        getProduct($(this).val());
        $(this).val('');
        e.preventDefault();
    } else if (e.which < 48 || e.which > 57) {
        e.preventDefault();
        alert('Only numbers are allowed');
    }
});

function getProduct(id) {

    if (id !== '') {
        $.get("/api/getProductID", {
            id: id
        }, function (data) {
            if (data !== '') {
                populateOrIncrementProductList(data);
                // console.log(data);
            } else {
                alert("There is no product with code: " + id);
            }
        });
    } else {
        alert("Please insert a code first");
    }
};

function populateOrIncrementProductList(data) {
    var product = convertDataToProduct(data);
    var element = getElementInList(product);
    if (element != null) {
        incrementProductQuantity(element);
    }
    else {
        populateProductList(product);
    }
};

//TODO REMOVE THIS AFTER FIXING IMAGE PROBLEMS
function convertDataToProduct(data) {
    var product = {};
    product["id"] = data.id;
    product["name"] = data.name;
    product["quantity"] = 1;
    product["price"] = data.price;
    //TODO IMPLEMENT IMAGEURL
    product["imageSource"] = null;
    return product;
}

//TODO CREATE PRECOMPILED HANDLEBARS FILE
function populateProductList(product) {

    var htmlToBeCompiled = document.getElementById('productItem').innerHTML;
    var template = Handlebars.compile(htmlToBeCompiled);
    var context = template(product);
    $('.products__list').append(context);

}

function getElementInList(product) {
    var elements = document.getElementsByClassName("product");
    for (var i = 0; i < elements.length; i++) {
        var innerHTML = elements[i].getElementsByClassName('product__code')[0].innerHTML;
        var id = innerHTML.match(/[0-9]+/)[0];
        id = parseInt(id, 10);
        if (id === product["id"]) {
            return elements[i];
        }
    }
    return null;
}

function incrementProductQuantity(element){
    var quantityInput=element.getElementsByClassName("quantity__input")[0];
    var quantity=parseInt(quantityInput.value,10);
    quantityInput.value=quantity+1;
}

function setQuantity(){
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());
}