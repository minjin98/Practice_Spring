// Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// Pie Chart Example
function pieChart(vals){
	var ctx = document.getElementById("myPieChart");
	var myPieChart = new Chart(ctx, {
	  type: 'pie',
	  data: {
	    labels: ["Blue", "Red"],
	    datasets: [{
	      data: vals,
	      backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745'],
	    }],
	  },
	});
}
//-------------------------------------------------------------------
function fn_chart1() {
	
	$.ajax({
		type:"post",
		async:false,  
		url:"http://localhost:8584/SMFPlatform/process1",
		success:function (data,textStatus) {
			var jsonVals = JSON.parse(data);
			// alert(jsonVals);
			pieChart(jsonVals);
		},
		error:function(data,textStatus){
  			alert("에러발생: " + data);
		},
		complete:function(data,textStatus){
  			// alert("수신완료");
		}
	});	 
}