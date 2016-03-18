$(function() {
    // enables tag autocomplete in filtering fields
    $('#applicationFilter, #serviceFilter').select2({
        theme: 'bootstrap',
        width: '100%'
    });

    // selects all request on page
    $('#selectAll').click(function() {
        $('#results input[type="checkbox"][name="operateSelect"]').prop('checked', this.checked);
    });

    // handles remove selected requests button click
    $(document).on('click', '#deleteSelected', function() {
        var selected = [];
        $('input:checked[name="operateSelect"]').each(function() {
            selected.push($(this).prop('id'));
        });
        if (selected.length != 0 && confirm('Do you really want to delete the results?')) {
            deleteRequests(selected);
        }
        return false;
    });

    // sends requests to delete to the server
    function deleteRequests(input) {
        console.log(input);
        $.ajax({
            type: 'DELETE',
            url: '/web-tester/results/requests',
            contentType: 'application/json',
            data: JSON.stringify(input),
            success: function(data, textStatus, jqXHR) {
                for (var i = 0; i < input.length; i++) {
                    $('input[type="checkbox"][id=' + input[i] + ']').parents('tr').remove();
                }
            },
            error: function(jqXHR) {
                alert('Error (code: ' + jqXHR.status + ')');
            },
        });
    };});