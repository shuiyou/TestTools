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
<title>CMF表订单落地统计</title>
<script>
	$(function() {
		function check() {
			if (document.getElementById('payMode').value == "") {
				alert("请输入支付类型");
				document.getElementById('payMode').focus();
				return false;
			}
			if (document.getElementById('instCode').value == "") {
				alert("请输机构代码");
				document.getElementById('instCode').focus();
				return false;
			}
			if (document.getElementById('orderType').value == "") {
				alert("请输入订单类型");
				document.getElementById('orderType').focus();
				return false;
			}
			if (document.getElementById('productCode').value == "") {
				alert("请输入产品码");
				document.getElementById('productCode').focus();
				return false;
			}
			if (document.getElementById('paymentCode').value == "") {
				alert("请输入支付码");
				document.getElementById('paymentCode').focus();
				return false;
			}
			return true;
		}

		function query() {
			var stime = $('#startTime').val();
			var etime = $('#endTime').val();
			var pMode = $('#payMode').val();
			var iCode = $('#instCode').val();
			var oType = $('#orderType').val();
			var proCode = $('#productCode').val();
			var payCode = $('#paymentCode').val();
			console.log(stime, etime);
			$.ajax({
				url : '/TestTools/collectCMF',
				data : {
					startTime : stime,
					endTime : etime,
					payMode: pMode,
					instCode: iCode,
					orderType: oType,
					productCode: proCode,
					paymentCode: payCode,
					_ : Math.random()
				},
				dataType : 'JSON',
				type : 'GET',
				success : function(data) {
					console.log(data);
					$('#container').highcharts({
						title : {
							text : 'TT_CMF_ORDER Data Collection',
							x : -20
						//center
						},
						subtitle : {
							text : 'TT_CMF_ORDER表每秒落地订单统计',
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
			<div class="h4">TT_CMF_ORDER表订单落地情况查询</div>
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
									<td><label class="col-sm-2 control-label">payMode：</label></td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="payMode" class="form-control"
												id="payMode" placeholder="支付类型" value="SAVING_POT">
										</div>
									</td>
									<td></td>
								</tr>
								<tr>
									<td><label class="col-sm-2 control-label">instCode：</label></td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="instCode" class="form-control"
												id="instCode" placeholder="机构代码" value="SPT">
										</div>
									</td>
									<td></td>
								</tr>
								<tr>
									<td><label class="col-sm-2 control-label">orderType：</label></td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="orderType" class="form-control"
												id="orderType" placeholder="订单类型" value="I">
										</div>
									</td>
									<td></td>
								</tr>
								<tr>
									<td><label class="col-sm-2 control-label">productCode：</label></td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="productCode" class="form-control"
												id="productCode" placeholder="产品码" value="20040010">
										</div>
									</td>
									<td></td>
								</tr>
								<tr>
									<td><label class="col-sm-2 control-label">paymentCode：</label></td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="paymentCode" class="form-control"
												id="paymentCode" placeholder="支付码" value="1001">
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