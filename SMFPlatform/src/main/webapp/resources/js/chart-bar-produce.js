function barChart2(jval){
	var title = new Array();
	var value = new Array();
	
	for(i = 0; i<8; i++){
		title = jval[i];
		value = jval[i+8];
		console.log("title : "+title);
		console.log("value : "+value);
		drawChart2(title,value);
	}
	
	
}
//-------------------------------------------------------------------
function drawChart2(title, value){
    var dom = document.getElementById('myBarChart2');
    var myChart = echarts.init(dom, null, {
    width : 700,
    height : 300,
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
  grid: {
    left: '3%',
    right: '4%',
    bottom: '3%',
    containLabel: true
  },
  xAxis: [
    {
      type: 'category',
	  data: [title],
      axisTick: {
        alignWithLabel: true
      }
    }
  ],
  yAxis: [
    {
      type: 'value'
    }
  ],
  series: [
    {
      name: 'Direct',
      type: 'bar',
      barWidth: '60%',
      data: [value]
    }
  ]
};

    if (option && typeof option === 'object') {
      myChart.setOption(option);
    }

    window.addEventListener('resize', myChart.resize);
}
    
//-------------------------------------------------------------------

function fn_chart3() {
	
	$.ajax({
		type:"post",
		async:false,  
		url:"http://localhost:8584/SMFPlatform/process3",
		success:function (data,textStatus) {
			var jsonVals = JSON.parse(data);
			// alert(jsonVals); 알림창 뜨기(넘어오는 값 확인할 때 좋을듯)
			barChart2(jsonVals);
		},
		error:function(data,textStatus){
  			alert("에러발생: " + data);
		},
		complete:function(data,textStatus){
  			// alert("수신완료");
		}
	});	 
}
//-------------------------------------------------------------------
