<%--
  Created by IntelliJ IDEA.
  User: hanxiaodi
  Date: 18/11/5
  Time: 下午4:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>创建内部户</title>
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

        function createInneraccount() {
            createAjax();
            //设置请求类型
            xmlHttp
                    .open("POST", "../insertInnerAccount?accountName="
                            + encodeURIComponent(document
                                    .getElementById("accountName").value)+"&accountTitleNo="
                            + encodeURIComponent(document.getElementById("accountTitleNo").value)+"&currency="
                            + encodeURIComponent(document.getElementById("currency").value), true);
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



    </script>
</head>
<body>
<div style="position: relative; margin-top: 50PX; margin-bottom: 50PX"
     class="container">
    <div id="createMember">
        <div class="h4">创建dpm中间户</div>
        <div class="h6">注意:调用dpm接口创建商户内部户</div>
        <div style="width: 800px">
            <div>
                <form target="_blank" class="form-horizontal" role="form"
                      name="login" action="../AdvanceHostingPay" method="post"
                      accept-charset="utf-8" onsubmit="return checkUrl();">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>
                                <div class="col-sm-10">请输入accountName</div>
                            </th>
                            <th>
                                <div class="col-sm-10">请输入accountTitleNo</div>
                            </th>
                            <th>
                                <div class="col-sm-10">请输入currency(默认CNY)</div>
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
                                    <input type="text" name="accountName" class="form-control"
                                           id="accountName" placeholder="请输入accountName" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="accountTitleNo" class="form-control"
                                           id="accountTitleNo" placeholder="请输入accountTitleNo" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="currency" class="form-control"
                                           id="currency" placeholder="请输入currency(默认CNY)" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="button" value="创建内部户" onclick="createInneraccount();"/>
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
