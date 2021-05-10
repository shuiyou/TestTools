<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>照片加密</title>
    <script language="JavaScript">
        var xmlHttp;
        function createAjax() {
            //分浏览器创建XMLHttp对象
            if (window.XMLHttpRequest) {
                xmlHttp = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")
            }
        }

        function encryptIamge() {
            createAjax();
            //设置请求类型
            xmlHttp.open("POST",
                    "../encryptImage?encryptImageContent="
                    + encodeURIComponent(document
                            .getElementById("encryptImageContent").value)+"&encryptkey="
                    + encodeURIComponent(document.getElementById("encryptkey").value), true);
            //回调函数
            xmlHttp.onreadystatechange = function() {
                if (xmlHttp.readyState == 4) {
                    if (xmlHttp.status == 200) {
                        document.getElementById("showEncryptImageContent").innerHTML = xmlHttp.responseText;
                    } else {
                        alert("AJAX服务器返回错误！");
                    }
                }
            }
            //发送请求
            xmlHttp.send();
        }

        function optimizeEncryptImage() {
            createAjax();
            //设置请求参数
            xmlHttp.open("POST",
                    "../optimizeEncryptImage?encryptImageContent="
                    + encodeURIComponent(document
                            .getElementById("encryptImageContent").value)+"&encryptkey="
                    + encodeURIComponent(document.getElementById("encryptkey").value), true);
            //回调函数
            xmlHttp.onreadystatechange = function() {
                if (xmlHttp.readyState == 4) {
                    if (xmlHttp.status == 200) {
                        document.getElementById("showOptionEncryptImageContent").innerHTML = xmlHttp.responseText;
                    } else {
                        alert("AJAX服务器返回错误！");
                    }
                }
            }
            //发送请求
            xmlHttp.send();


        }

    </script>
    <script type="text/javascript">
        function showEncryptKey(){
            var select = document.getElementById('selectEncryptKey');
            var encryptKey = document.getElementById('encryptkey');
            switch(select.value){
                case 'func':
                    encryptKey.value='MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDv0rdsn5FYPn0EjsCPqDyIsYRawNWGJDRHJBcdCldodjM5bpve+XYb4Rgm36F6iDjxDbEQbp/HhVPj0XgGlCRKpbluyJJt8ga5qkqIhWoOd/Cma1fCtviMUep21hIlg1ZFcWKgHQoGoNX7xMT8/0bEsldaKdwxOlv3qGxWfqNV5QIDAQAB';
                    break;
                case 'test':
                    encryptKey.value='MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBpueNweMbYdb+CMl8dUNv5g5THYLD9Z33cAMA4GNjmPYsbcNQLyO5QSlLNjpbCwopt7b5lFP8TGLUus4x0Ed6S4Wd9KmNw6NLbszNEmppP9HXlT9sT4/ShL0CpVF4ofFS8O/gXwCTJjYZJ0HvK3GBTSP2C9WlipTpWQ+9QJugewIDAQAB';
                    break;
                case 'real':
                    encryptKey.value='MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgzUSRjJijlZudygIjXl9EzqbwQTdnZys88wt4OolaRo6Cwc8RckBupaDdw3Xx3rDzSRHwvO3uMpAYapLpIkVFRq0VmzQG80ZW9pTQ6UBTKx94H+C5jZ1dBudVUun0x6+sW+LZ8vcUY1dRqN62fPN7xSaZXEfgcgu1+pWb5lOYSwIDAQAB';
                    break;
                default:
                    encryptKey.value='';
                    break;
            }
        }
    </script>
<html>
<body>
<div style="position: relative; margin-top: 50PX; margin-bottom: 50PX"
     class="container">
    <div id="base64EncryptIamge">
        <div>pas接口照片加密</div>
        <div style="width: 800px">
            <div>
                <table class="table table-striped">
                    <div>
                        <tr>
                            <td><label class="col-sm-2 control-label">ENCRYPT_KEY:</label></td>
                            <td>
                                <input type="text" name="encryptkey" class="form-control" id="encryptkey" placeholder="加密公钥" value="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDv0rdsn5FYPn0EjsCPqDyIsYRawNWGJDRHJBcdCldodjM5bpve+XYb4Rgm36F6iDjxDbEQbp/HhVPj0XgGlCRKpbluyJJt8ga5qkqIhWoOd/Cma1fCtviMUep21hIlg1ZFcWKgHQoGoNX7xMT8/0bEsldaKdwxOlv3qGxWfqNV5QIDAQAB">
                            </td>
                            <td>
                                <select id="selectEncryptKey" class="form-control" onchange="showEncryptKey();">
                                    <option>请选择</option>
                                    <option value="func">测试环境加密公钥</option>
                                    <option value="test">联调环境加密公钥</option>
                                    <option value="real">生产环境加密公钥</option>
                                </select>
                            </td>
                        </tr>
                    </div>
                    <div>
                        <tr>
                            <td>
                                <input tpye="text" name="encryptImage" id="encryptImageContent" placeholder="输入路径"
                                       value="" style="width: 70%">
                                <input type="button" value="加密(原pas图片接口)" onclick="encryptIamge()">
                                <input type="button" value="加密(优化pas图片接口)" onclick="optimizeEncryptImage()">
                            </td>
                        </tr>
                    </div>
                </table>
            </div>
        </div>
    </div>
    <div style="width: 800px">
        <table class="table table-striped">
            <div>
                <tr>
                    <td><label class="col-sm-2 control-label">加密后数据:</label></td>
                </tr>
            </div>
            <div>
                <tr>
                    <td id="showEncryptImageContent">加密图片(原接口):
                    </td>
                </tr>
                <tr>
                    <td id="showOptionEncryptImageContent">加密图片(优化接口):
                    </td>
                </tr>
            </div>
        </table>
    </div>
</div>
</body>
</html>
