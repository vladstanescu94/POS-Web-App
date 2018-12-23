document.getElementById("imageSelect").onchange = function () {
    console.log("image updated");
    var file = document.getElementById('imageSelect').files[0];
    var formData = new FormData();
    if (file) {
        formData.append("image", file);
    }

    $.ajax({
        url: "/file/upload",
        type: 'POST',
        enctype: "multipart/form-data",
        processData: false,
        contentType: false,
        cache: false,
        // data:$('#imageSelect').serialize(),
        data: formData,
        success: function (data) {
            document.getElementById("imageUUID").value=data;
        },
        error: function (data) {
            console.log(data);
        }
    });
};