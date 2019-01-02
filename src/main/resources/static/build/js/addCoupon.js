$(".js-confirm-coupon").on('click', function (e) {
    var logLabel = document.getElementById('coupon-message');
    var code = $('input[name=coupon-code]')[0];
    var discount = $('input[name=coupon-discount]')[0];
    var date = $('input[name=coupon-date]')[0];
    $.ajax({
        url: "/coupon/add",
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify({
            code: code.value,
            discountPercentage: discount.value,
            expiryDate: date.value,
        }),
        contentType: "application/json; charset=utf-8",
        success: function (data) {
            code.value = '';
            discount.value = '';
            date.value = '';
            logLabel.innerHTML = "Coupon saved. This modal will close in 3 seconds";
            setTimeout(
                function () {
                    document.getElementById('js-add-coupon').style.display = 'none';
                    logLabel.innerHTML="";
                }, 3000);
        },
        error: function () {
            logLabel.innerHTML="THERE WAS A ERROR";
        }
    });
});