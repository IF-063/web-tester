$(function() {
	
  $('.multipleSelect').select2({
    theme: 'bootstrap'
  });
  
  $('#requests').multiSelect({
	  dblClick: true
  });
  
  $('#select-all').click(function(){
      $('#requests').multiSelect('select_all');
      return false;
  });
  $('#deselect-all').click(function(){
	  $('#requests').multiSelect('deselect_all');
	  return false;
  });
  
  $('#reset').click(function() {
	  location.reload();
	  return false;
  });
})    
