<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>银行测试数据记录</title>
<script>
	$(function() {
		function recordCheck() {
			if ($('#fundType').val() == "none") {
				alert("请选择fund_type");
				document.getElementById('fundType').focus();
				return false;
			}
			if (document.getElementById('bankName').value == "") {
				alert("请输入银行名称");
				document.getElementById('bankName').focus();
				return false;
			}
			if (document.getElementById('channelCode').value == "") {
				alert("请输入渠道编码");
				document.getElementById('channelCode').focus();
				return false;
			}
			if (document.getElementById('executor').value == "") {
				alert("请输入操作人员姓名");
				document.getElementById('executor').focus();
				return false;
			}
			if (document.getElementById('orderAmount').value == "") {
				alert("请输入订单金额");
				document.getElementById('orderAmount').focus();
				return false;
			}
			if (document.getElementById('bankOrderNo').value == "") {
				alert("请输入银行订单号");
				document.getElementById('bankOrderNo').focus();
				return false;
			}
			if (document.getElementById('evnType').value == "") {
				alert("请输入环境");
				document.getElementById('evnType').focus();
				return false;
			}
			return true;
		}

		function record() {
			var cfundType = $('#fundType').val();
			var cbankName = $('#bankName').val();
			var cchannelCode = $('#channelCode').val();
			var cexecutor = $('#executor').val();
			var ctestTarget = $('#testTarget').val();
			var corderAmount = $('#orderAmount').val();
			var cbankOrderNo = $('#bankOrderNo').val();
			var corgFundinOrderNo = $('#orgFundinOrderNo').val();
			var cevnType = $('#evnType').val();
			$.ajax({
				url : '/TestTools/recordBankData',
				data : {
					fundType : cfundType,
					bankName : cbankName,
					channelCode : cchannelCode,
					executor : cexecutor,
					testTarget : ctestTarget,
					orderAmount : corderAmount,
					bankOrderNo : cbankOrderNo,
					orgFundinOrderNo : corgFundinOrderNo,
					evnType : cevnType,
					_ : Math.random()
				},
				dataType : 'TEXT',
				type : 'POST',
				success : function(data) {
					$('#result').html(data);
				}
			});
		}

		$('#record_form').submit(function() {
			if (recordCheck()) {
				record();
			}
			return false;
		});
	});
</script>
</head>
<body>
	<div style="position: relative; margin-top: 50PX; margin-bottom: 50PX"
		class="container">
		<div id="cleanCmember">
			<div style="width: 800px">
				<div>
					<table class="table table-striped">
						<thead>
							<tr>
								<td><label class="col-sm-2 control-label">fund_type:
								</label></td>
								<td>
									<div class="col-sm-10">
										<select name="fundType" class="form-control" id="fundType"
											onchange="show();">
											<option value="none">请选择</option>
											<option value="支付">支付</option>
											<option value="退款">退款</option>
											<option value="出款">出款</option>
										</select>
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td><label class="col-sm-2 control-label">bank_name：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="bankName" class="form-control"
											id="bankName" placeholder="银行名称: (e.g.) 招行,工行,农行..." value="">
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td><label class="col-sm-2 control-label">channel_code：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="channelCode" class="form-control"
											id="channelCode" placeholder="渠道编码: (e.g.) CMB10101..."
											value="">
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td><label class="col-sm-2 control-label">executor：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="executor" class="form-control"
											id="executor" placeholder="执行人员" value="">
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td><label class="col-sm-2 control-label">test_target：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="testTarget" class="form-control"
											id="testTarget" placeholder="测试目的" value="">
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td><label class="col-sm-2 control-label">order_amount：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="orderAmount" class="form-control"
											id="orderAmount" placeholder="订单金额" value="">
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td><label class="col-sm-2 control-label">bank_orderNo：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="bankOrderNo" class="form-control"
											id="bankOrderNo" placeholder="银行订单号" value="">
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td><label class="col-sm-2 control-label">org_fundin_orderNo：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="orgFundinOrderNo"
											class="form-control" id="orgFundinOrderNo"
											placeholder="原入款订单号" value="">
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td><label class="col-sm-2 control-label">env_type：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="evnType" class="form-control"
											id="evnType" placeholder="环境类型: (e.g.) 测试环境, 联调环境..."
											value="测试环境">
									</div>
								</td>
								<td></td>
							</tr>
							<tr>
								<td></td>
								<td>
									<form target="_blank" class="form-horizontal" role="form"
										id="record_form">
										<div class="col-sm-10">
											<input type="submit" value="保存" />
										</div>
									</form>
								</td>
							</tr>
						</thead>
					</table>
					<form target="_blank" class="form-horizontal" role="form"
						id="result_form">
						<div id="result"
							style='overflow: hidden; text-align: center'></div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="job_result"></div>
	<div id="container" style="min-width: 800px; height: 400px;"></div>
</body>
</html>