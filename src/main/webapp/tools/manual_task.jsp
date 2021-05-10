<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>手工触发task</title>
<script>
	$(function() {
		function recordCheck() {
			if (document.getElementById('hours').value == "") {
				alert("请输入时间");
				document.getElementById('hours').focus();
				return false;
			}
			return true;
		}

		function record() {
			var chours = $('#hours').val();
			$.ajax({
				url : '/TestTools/executeManualBankDataTask',
				data : {
					hours : chours,
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
								<td><label class="col-sm-2 control-label">ExecuteBankDataTaskManually</label></td>
							</tr>
							<div class="h6">注意: 启动任务以后请停留在该页面, 任务运行完毕后会返回结果.(10秒)</div>
							<tr>
								<td><label class="col-sm-2 control-label">hours(ago)：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="hours" class="form-control"
											id="hours" placeholder="需要重新发送几小时前的数据" value="">
									</div>
								</td>
								<td>
									<form target="_blank" class="form-horizontal" role="form"
										id="record_form">
										<div class="col-sm-10">
											<input type="submit" value="启动任务" />
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