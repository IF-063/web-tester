

    function connectionSuccessful(xhr) {
        alert("Success: " + xhr.status + " " + xhr.statusText);
    }

    function connectionFailed(xhr) {
        alert("An error occured: " + xhr.status + " " + xhr.statusText);
    }

    function checkEnvironment(id) {
        $("#glyphiconCheck"+id).
        $.ajax({
            type : "POST",
            url : contextPath + '/configuration/environments/check/' + id,
            success : function (data) {
                $("#glyphiconCheck"+id).css("color","green"),
                alert("Success: " + data);
            },
            error : function (data) {
                $("#glyphiconCheck"+id).css("color","red"),
                alert("Error: " + data);
            }
        });
    }
