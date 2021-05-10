<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>加解密工具</title>
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

	function encrypt() {
		createAjax();
		//设置请求类型
		xmlHttp.open("POST",
				"../encrypt?encryptContent="
						+ encodeURIComponent(document
								.getElementById("encryptContent").value), true);
		//回调函数
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					document.getElementById("showEncryptContent").innerHTML = xmlHttp.responseText;
				} else {
					alert("AJAX服务器返回错误！");
				}
			}
		}
		//发送请求
		xmlHttp.send();
	}

	function decrypt() {
		createAjax();
		//设置请求类型
		xmlHttp.open("POST",
				"../decrypt?decryptContent="
						+ encodeURIComponent(document
								.getElementById("decryptContent").value), true);
		//回调函数
		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					document.getElementById("shwoDecryptContent").innerHTML = xmlHttp.responseText;
				} else {
					alert("AJAX服务器返回错误！");
				}
			}
		}
		//发送请求
		xmlHttp.send();
	}
	
	function showEncryptKey() {
		if (document.getElementById("encryptCheckbox").checked) {
			document.getElementById("encryptKey").style.display="block";
		} else {
			document.getElementById("encryptKey").style.display="none";
			document.getElementById("encryptKey").value="";
		}
	}
	
	function showDecryptKey() {
		if (document.getElementById("decryptCheckbox").checked) {
			document.getElementById("decryptKey").style.display="block";
		} else {
			document.getElementById("decryptKey").style.display="none";
			document.getElementById("decryptKey").value="";
		}
	}
</script>
</head>
<body>
	<div style="position: relative; margin-top: 60PX; margin-bottom: 50PX"
		class="container">
		<table class="table table-striped" cellpadding="5"
			style="word-break: break-all; word-wrap: break-all;">
			<tr>
				<th class="table table-striped"><input tpye="text"
					name="encryptContent" id="encryptContent" placeholder="请输入需要加密的内容"
					value="" style="width: 90%"> <input type="button"
					value="加密" onclick="encrypt()"></th>
			</tr>
			<tr>
				<th class="table table-striped">自定义加密私钥 <input type="checkbox" id="encryptCheckbox" name="encryptCheckbox" onclick="showEncryptKey()"/> </th>
			</tr>
			<tr>
				<th class="table table-striped"><input tpye="text"
					name="encryptKey" id="encryptKey" placeholder="请输入自定义加密私钥"
					value="" style="width: 90%; display: none"> </th>
			</tr>
			<tr>
				<th id="showEncryptContent" class="table table-striped"></th>
			</tr>
			<tr>
				<th class="table table-striped"><input tpye="text"
					name="decryptContent" id="decryptContent" placeholder="请输入需要解密的内容"
					value="" style="width: 90%"> <input type="button"
					value="解密" onclick="decrypt()"></th>
			</tr>
			<tr>
				<th class="table table-striped">自定义解密公钥 <input type="checkbox" id="decryptCheckbox" name="decryptCheckbox" onclick="showDecryptKey()"/> </th>
			</tr>
			<tr>
				<th class="table table-striped"><input tpye="text"
					name="decryptKey" id="decryptKey" placeholder="请输入自定义解密公钥"
					value="" style="width: 90%; display: none"> </th>
			</tr>
			<tr>
				<th id="shwoDecryptContent" class="table table-striped"></th>
			</tr>
		</table>
	</div>
</body>
</html>