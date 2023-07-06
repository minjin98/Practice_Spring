var dom = document.getElementById('myLineChart');
var myChart = echarts.init(dom, null, {
    
    width: 800,
    height: 230,
    renderer: 'canvas',
    useDirtyRect: false
});
var app = {};

var option;

option = {
xAxis: {
type: 'category',
data: ['Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat', 'Sun']
},
yAxis: {
type: 'value',
        min: 130,
        max: 110
        },

series: [
{
    data: [115,122,121,120,118],
    type: 'line'
    
}
]
};

if (option && typeof option === 'object') {
    myChart.setOption(option);
}

window.addEventListener('resize', myChart.resize);
