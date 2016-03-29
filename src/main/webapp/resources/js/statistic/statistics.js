$(function() {

  $('.select2-multiple').select2({
    theme: 'bootstrap',
    width: '100%'
  });

  $('.serviceStatistic').each(function() {
    var data = $(this).find('.data');
    for (var i = 1; i < data.length; i++) {
      var element = $(data[i]);
      if (element.text()) {
        var j = i - 1;
        var prevValue;
        do {
          prevValue = $(data[j]).text();
        } while (j-- > 0 && !prevValue);
        if (prevValue) {
          var diff = element.text() - prevValue;
          if (diff != 0) {
            element.css('background-color', diff < 0 ? '#71c171' : '#d85e5a');
          }
        }
      }
    }
  });

  $('#exportXls').click(function(e) {
    $.ajax({
      type: 'GET',
      url: '/web-tester/reports/statistic/xls?' + $('#statisticFilterDTO').serialize(),
      success: function(data, textStatus, jqXHR) {
        alert(1);
      },
      error: function(jqXHR) {
        alert(0);
      },
    });
    return false;
  });

});