<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>清除会员信息</title>
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
				.open(
						"POST",
						"../clean?entity="
								+ encodeURIComponent(document
										.getElementById("entity").value)
								+ "&merchantId="
								+ encodeURIComponent(document
										.getElementById("merchantId").value)
								+ "&cleanType="
								+ encodeURIComponent(document
										.getElementById("selectCleanType").value),
						true);
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

	function check() {
		if (document.getElementById('entity').value == "") {
			alert("请输入UID,手机号或邮箱!");
			document.getElementById('entity').focus();
			return false;
		}
		if (document.getElementById('merchantId').value == "") {
			alert("请输入商户号");
			document.getElementById('merchantId').focus();
			return false;
		}
		if (document.getElementById('selectCleanType').value == "") {
			alert("请选择需要清除的信息!");
			document.getElementById('selectCleanType').focus();
			return false;
		}
		return true;
	}

	function clickcheck() {
		if (check()) {
			clean();
			return true;
		}
		return false;
	}
</script>
</head>
<body>
	<div style="position: relative; margin-top: 50PX; margin-bottom: 50PX"
		class="container">
		<div id="createMember">
			<div class="h4">清除会员信息</div>
			<div class="h6">注意:使用手机号或者邮箱名称来清除会员信息时,
				所有绑定了所输入的邮箱或手机的会员的相应的信息都会被清除, 若想防止误伤请使用UID进行会员信息数据清除!</div>
			<div style="width: 800px">
				<div>
					<form target="_blank" class="form-horizontal" role="form"
						name="login" action="../AdvanceHostingPay" method="post"
						accept-charset="utf-8" onsubmit="return checkUrl();">
						<table class="table table-striped">
							<thead>
								<tr>
									<th>
										<div class="col-sm-10">请输入查找条件</div>
									</th>
									<th>
										<div class="col-sm-10">请输入商户号</div>
									</th>
									<th>
										<div class="col-sm-10">请选择清除信息</div>
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
											<input type="text" name="entity" class="form-control"
												id="entity" placeholder="请输入UID,手机号或邮箱" value="">
										</div>
									</td>
									<td>
										<div class="col-sm-10">
											<input type="text" name="merchantId" class="form-control"
												id="merchantId" placeholder="请输入商户号" value="">
										</div>
									</td>
									<td>
										<div class="col-sm-10">
											<select id="selectCleanType" name="selectCleanType"
												class="form-control" onchange="show();">
												<option value="">请选择</option>
												<option value="MOBILE">手机</option>
												<option value="EMAIL">邮箱</option>
												<option value="REALNAME">实名信息</option>
												<option value="ALL">全部清除</option>
											</select>
										</div>
									</td>
									<td>
										<div class="col-sm-10">
											<input type="button" value="清除" onclick="clickcheck();" />
										</div>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
					<div id="showResult" style="text-align: center;"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>