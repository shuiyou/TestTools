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
    <table>
        <tr>
            <th class="table table-striped">请求报文:<%=request.getAttribute("request")%></th>
        </tr>
        <tr>
            <td class="button_ellipse" onClick="javascript:document.getElementById('frmBankID').submit();"><img src="/BOSP/images/icon/add.gif" class="div_control_image">提交</td>
        </tr>
    </table>

    <%=request.getAttribute("response")%>

</div>
</body>
</html>