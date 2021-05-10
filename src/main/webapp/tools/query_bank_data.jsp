<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询银行数据</title>
<script>
	$(function() {
		function queryCheck() {
			if (document.getElementById('date').value == "") {
				alert("请输入日期");
				document.getElementById('date').focus();
				return false;
			}
			return true;
		}

		function query() {
			var cdate = $('#date').val();
			$.ajax({
				url : '/TestTools/queryBankData',
				data : {
					date : cdate,
					_ : Math.random()
				},
				dataType : 'TEXT',
				type : 'POST',
				success : function(data) {
					$('#result').html(data);
				}
			});
		}

		$('#queryByDate_form').submit(function() {
			if (queryCheck()) {
				query();
			}
			return false;
		});
	});
	
	$(function() {
		function queryCheck() {
			if (document.getElementById('bankOrderNo').value == "") {
				alert("请输银行订单号");
				document.getElementById('bankOrderNo').focus();
				return false;
			}
			return true;
		}

		function query() {
			var cbankOrderNo = $('#bankOrderNo').val();
			$.ajax({
				url : '/TestTools/queryBankData',
				data : {
					bankOrderNo : cbankOrderNo,
					_ : Math.random()
				},
				dataType : 'TEXT',
				type : 'POST',
				success : function(data) {
					$('#result').html(data);
				}
			});
		}

		$('#queryByBankOrderNo_form').submit(function() {
			if (queryCheck()) {
				query();
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
								<td><label class="col-sm-2 control-label">QueryBankData</label></td>
							</tr>
							<tr>
								<td><label class="col-sm-2 control-label">date：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="date" class="form-control" id="date"
											placeholder="查询日期, 格式:2016.07.26" value="">
									</div>
								</td>
								<td>
									<form target="_blank" class="form-horizontal" role="form"
										id="queryByDate_form">
										<div class="col-sm-10">
											<input type="submit" value="查询" />
										</div>
									</form>
								</td>
							</tr>
							<tr>
								<td><label class="col-sm-2 control-label">bankOrderNo：</label></td>
								<td>
									<div class="col-sm-10">
										<input type="text" name="bankOrderNo" class="form-control" id="bankOrderNo"
											placeholder="银行订单号" value="">
									</div>
								</td>
								<td>
									<form target="_blank" class="form-horizontal" role="form"
										id="queryByBankOrderNo_form">
										<div class="col-sm-10">
											<input type="submit" value="查询" />
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