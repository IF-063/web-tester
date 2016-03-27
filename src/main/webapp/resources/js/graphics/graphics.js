$("#Time1, #Time2").change(function () {$("#Submit").prop("disabled", false);});
function valid(){
    var t = $("#BuildVersion");
    alert(t);

    if (!$("#BuildVersion").val()){
        $("#Submit").prop("disabled", false);
    }
};

$("#BuildVersion").change(function () {
    var f = $("#BuildVersion");
    if (!$("#BuildVersion")){
        $("#Submit").prop("disabled", false);
    }
});

$(function() {

// Make submit button inactive until input fields have been filled
// enables tag autocomplete in filtering fields
    $('.select2-multiple').select2({
        theme: 'bootstrap',
        width: '100%'
    });


    /*
     jQuery("#Submit").prop('disabled', true);

     var toValidate = jQuery('#Service, #BuildVersion'),
     valid = false;
     toValidate.keyup(function () {
     if (jQuery(this).val().length > 0) {
     jQuery(this).data('valid', true);
     } else {
     jQuery(this).data('valid', false);
     }
     toValidate.each(function () {
     if (jQuery(this).data('valid') == true) {
     valid = true;
     } else {
     valid = false;
     }
     });
     if (valid === true) {
     jQuery("#Submit").prop('disabled', false);
     } else {
     jQuery("#Submit").prop('disabled', true);
     }
     });
     */
});






