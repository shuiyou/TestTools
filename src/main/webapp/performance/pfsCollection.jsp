<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript"
	src="http://cdn.hcharts.cn/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript"
	src="http://cdn.hcharts.cn/highcharts/highcharts.js"></script>
<script type="text/javascript"
	src="http://cdn.hcharts.cn/highstock/highstock.js"></script>
<script type="text/javascript"
	src="http://cdn.hcharts.cn/highmaps/highmaps.js"></script>
<title>PFS表订单落地统计</title>
<script>
	$(function() {
		function check() {
			if (document.getElementById('bizCode').value == "") {
				alert("请输入业务类型");
				document.getElementById('bizCode').focus();
				return false;
			}
			return true;
		}

		function query() {
			var stime = $('#startTime').val();
			var etime = $('#endTime').val();
			var bCode = $('#bizCode').val();
			console.log(stime, etime);
			$.ajax({
				url : '/TestTools/collectPFS',
				data : {
					startTime : stime,
					endTime : etime,
					bizCode: bCode,
					_ : Math.random()
				},
				dataType : 'JSON',
				type : 'GET',
				success : function(data) {
					console.log(data);
					$('#container').highcharts({
						title : {
							text : 'TB_BIZ_PAYMENT_ORDER Data Collection',
							x : -20
						//center
						},
						subtitle : {
							text : 'TB_BIZ_PAYMENT_ORDER表每秒落地订单统计',
							x : -20
						},
						xAxis : {
							categories : data.timeList
						},
						yAxis : {
							title : {
								text : 'Orders/per second'
							},
							plotLines : [ {
								value : 0,
								width : 1,
								color : '#808080'
							} ]
						},
						tooltip : {
							valueSuffix : '条'
						},
						legend : {
							layout : 'vertical',
							align : 'right',
							verticalAlign : 'middle',
							borderWidth : 0
						},
						series : [ {
							name : '每秒订单落地数',
							data : data.countList
						} ]
					});
				}
			});
		}

		$('#check_form').submit(function() {
			if (check()) {
				query();
			}
			return false;
		});
	});
</script>
<script>
$(function() {
	
	function start_job() {
		var action = $('#start_action').val();
		console.log(action);
		$.ajax({
			url: '/TestTools/hostingCollectTradeCollection',
			data: {
				action: action,
				_: Math.random()
			},
		dataType: 'TEXT',
		type : 'GET',
		success : function(data) {
			console.log(data)
		}
		});
		return true;
	}
	
	$('#job_start').submit(function() {
		if (start_job()) {
			return false;
		}
	});
});
</script>

<script>
$(function() {
	
	function stop_job() {
		var action = $('#stop_action').val();
		console.log(action);
		$.ajax({
			url: '/TestTools/hostingCollectTradeCollection',
			data: {
				action: action,
				_: Math.random()
			},
		dataType: 'TEXT',
		type : 'GET',
		success : function(data) {
			console.log(data)
		}
		});
		return true;
	}
	
	$('#job_stop').submit(function() {
		if (stop_job()) {
			return false;
		}
	});
});
</script>

</head>
<body>
	<div style="position: relative; margin-top: 50PX; margin-bottom: 50PX"
		class="container">
		<div id="tradeOrderCollection">
			<div class="h4">TB_BIZ_PAYMENT_ORDER表订单落地情况查询</div>
			<div class="h6">注意: 业务类型必填, 例如托管代收就填301003</div>
			<div class="h6">注意: 起始时间和截止时间请按照20150101010101的格式来输入</div>
			<div class="h6">注意: 起始时间和截止时间都不填, 或其中之一不填,则查询当前时间前1分钟的订单落地情况, 此功能方便压力测试时的快速查询.</div>
			<div style="width: 800px">
				<div>

					<!--<form target="_blank" class="form-horizontal" role="form"
						id="job_start">
						<input type="text" name="start_action" id="start_action"
							value="start" style="display: none" /> <input type="submit"
							value="开始数据采集" />
					</form>

					<form target="_blank" class="form-horizontal" role="form"
						id="job_stop">
						<input type="text" name="stop_action" id="stop_action"
							value="stop" style="display: none" /> <input type="submit"
							value="停止数据采集" />
					</form>-->

					<form target="_blank" class="form-horizontal" role="form"
						id="check_form">
						<table class="table table-striped">
							<thead>
								<tr>
									<td><label class="col-sm-2 control-label">startTime：</label></td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="startTime" class="form-control"
												id="startTime" placeholder="起始时间" value="">
										</div>
									</td>
									<td></td>
								</tr>
								<tr>
									<td><label class="col-sm-2 control-label">endTime：</label></td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="endTime" class="form-control"
												id="endTime" placeholder="截止时间" value="">
										</div>
									</td>
									<td></td>
								</tr>
								<tr>
									<td><label class="col-sm-2 control-label">bizCode：</label></td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="bizCode" class="form-control"
												id="bizCode" placeholder="业务类型" value="301003">
										</div>
									</td>
									<td></td>
								</tr>
								<tr>
									<td></td>
									<td>
										<div class="col-sm-10">
											<input type="submit" value="提交查询" />(不填起始与截止时间直接提交则自动查询60内的落地情况)
										</div>
									</td>
									<td></td>
								</tr>
							</thead>
						</table>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="job_result"></div>
	<div id="container" style="min-width: 800px; height: 400px;"></div>
</body>
</html>