<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>接口返回结果</title>
</head>
<body>

<div style="position: relative;margin-top: 60PX;margin-bottom: 50PX" class="container">
  <table class="table table-bordered" cellpadding="5" align="center" style="word-break:break-all; word-wrap:break-all;">
    <tr>
        <th class="table table-striped">请求报文:<%=request.getAttribute("request")%></th>
    </tr>
    <tr>
        <th class="table table-striped" id="result">返回结果:<%=request.getAttribute("response")%></th>
    </tr>
  </table>
</div>
</body>
</html>