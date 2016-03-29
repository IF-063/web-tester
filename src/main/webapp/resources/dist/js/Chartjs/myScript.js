$(function() {

  var sla = $('.sla').text();

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
      data: $('#data .y').map(function() { return $(this).text(); }).get(),
      borderColor: '#66f',
      borderDash: [20, 30]
    }]
  };

  var ctx = document.getElementById("canvas").getContext("2d");

  Chart.types.Line.extend({
    name: "LineWithLine",
    draw: function () {
      Chart.types.Line.prototype.draw.apply(this, arguments);
      var scale = this.scale;
      var xStart = Math.round(this.scale.xScalePaddingLeft);
      var linePositionY = this.scale.calculateY(sla);

      // draw line
      this.chart.ctx.beginPath();
      this.chart.ctx.moveTo(xStart + 5, linePositionY);
      this.chart.ctx.strokeStyle = '#ff0000';
      this.chart.ctx.lineTo(this.scale.width, linePositionY);
      this.chart.ctx.stroke();

      // write SLA
      this.chart.ctx.textAlign = 'left';
      this.chart.ctx.fillText("SLA", scale.startPoint + 60, linePositionY+20);

      // write SLA = sla ms
      this.chart.ctx.textAlign = 'left';
      this.chart.ctx.fillText("SLA = "+sla+"ms", scale.startPoint+linePositionY-linePositionY + 60, 20);
    }
  });

  new Chart(ctx).LineWithLine(data, {
    responsive: true,
    scaleLabel: function(f) {
      return f.value + 'ms';
    },
    datasetFill : false
  });
});
