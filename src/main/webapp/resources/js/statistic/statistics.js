$(function() {

  $(document).ready(function() {
    $('#statistics').DataTable({
      language: {
        search: "Search by name:",
        searchPlaceholder: "search..."
      },
      order: [
        [1, 'asc']
      ],
      columnDefs: [{
        targets: [0,2,3,4],
        orderable: false,
      }]
    });
  });
  
  $('#selectAll').click(function() {
    $('input[type="checkbox"][name="operateSelect"]').prop('checked', this.checked);
  });
}); 