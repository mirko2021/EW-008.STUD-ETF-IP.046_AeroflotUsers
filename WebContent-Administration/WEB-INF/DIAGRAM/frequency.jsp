<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<jsp:useBean id='LoginCounterBean' class='yatospace.session.counter.bean.LoginCounterBean' scope='request'></jsp:useBean>

<br>
<canvas id="canvas" height="450" width="600"></canvas>
<script>

	var barChartData = {
		labels : ${LoginCounterBean.getTimeJsonHeader()},
		datasets : [
			{
				fillColor : "rgba(220,220,220,0.5)",
				strokeColor : "rgba(220,220,220,1)",
				data :  ${LoginCounterBean.getFrecvenceJsonHeader()}
			}
		]
	}

	var myLine = new Chart(document.getElementById("canvas").getContext("2d")).Bar(barChartData);
</script>