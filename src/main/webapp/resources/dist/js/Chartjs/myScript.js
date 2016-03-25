$(function() {

  var x = [];
  var y = [];

  $('.x').each(function() {
    x.push($(this).text());
  });

  $('.y').each(function() {
    y.push($(this).text());
  });

  var data = {
    labels: x,
    datasets: [{
      label: "My Second dataset",
      fillColor: "rgba(151,187,205,0.2)",
      strokeColor: "rgba(151,187,205,1)",
      pointColor: "rgba(151,187,205,1)",
      pointStrokeColor: "#fff",
      pointHighlightFill: "#fff",
      pointHighlightStroke: "rgba(151,187,205,1)",
      data: y
    }]
  };

  var myLineChart = document.getElementById("canvas").getContext("2d");
  window.myLine = new Chart(myLineChart).Line(data, {
    responsive: true,
    scaleLabel: function(f) {
      return f.value + 'ms';
    }
  });
});