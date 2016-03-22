Chart.types.Line.extend({
    name: "LineAlt",
    draw: function () {
        Chart.types.Line.prototype.draw.apply(this, arguments);

        var ctx = this.chart.ctx;
        ctx.save();
        // text alignment and color
        ctx.textAlign = "center";
        ctx.textBaseline = "bottom";
        ctx.fillStyle = this.options.scaleFontColor;
        // position
        var x = this.scale.xScalePaddingLeft * 0.4;
        var y = this.chart.height / 2;
        // change origin
        ctx.translate(x, y)
        // rotate text
        ctx.rotate(-90 * Math.PI / 180);
        ctx.fillText(this.datasets[0].label, 0, 0);
        ctx.restore();
    }
});



var data = {
    //labels: ["v1.0", "v1.1", "v1.2", "v1.3", "v2.1", "v2.2", "v2.3"],
    labels: [],
    datasets: [
        {
            label: "My Second dataset",
            fillColor: "rgba(151,187,205,0.2)",
            strokeColor: "rgba(151,187,205,1)",
            pointColor: "rgba(151,187,205,1)",
            pointStrokeColor: "#fff",
            pointHighlightFill: "#fff",
            pointHighlightStroke: "rgba(151,187,205,1)",
            //data: [28, 48, 40, 45, 53, 58, 52]
            data: [0]
        }
    ]
};

window.onload = function(){
    var myLineChart = document.getElementById("canvas").getContext("2d");
    window.myLine = new Chart(myLineChart).Line(data, {responsive: true,
    scaleLabel: function(f){
        return f.value + 'ms';
    }
    });
    myLineChart.generateLegend("dssdds");
}