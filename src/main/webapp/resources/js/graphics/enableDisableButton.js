$("#Time1, #Time2").on("change", function(){
    enableDisableButton();
});

function enableDisableButton(){
    if($("#BuildVersion").val() && $("#Time1, #Time2").is(":checked") == true){
        $("#Submit").prop("disabled", false);
        $("#Submit").prop("title", "Generating a new graphic");
    } else {
        $("#Submit").prop("disabled", true);
    }
}