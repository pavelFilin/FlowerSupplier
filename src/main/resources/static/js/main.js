const $productFileInput = $("#product-photo-file-input");
const $productFileLabel = $("#product-photo-file-label");

$productFileInput.change(function () {
    let file = $productFileInput[0].files[0];
    $productFileLabel.text(file.name);
});

function resetFile() {
    $productFileInput.val('');
    $productFileLabel.text('Choose file');
}

$("#product-photo-reset").click(function () {
    resetFile();
});


function changeCartQuantity(input, productId) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    if (input.value < 1) {
        input.value = 1;
    }


    $.ajax({
        method: "POST",
        url: "/cart/change/orderstatus",
        data: {productId: productId, quantity: input.value},
        dataType: 'json',
        success: function (data) {
            if (data != "success") {
                alert(data)
            } else {
                location.reload();
            }
        },
        error: function () {
            alert('error!');
        }
    });
}


function changeOrderStatus(input, orderId) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    $.ajax({
        method: "POST",
        url: "/order/change/orderstatus",
        data:
        // data: JSON.stringify({
        //     id: orderId, orderStatus: input.value
        // }),
            {id: orderId, orderStatus: input.value},
        dataType: 'json',
        success: function (data) {

        },
        error: function () {
        }
    });
}

function changeProductStock(input, productId) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

    if (input.value < 0) {
        input.value = 0;
    }


    $.ajax({
        method: "POST",
        url: "/product/change/stock",
        data: {productId: productId, stock: input.value},
        dataType: 'json',
        success: function (data) {
            if (data != "success") {
                alert(data)
            } else {
                location.reload();
            }
        },
        error: function () {
            alert('error!');
        }
    });
}

function changeProductActive(input, productId) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });


    $.ajax({
        method: "POST",
        url: "/product/change/active",
        data: {productId: productId, active: input.value},
        dataType: 'json',
        success: function (data) {
            if (data != "success") {
                alert(data)
            } else {
                location.reload();
            }
        },
        error: function () {
            alert('error!');
        }
    });
}


function deleteCartItem(cartId) {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });


    $.ajax({
        method: "POST",
        url: "/cart/delete",
        data: {cartId: cartId},
        dataType: 'json',
        success: function (data) {
            if (data != "success") {
                alert(data)
            } else {
                location.reload();
            }
        },
        error: function () {
            alert('error!');
        }
    });
}

