Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';
  
var dom = document.getElementById('myBarChart');
var myChart = echarts.init(dom, null, {
  width : 700,
  height : 200,
  renderer: 'canvas',
  useDirtyRect: false
});
var app = {};

var option;

option = {
tooltip: {
trigger: 'axis',
axisPointer: {
  type: 'shadow'
}
},
legend: {
data: ['Profit', 'Income']
},
grid: {
left: '3%',
right: '4%',
bottom: '3%',
containLabel: true
},
xAxis: [
{
  type: 'value'
}
],
yAxis: [
{
  type: 'category',
  axisTick: {
    show: false
  },
  data: ['Mon']
}
],
series: [
{
  name: 'Profit',
  type: 'bar',
  label: {
    show: true,
    position: 'inside'
  },
  emphasis: {
    focus: 'series'
  },
  data: [200]
},
{
  name: 'Income',
  type: 'bar',
  stack: 'Total',
  label: {
    show: true
  },
  emphasis: {
    focus: 'series'
  },
  data: [320,]
},

]
};

if (option && typeof option === 'object') {
  myChart.setOption(option);
}

window.addEventListener('resize', myChart.resize);
