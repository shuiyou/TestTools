<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>清除会员</title>
<script language="javascript">
	var xmlHttp;
	function createAjax() {
		//分浏览器创建XMLHttp对象
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")
		}
	}

	function clean() {
		createAjax();
		//设置请求类型
		xmlHttp
				.open("POST", "../cleanMember?memberId="
						+ encodeURIComponent(document
								.getElementById("memberId").value), true);
		//回调函数
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					document.getElementById("showResult").innerHTML = xmlHttp.responseText;
				} else {
					alert("AJAX服务器返回错误！");
				}
			}
		}
		//发送请求
		xmlHttp.send();
	}
	
	function check(){
		if (document.getElementById('memberId').value == ""){
			alert("请输入需要删除的会员member_id");
			document.getElementById('memberId').focus();
			return false;
		} else {
			clean();
		}
	}

	
</script>
</head>
<body>
	<div style="position: relative; margin-top: 50PX; margin-bottom: 50PX"
		class="container">
		<div id="createMember">
			<div class="h4">清除会员</div>
			<div class="h6">注意:使用member_id进行会员彻底清除,包括所绑定的邮箱,手机以及实名信息</div>
			<div style="width: 800px">
				<div>
					<form target="_blank" class="form-horizontal" role="form"
						name="login" action="../AdvanceHostingPay" method="post"
						accept-charset="utf-8" onsubmit="return checkUrl();">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>
										<div class="col-sm-10">请输入member_id</div>
									</th>
									<th>
										<div class="col-sm-10">按钮</div>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>
										<div class="col-sm-10">
											<input type="text" name="memberId" class="form-control"
												id="memberId" placeholder="请输入member_id" value="">
										</div>
									</td>
									<td>
										<div class="col-sm-10">
											<input type="button" value="清除" onclick="check();"/>
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
					<div id="showResult" style="text-align:center;"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>