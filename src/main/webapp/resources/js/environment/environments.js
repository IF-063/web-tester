function showHideAlert() {
    $('#success-error').show(),
    $(".alert").on("click", function(e) {
        $(".alert").hide();
    });
}

function checkEnvironment(id) {
    $("#environmentCheck" + id).css("pointer-events", "none");
    $.ajax({
        type : "POST",
        url : contextPath + '/configuration/environments/check/' + id,
        success : function(data) {
            $("#environmentCheck" + id).css("color", "green", "pointer-events", "auto"),
            $(".alert").addClass("alert-success").removeClass("alert-danger"),
            $(".alert").html("<b>Success: </b>" + data),
            showHideAlert();
        },
        error : function(jqXHR, status, error) {
            $("#environmentCheck" + id).css("color", "red", "pointer-events", "auto"),
            $(".alert").addClass("alert-danger").removeClass("alert-success"),
            $(".alert").html("<b>Error: </b>" + jqXHR.responseText),
            showHideAlert();
        }
    });
}

$(".deleteEnvironment").click(function() {
    if (confirm("Are you sure you want to delete envronment?")) {
        var currentTd = $(this);
        $.ajax({
            type : "POST",
            url : contextPath + '/configuration/environments/delete/' + currentTd.prop("id"),
            success : function() {
                currentTd.parents("tr").remove();
            },
            error : function(jqXHR) {
                alert('Error (code: ' + jqXHR.status + ')');
            },
        });
    }
    return false;
});