$(function() {

  $(document).ready(function() {
    $("#serviceTable").DataTable({
      language: {
        search: "Search by name:",
        searchPlaceholder: "search..."
      },
      order: [
        [0]
      ],
      columnDefs: [{
        targets: [1,2,3],
        orderable: false,
      }]
    });
  });
});