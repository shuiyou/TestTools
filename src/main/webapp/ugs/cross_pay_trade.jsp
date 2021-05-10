<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>收单再次支付</title>
    <script type="text/javascript">
        function show(){
            var select = document.getElementById('selectEnv');
            var address = document.getElementById('url');
            var partnerId = document.getElementById('partner_id');
            switch(select.value){
                case 'func':
                    address.value='http://10.65.209.13:9100/ugs/gateway.do';
                    partnerId.value='200003670082';
                    break;
                case 'test':
                    address.value='http://10.65.210.18:9200/ugs/gateway.do';
                    partnerId.value='200003670082';
                    break;
                case 'real':
                    address.value='https://gate.pay.sina.com.cn/ugs/gateway.do';
                    partnerId.value='200003673585';
                    break;
                default:
                    address.value='';
                    partnerId.value='';
                    break;
            }
        }

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

        function setRequestTime(){
            var requestTime = document.getElementById('request_time');
            requestTime.value = new Date().Format('yyyyMMddHHmmss');
        }

        function setOutPayNo(){
            var requestTime = document.getElementById('out_pay_no');
            requestTime.value = new Date().Format('yyyyMMddHHmmss');
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
    <div id="CreateInsureTrade">
        <div class="h4" >收单再次支付</div>
        <div style="width: 800px">
            <div>
                <form target="_blank" class="form-horizontal" role="form" name="login" action="../CrossPayTrade" method="post" accept-charset="utf-8" onsubmit="return checkUrl();">
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
                            <td><label class="col-sm-2 control-label" >service：</label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="service" class="form-control" id="service"  placeholder="接口名称" value="cross_pay_trade">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">version: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="version" class="form-control" id="version" placeholder="接口版本" value="1.0">
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
                            <td><label class="col-sm-2 control-label">partner_id: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="partner_id" class="form-control" id="partner_id" placeholder="合作者身份ID" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">_input_charset: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="_input_charset" class="form-control" id="_input_charset" placeholder="参数字符编码集" value="UTF-8">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">sign_type: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="sign_type" class="form-control" id="sign_type" placeholder="签名类型" value="RSA">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">sign_version: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="sign_version" class="form-control" id="sign_version" placeholder="签名版本号" value="1.0">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">encrypt_version: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="encrypt_version" class="form-control" id="encrypt_version" placeholder="加密版本号" value="1.0">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">notify_url: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="notify_url" class="form-control" id="notify_url" placeholder="系统异步回调" value="http://10.65.110.27:8888/receiveNotify">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">return_url: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="return_url" class="form-control" id="return_url" placeholder="页面跳转同步返回页面路径" value="http://10.65.110.27:8888/receiveNotify">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">out_pay_no: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="out_pay_no" class="form-control" id="out_pay_no" placeholder="支付请求号" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="button" value="生成交易订单号" onclick="setOutPayNo()"/>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">out_trade_no: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="out_trade_no" class="form-control" id="out_trade_no" placeholder="交易订单号" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">payer_ip: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="payer_ip" class="form-control" id="payer_ip" placeholder="付款用户IP" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">pay_method: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="pay_method" class="form-control" id="pay_method" placeholder="支付方式" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>



                        <tr>
                            <td><label class="col-sm-2 control-label">MD5_SIGN_KEY: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="md5key" class="form-control" id="md5key" placeholder="MD5签名KEY" value="1234567890qwertyuiopasdfghjklzxc">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">RSA_SIGN_KEY: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="rsakey" class="form-control" id="rsakey" placeholder="RSA签名私钥" value="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO/6rPCvyCC+IMalLzTy3cVBz/+wamCFNiq9qKEilEBDTttP7Rd/GAS51lsfCrsISbg5td/w25+wulDfuMbjjlW9Afh0p7Jscmbo1skqIOIUPYfVQEL687B0EmJufMlljfu52b2efVAyWZF9QBG1vx/AJz1EVyfskMaYVqPiTesZAgMBAAECgYEAtVnkk0bjoArOTg/KquLWQRlJDFrPKP3CP25wHsU4749t6kJuU5FSH1Ao81d0Dn9m5neGQCOOdRFi23cV9gdFKYMhwPE6+nTAloxI3vb8K9NNMe0zcFksva9c9bUaMGH2p40szMoOpO6TrSHO9Hx4GJ6UfsUUqkFFlN76XprwE+ECQQD9rXwfbr9GKh9QMNvnwo9xxyVl4kI88iq0X6G4qVXo1Tv6/DBDJNkX1mbXKFYL5NOW1waZzR+Z/XcKWAmUT8J9AkEA8i0WT/ieNsF3IuFvrIYG4WUadbUqObcYP4Y7Vt836zggRbu0qvYiqAv92Leruaq3ZN1khxp6gZKl/OJHXc5xzQJACqr1AU1i9cxnrLOhS8m+xoYdaH9vUajNavBqmJ1mY3g0IYXhcbFm/72gbYPgundQ/pLkUCt0HMGv89tn67i+8QJBALV6UgkVnsIbkkKCOyRGv2syT3S7kOv1J+eamGcOGSJcSdrXwZiHoArcCZrYcIhOxOWB/m47ymfE1Dw/+QjzxlUCQCmnGFUO9zN862mKYjEkjDN65n1IUB9Fmc1msHkIZAQaQknmxmCIOHC75u4W0PGRyVzq8KkxpNBq62ICl7xmsPM=">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">ENCRYPT_KEY: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="encryptkey" class="form-control" id="encryptkey" placeholder="加密公钥" value="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDv0rdsn5FYPn0EjsCPqDyIsYRawNWGJDRHJBcdCldodjM5bpve+XYb4Rgm36F6iDjxDbEQbp/HhVPj0XgGlCRKpbluyJJt8ga5qkqIhWoOd/Cma1fCtviMUep21hIlg1ZFcWKgHQoGoNX7xMT8/0bEsldaKdwxOlv3qGxWfqNV5QIDAQAB">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <select id="selectEncryptKey" class="form-control" onchange="showEncryptKey();">
                                        <option>请选择</option>
                                        <option value="func">测试环境加密公钥</option>
                                        <option value="test">联调环境加密公钥</option>
                                        <option value="real">生产环境加密公钥</option>
                                    </select>
                                </div>
                            </td>
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