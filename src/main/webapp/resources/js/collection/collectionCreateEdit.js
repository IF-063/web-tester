$(function() {
	
  $('.multipleSelect').select2({
    theme: 'bootstrap'
  });
  
  $('#requests').multiSelect({
	  dblClick: true
  });
  
  $('#reset').click(function() {
	  location.reload();
	  return false;
  });
})    
