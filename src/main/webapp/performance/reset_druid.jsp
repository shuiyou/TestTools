<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>重置durid</title>
<script>
	$(function() {
		function resetCheck() {
			if (document.getElementById('druid_url').value == "") {
				alert("请输入druid重置地址");
				document.getElementById('druid_url').focus();
				return false;
			}
			return true;
		}

		function reset() {
			var cdruid_url = $('#druid_url').val();
			$.ajax({
				url : '/TestTools/resetDruid',
				data : {
					druid_url : cdruid_url,
					_ : Math.random()
				},
				dataType : 'TEXT',
				type : 'POST',
				success : function(data) {
					$('#result').html(data);
				}
			});
		}

		$('#resetDruid_form').submit(function() {
			if (resetCheck()) {
				reset();
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
			<div style="width: 1200px">
				<div>
					<table class="table table-striped">
						<thead>
							<tr>
								<td><label class="col-sm-2 control-label">ResetDruid</label></td>
							</tr>
							<tr>
								<td><label class="col-sm-2 control-label">druid_url：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="druid_url" class="form-control"
											id="druid_url" placeholder="逗号分隔: url,url,url..."
											value="http://10.65.212.13:8100/voucher/druid/reset-all.json,http://10.65.212.19:8092/mas/druid/reset-all.json,http://10.65.212.13:8788/cashdesk-web/druid/reset-all.json,http://10.65.212.16:8904/cashdesk-api/druid/reset-all.json,http://10.65.212.20:8100/trade/druid/reset-all.json,http://10.65.212.11:8102/pfs-payment/druid/reset-all.json,http://10.65.212.14:8091/payment/druid/reset-all.json,http://10.65.212.18:8095/ma-web/druid/reset-all.json,http://10.65.212.16:8482/dpm-accounting/druid/reset-all.json,http://10.65.212.16:8483/dpm-manager/druid/reset-all.json,http://10.65.212.11:8581/druid/reset-all.json"
											style="width: 85%">
									</div>
								</td>
								<td>
									<form target="_blank" class="form-horizontal" role="form"
										id="resetDruid_form">
										<div class="col-sm-10">
											<input type="submit" value="重置" />
										</div>
									</form>
								</td>
							</tr>
						</thead>
					</table>
					<form target="_blank" class="form-horizontal" role="form"
						id="result_form">
						<div id="result" style='overflow: hidden; text-align: center'></div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div id="job_result"></div>
	<div id="container" style="min-width: 800px; height: 400px;"></div>
</body>
</html>