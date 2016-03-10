$(function() {

  $('.multipleSelect').select2({
    theme: 'bootstrap',
    width: '100%'
  });
  
  $('.multiSelect').multiSelect({
    dblClick: true
  });
  
  $('#select-all').click(function(){
      $('.multiSelect').multiSelect('select_all');
      return false;
  });
  $('#deselect-all').click(function(){
    $('.multiSelect').multiSelect('deselect_all');
    return false;
  });
  
  $('#reset').click(function() {
    location.reload();
    return false;
  });
  
  $('#clean').click(function(e) {
    $('.multipleSelect').select2('val', null);
    $('.multiSelect').multiSelect('deselect_all');
    $('input[type=text],textarea').val('');
    return false;
  });
})    
