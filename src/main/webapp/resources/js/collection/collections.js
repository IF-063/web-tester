$(function() {

  $('#collectionList #selectAll').click(function() {
    $('#collectionList input[type="checkbox"][name="operateSelect"]').prop('checked', this.checked);
  });

  $(document).on('click', '.removeInstance', function() {
    if (confirm('Do you really want to delete this Collection?')) {
    	deleteRequestCollections([$(this).prop('id')]);
    }
    return false;
  });

  $(document).on('click', '#deleteSelected', function() {
    var selected = [];
    $('#collectionList input:checked[name="operateSelect"]').each(function() {
      selected.push($(this).prop('id'));
    });
    if (selected.length != 0 && confirm('Do you really want to delete the requests?')) {
      deleteRequests(selected);
    }
    return false;
  });

  function deleteRequestCollections(input) {
    $.ajax({
      type: 'DELETE',
      url: '/web-tester/tests/collections',
      contentType: 'application/json',
      data: JSON.stringify(input),
      success: function(data, textStatus, jqXHR) {
        for (var i = 0; i < input.length; i++) {
          $('#collectionList input[type="checkbox"][id=' + input[i] + ']').parents('tr').remove();
        }
        alert('code: ' + jqXHR.status);
      },
      error: function(jqXHR) {
        alert('oyva.. code: ' + jqXHR.status);
      },
    });
  };

});