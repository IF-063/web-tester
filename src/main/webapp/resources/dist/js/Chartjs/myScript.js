$(function() {

  var data = {
    labels: $('#data .x').map(function() { return $(this).text(); }).get(),
    datasets: [{
      label: "My Second dataset",
      fillColor: "rgba(151,187,205,0.2)",
      strokeColor: "rgba(151,187,205,1)",
      pointColor: "rgba(151,187,205,1)",
      pointStrokeColor: "#fff",
      pointHighlightFill: "#fff",
      pointHighlightStroke: "rgba(151,187,205,1)",
      data: $('#data .y').map(function() { return $(this).text(); }).get()
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