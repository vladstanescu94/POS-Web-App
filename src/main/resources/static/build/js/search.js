function searchProducts() {
    var input = document.getElementById("searchBox");
    var textToBeSearched = input.value;

    var matchingProducts = getMatchingProducts(textToBeSearched);
    updateList(matchingProducts);
}

function getMatchingProducts(textToBeMatched) {
    var matchingProducts = [];
    products.forEach((product, index) => {
        if (product.name.toLowerCase().includes(textToBeMatched.toLowerCase())) {
            matchingProducts.push(product);
        }
    });
    return matchingProducts;
    
}

function updateList(products) {
    document.getElementsByClassName("products__list -dashboard")[0].innerHTML = "";

    if(products.length===0)
    {
        document.getElementsByClassName("products__list -dashboard")[0].innerHTML = "NOTHING TO SHOW";
    }

    var htmlToBeCompiled = document.getElementById('productItem').innerHTML;
    var template = Handlebars.compile(htmlToBeCompiled);
    products.forEach((product, index) => {
        var context = template(product);
        $('.products__list').append(context);
    });
}