<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/11/15
  Time: 15:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>重置密码</title>
    <script type="text/javascript">
        function show(){
            var select = document.getElementById('selectEnv');
            var address = document.getElementById('url');
            switch(select.value){
                case 'func':
                    address.value='http://10.65.209.17:9100/wexwallet/gateway.do';
                    break;
                case 'test':
                    address.value='http://10.65.209.17:9100/wexwallet/gateway.do';
                    break;
                case 'real':
                    address.value='';
                    break;
                default:
                    address.value='';
                    break;
            }
        }

        function selectPlatform(){
            var select = document.getElementById('selectPlatform');
            var platform = document.getElementById('platform');
            switch(select.value){
                case 'IOS':
                    platform.value='1';
                    break;
                case 'ANDROID':
                    platform.value='2';
                    break;
                default:
                    platform.value='';
                    break;
            }
        }

        function setRequestTime(){
            var requestTime = document.getElementById('request_time');
            requestTime.value = new Date().Format('yyyyMMddHHmmss');
        }

        function setUid(){
            var requestTime = document.getElementById('identity_id');
            requestTime.value = new Date().Format('MMddHHmmss');
        }

        function checkUrl(){
            if (document.getElementById('url').value == ""){
                alert("请选择请求环境!");
                document.getElementById('selectEnv').focus();
                return false;
            }
        }

        Date.prototype.Format = function(formatStr)
        {
            var str = formatStr;
            var Week = ['日','一','二','三','四','五','六'];

            str=str.replace(/yyyy|YYYY/,this.getFullYear());
            str=str.replace(/yy|YY/,(this.getYear() % 100)>9?(this.getYear() % 100).toString():'0' + (this.getYear() % 100));

            str=str.replace(/MM/,this.getMonth()+1>9?(this.getMonth()+1).toString():'0' + (this.getMonth()+1));
            str=str.replace(/M/g,this.getMonth());

            str=str.replace(/w|W/g,Week[this.getDay()]);

            str=str.replace(/dd|DD/,this.getDate()>9?this.getDate().toString():'0' + this.getDate());
            str=str.replace(/d|D/g,this.getDate());

            str=str.replace(/hh|HH/,this.getHours()>9?this.getHours().toString():'0' + this.getHours());
            str=str.replace(/h|H/g,this.getHours());
            str=str.replace(/mm/,this.getMinutes()>9?this.getMinutes().toString():'0' + this.getMinutes());
            str=str.replace(/m/g,this.getMinutes());

            str=str.replace(/ss|SS/,this.getSeconds()>9?this.getSeconds().toString():'0' + this.getSeconds());
            str=str.replace(/s|S/g,this.getSeconds());

            return str;
        }
    </script>
</head>
<body>
<div style="position: relative;margin-top: 50PX;margin-bottom: 50PX" class="container">
    <div id="LoginResetPasswordReset">
        <div class="h4" >重置密码</div>
        <div style="width: 800px">
            <div>
                <form target="_blank" class="form-horizontal" role="form" name="login" action="../LoginResetPasswordReset" method="post" accept-charset="utf-8" onsubmit="return checkUrl();">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th>
                                <div class="col-sm-10">参数名称</div>
                            </th>
                            <th>
                                <div class="col-sm-10">参数值</div>
                            </th>
                            <th>
                                <div class="col-sm-10">按钮</div>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td><label class="col-sm-2 control-label">env: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="url" class="form-control" id="url" placeholder="请求接口地址" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <select id="selectEnv" class="form-control" onchange="show();">
                                        <option>请选择</option>
                                        <option value="func">测试环境</option>
                                        <option value="test">联调环境</option>
                                        <option value="real">生产环境</option>
                                    </select>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">平台类型: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="platform" class="form-control" id="platform" placeholder="平台" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <select id="selectPlatform" class="form-control" onchange="selectPlatform();">
                                        <option>请选择</option>
                                        <option value="IOS">IOS平台</option>
                                        <option value="ANDROID">安卓平台</option>
                                    </select>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">version: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="version" class="form-control" id="version" placeholder="接口版本" value="1.0.0">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label" >service</label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="service" class="form-control" id="service"  placeholder="接口名称" value="Login.ResetPassword.Reset">
                                </div>
                            </td>
                            <td></td>
                        </tr>



                        <tr>
                            <td><label class="col-sm-2 control-label">request_time: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="request_time" class="form-control" id="request_time" placeholder="请求时间">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="button" value="生成请求时间" onclick="setRequestTime()"/>
                                </div>
                            </td>
                        </tr>



                        <tr>
                            <td><label class="col-sm-2 control-label">lang: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="lang" class="form-control" id="lang" placeholder="文案语言" value="CN">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">mobile: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="mobile" class="form-control" id="mobile" placeholder="登录手机号" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">reset_token: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="reset_token" class="form-control" id="reset_token" placeholder="重置令牌" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">device_identity: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="device_identity" class="form-control" id="device_identity" placeholder="唯一标识用户设备" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">salt_id: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="salt_id" class="form-control" id="salt_id" placeholder="盐id" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">salt: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="salt" class="form-control" id="salt" placeholder="盐" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">password: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="password" class="form-control" id="password" placeholder="登陆密码" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>



                        <tr>
                            <td></td>
                            <td>
                                <div class="col-sm-10">
                                    <button type="submit" class = "btn btn-primary" style="width: 280px;text-align: center">发送请求</button>
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
