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
<title>云金融会员一键清除</title>
<script>
	$(function() {
		function queryCheck() {
			if (document.getElementById('partnerId').value == "") {
				alert("请输入云金融商户号");
				document.getElementById('partnerId').focus();
				return false;
			}
			if (document.getElementById('mobile').value == "") {
				alert("请输入手机号");
				document.getElementById('mobile').focus();
				return false;
			}
			if ($('#env').val() == "none") {
				alert("请选择环境");
				document.getElementById('env').focus();
				return false;
			}
			return true;
		}

		function query() {
			var cpartnerId = $('#partnerId').val();
			var cmobile = $('#mobile').val();
			var envdata = $('#env').val();
			console.log(cpartnerId, cmobile, envdata);
			$.ajax({
				url : '/TestTools/queryCmember',
				data : {
					partnerId : cpartnerId,
					mobile : cmobile,
					env : envdata,
					_ : Math.random()
				},
				dataType : 'TEXT',
				type : 'GET',
				success : function(data) {
					$('#cmemberId').attr('value', data);
				}
			});
		}

		$('#query_form').submit(function() {
			if (queryCheck()) {
				query();
			}
			return false;
		});
	});
</script>

<script>
	$(function() {
		function cleanCheck() {
			if (document.getElementById('cmemberId').value == "") {
				alert("请输入云金融会员号");
				document.getElementById('cmemberId').focus();
				return false;
			}
			if ($('#env').val() == "none") {
				alert("请选择环境");
				document.getElementById('env').focus();
				return false;
			}
			return true;
		}

		function clean() {
			var cmid = $('#cmemberId').val();
			var envdata = $('#env').val();
			console.log(cmid, env);
			$.ajax({
				url : '/TestTools/cleanCmember',
				data : {
					cmemberId : cmid,
					env : envdata,
					_ : Math.random()
				},
				dataType : 'TEXT',
				type : 'GET',
				success : function(data) {
					$('#result').html(data);
				}
			});
		}

		$('#clean_form').submit(function() {
			if (cleanCheck()) {
				clean();
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
									<td><label class="col-sm-2 control-label">env: </label></td>
									<td>
										<div class="col-sm-10">
											<select name="env" class="form-control" id="env"
												onchange="show();">
												<option value="none">请选择环境</option>
												<option value="func">测试环境</option>
												<option value="test">联调环境</option>
											</select>
										</div>
									</td>
									<td></td>
								</tr>
								<tr>
									<td><label class="col-sm-2 control-label">partner_id：</label></td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="partnerId" class="form-control"
												id="partnerId" placeholder="云金融商户号" value="">
										</div>
									</td>
									<td></td>
								</tr>
								<tr>
									<td><label class="col-sm-2 control-label">mobile：</label></td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="mobile" class="form-control"
												id="mobile" placeholder="手机号" value="">
										</div>
									</td>
									<td>
										<form target="_blank" class="form-horizontal" role="form"
											id="query_form">
											<div class="col-sm-10">
												<input type="submit" value="查询" />
											</div>
										</form>
									</td>
								</tr>
								<tr>
									<td><label class="col-sm-2 control-label">cmember_id：</label></td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="cmemberId" class="form-control"
												id="cmemberId" placeholder="云金融会员号" value="">
										</div>
									</td>
									<td>
										<form target="_blank" class="form-horizontal" role="form"
											id="clean_form">
											<div class="col-sm-10">
												<input type="submit" value="清除" />
											</div>
										</form>
									</td>
								</tr>
							</thead>
						</table>
					<form target="_blank" class="form-horizontal" role="form"
						id="result_form">
						<div id="result"
							style='height: 105px; line-height: 105px; overflow: hidden; text-align: center'></div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="job_result"></div>
	<div id="container" style="min-width: 800px; height: 400px;"></div>
</body>
</html>