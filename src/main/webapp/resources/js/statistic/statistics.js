$(function() {

  $('.select2-multiple').select2({
    theme : 'bootstrap',
    width : '100%'
  });

  var data = $('#statistics .data');
  for (var i = 1; i < data.length; i++) {
    var element = $(data[i]);
    var diff = element.text() - $(data[i-1]).text();
    if (diff != 0){
      element.css('background-color', diff<0 ? '#71c171' : '#d85e5a');
    }
  }

});