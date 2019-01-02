
window.onload-computePrices();


$(document).on('click', '.btn-minus', function (e) {
    e.preventDefault();
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());

    if (value > 1) {
        var id=$this.closest('.product').find('.product__code');
        id=id[0].innerHTML.match('[0-9]+')[0];
        $.get("/api/decreaseQuantity", {
            id: id
        }, function (data) {
            if (data === 'decreaseSuccess') {
                value=value -1;
                $input.val(value);
                computePrices();
            } else {
                alert("Something Went Wrong");
            }
        });
    }
});


$(document).on('click', '.btn-plus', function (e) {
    e.preventDefault();
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());

    var id=$this.closest('.product').find('.product__code');
    id=id[0].innerHTML.match('[0-9]+')[0];
    $.get("/api/increaseQuantity", {
        id: id
    }, function (data) {
        if (data === 'increaseSuccess') {
            value=value +1;
            $input.val(value);
            computePrices();
        }
        else if(data==='EOS'){
            alert("OUT OF STOCK");
        }
        else {
            alert("Something Went Wrong");
        }
    });
});


//TODO IF MORE THAN 1 IN QUANTITY ASK IF HE WANTS TO REMOVE ALL PRODUCTS
$(document).on('click', ".btn-delete.far", function (e) {
    e.preventDefault();
    var $this = $(this);
    var $item = $this.closest('li');

    var id=$this.closest('.product').find('.product__code');
    id=id[0].innerHTML.match('[0-9]+')[0];
    $.get("/api/removeProduct", {
        id: id
    }, function (data) {
        if (data === 'removeSuccess') {
            $item.remove();
            computePrices();
        }
        else if(data==='noProduct'){
            alert("THERE WAS NO PRODUCT ON SERVER");
        }
        else {
            alert("Something Went Wrong");
        }
    });
});

$(document).on('click', '.js-delete-all', function (e) {
    e.preventDefault();
    var $this = $(this);
    var $list = $('.products__list');
    $.post("/api/removeAllProducts");
    $list.empty();
    computePrices();
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
                computePrices();
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
    console.log(data);
    var product = convertDataToProduct(data);
    var element = getExistingElementFromProductsList(product);
    if (element != null) {
        incrementProductQuantity(element);
    }
    else {
        populateProductList(product);
    }
};

function convertDataToProduct(data) {
    var product = {};
    product["id"] = data.id;
    product["name"] = data.name;
    product["quantity"] = 1;
    product["price"] = data.price;
    product["image"] = data.image;
    return product;
}

//TODO CREATE PRECOMPILED HANDLEBARS FILE
function populateProductList(product) {

    var htmlToBeCompiled = document.getElementById('productItem').innerHTML;
    var template = Handlebars.compile(htmlToBeCompiled);
    var context = template(product);
    $('.products__list').append(context);

}

function getExistingElementFromProductsList(product) {
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
    computePrices();
}

function setQuantity(){
    var $this = $(this);
    var $input = $this.closest('div').find('input');
    var value = parseInt($input.val());
    computePrices();
}

//TODO ADD METHOD TO HANDLE COUPON REQUEST

function computePrices() {
    var price=totalSumOfProducts();
    document.getElementsByClassName("subtotal__value")[0].innerHTML=price+" RON";
    var discount=getDiscount();
    console.log(discount);
    var discountedPrice=(1-discount/100)*price;
    document.getElementsByClassName("total__value")[0].innerHTML=discountedPrice+" RON";
}

function totalSumOfProducts(){
    var ul= document.getElementsByClassName("products__list")[0];
    var sum=0;
    var products= ul.getElementsByClassName("product");
        [].forEach.call(products,
        (item,index)=>{
            var quantity=item.getElementsByClassName("quantity__input")[0].value;
            var price=item.getElementsByClassName("price__value")[0].innerHTML;
            sum+=quantity*price;
        }
    );
    return sum;
}

function getDiscount(){
    var discount=document.getElementsByClassName("discount__value")[0].innerHTML;
    return discount.match('[0-9]+')[0];
}

function checkCoupon(){
    var input = document.getElementsByClassName("modal__input")[0];
    console.log(input);
    var code=input.value;
    if (code !== '') {
        $.get("/coupon/check", {
            code: code
        }, function (data) {
            if (data !== '') {
                if(data>0)
                {
                    console.log(data);
                    // At first apply button should be greyed out & disabled

                    // TODO
                    // Should enable Apply button
                    // Should make itself green indicating coupon is valid
                }
                else{
                    // TODO
                    // Should make itself red
                }
            } else {
                alert("There was an error");
            }
        });
    }

}


function applyCoupon() {
    var input = document.getElementsByClassName("modal__input")[0];
    var code=input.value;
    if (code !== '') {
        $.get("/coupon/apply", {
            code: code
        }, function (data) {
            if (data !== '') {
                if(data>0)
                {
                    var discountElement=document.getElementsByClassName("discount__value")[0];
                    discountElement.innerHTML=data+"%";
                    computePrices();
                    document.getElementById('js-check-coupon').style.display='none';
                    document.getElementsByClassName("js-btn-coupons")[0].disabled=true;
                }
            } else {
                alert("There was an error");
            }
        });
    }
}