Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

 function barChart(vals){
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
	data: ['leadtime', 'cycletime']
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
	  data: ['Time']
	}
	],
	series: [
	{
	  name: 'leadtime',
	  type: 'bar',
	  label: {
	    show: true,
	    position: 'inside'
	  },
	  emphasis: {
	    focus: 'series'
	  },
	  data: [vals[0]]
	},
	{
	  name: 'cycletime',
	  type: 'bar',
	  stack: 'Total',
	  label: {
	    show: true
	  },
	  emphasis: {
	    focus: 'series'
	  },
	  data: [vals[1]]
	},
	
	]
	};
	
	if (option && typeof option === 'object') {
	  myChart.setOption(option);
	}
	
	window.addEventListener('resize', myChart.resize);
}
//-------------------------------------------------------------------
function fn_chart2() {
	
	$.ajax({
		type:"post",
		async:false,  
		url:"http://localhost:8584/SMFPlatform/process2",
		success:function (data,textStatus) {
			var jsonVals = JSON.parse(data);
			// alert(jsonVals);
			alert(jsonVals[1]);
			barChart(jsonVals);
		},
		error:function(data,textStatus){
  			alert("에러발생: " + data);
		},
		complete:function(data,textStatus){
  			// alert("수신완료");
		}
	});	 
}