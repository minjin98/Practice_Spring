Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';
	
	var dom = document.getElementById('myGaugeChart');  
	var myChart = echarts.init(dom, null, {
	    width: 330,
	    height: 230,
	    renderer: 'canvas',
	    useDirtyRect: false
	});
	
	var app = {};
	
	var option;
	
	option = {
	series: [
	{
	    type: 'gauge',
	    axisLine: {
	    lineStyle: {
	        width: 20,
	        color: [
	        [0.3, '#67e0e3'],
	        [0.7, '#37a2da'],
	        [1, '#fd666d']
	        ]
	    }
	    },
	    pointer: {
	    itemStyle: {
	        color: 'auto'
	    }
	    },
	    axisTick: {
	    distance: -20,
	    length: 6,
	    lineStyle: {
	        color: '#fff',
	        width: 2
	    }
	    },
	    splitLine: {
	    distance: -20,
	    length: 20,
	    lineStyle: {
	        color: '#fff',
	        width: 2
	    }
	    },
	    axisLabel: {
	    color: 'inherit',
	    distance: 30,
	    fontSize: 0
	    },
	    detail: {
	    valueAnimation: true,
	    formatter: '{value}%',
	    color: 'inherit'
	    },
	    data: [
	    {
	        value: 70
	    }
	    ]
	}
	]
	};
	
	if (option && typeof option === 'object') {
	    myChart.setOption(option);
	}
	window.addEventListener('resize', myChart.resize);
