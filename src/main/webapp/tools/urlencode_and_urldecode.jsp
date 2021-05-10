<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>URL编码工具</title>
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
	
	function encode(){
		createAjax();
		//设置请求类型
		xmlHttp.open("POST", "../encode?encodeContent="
				+ encodeURIComponent(document.getElementById("encodeContent").value), true);
		//回调函数
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					document.getElementById("showEncodeContent").innerHTML = xmlHttp.responseText;
				} else {
					alert("AJAX服务器返回错误！");
				}
			}
		}
		//发送请求
		xmlHttp.send();
	}
	
	function decode(){
		createAjax();
		//设置请求类型
		xmlHttp.open("POST", "../decode?decodeContent="
				+ encodeURIComponent(document.getElementById("decodeContent").value), true);
		//回调函数
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					document.getElementById("shwoDecodeContent").innerHTML = xmlHttp.responseText;
				} else {
					alert("AJAX服务器返回错误！");
				}
			}
		}
		//发送请求
		xmlHttp.send();
	}
</script>
</head>
<body>
	<div style="position: relative; margin-top: 60PX; margin-bottom: 50PX"
		class="container">
		<table class="table table-striped" cellpadding="5" align="center"
			style="word-break: break-all; word-wrap: break-all;">
			<tr>
				<th class="table table-striped"><input tpye="text"
					name="encodeContent" id="encodeContent" placeholder="请输入需要编码的内容"
					value="" style = "width:85%"> <input type="button" value="UrlEncode编码"
					onclick="encode()"></th>
			</tr>
			<tr>
				<th id="showEncodeContent" class="table table-striped"></th>
			</tr>
			<tr>
				<th class="table table-striped"><input tpye="text"
					name="decodeContent" id="decodeContent" placeholder="请输入需要解码的内容"
					value="" style = "width:85%"> <input type="button" value="UrlDecode解码"
					onclick="decode()"></th>
			</tr>
			<tr>
				<th id="shwoDecodeContent" class="table table-striped"></th>
			</tr>
		</table>
	</div>
</body>
</html>