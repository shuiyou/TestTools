<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>绑定银行卡</title>
    <script type="text/javascript">
        function show(){
            var select = document.getElementById('selectEnv');
            var address = document.getElementById('url');
            var partnerId = document.getElementById('partner_id');
            switch(select.value){
                case 'func':
                    address.value='http://10.65.209.181/mgs/gateway.do';
                    partnerId.value='200006658095';
                    break;
                case 'test':
                    address.value='https://testgate.pay.sina.com.cn/mgs/gateway.do';
                    partnerId.value='200003670082';
                    break;
                case 'real':
                    address.value='https://gate.pay.sina.com.cn/mgs/gateway.do';
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

        function setRequestNo(){
            var requestTime = document.getElementById('request_no');
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
    <div id="createMember">
        <div class="h4" >绑定银行卡</div>
        <div style="width: 800px">
            <div>
                <form target="_blank" class="form-horizontal" role="form" name="login" action="../BindingBankCard_sh" method="post" accept-charset="utf-8" onsubmit="return checkUrl();">
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
                                    <input type="text" name="service" class="form-control" id="service"  placeholder="接口名称" value="binding_bank_card">
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
                                    <input type="text" name="notify_url" class="form-control" id="notify_url" placeholder="系统异步回调" value="http://www.baidu.com">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">return_url: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="return_url" class="form-control" id="return_url" placeholder="页面跳转同步返回页面路径" value="http://www.baidu.com">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">memo: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="memo" class="form-control" id="memo" placeholder="备注" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">request_no: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="request_no" class="form-control" id="request_no" placeholder="绑卡请求号" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="button" value="生成绑卡请求号" onclick="setRequestNo()"/>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">identity_id: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="identity_id" class="form-control" id="identity_id" placeholder="客户标识信息" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">identity_type: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="identity_type" class="form-control" id="identity_type" placeholder="客户标识类型" value="UID">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">card_id: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="card_id" class="form-control" id="card_id" placeholder="银行卡ID" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">bank_code: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="bank_code" class="form-control" id="bank_code" placeholder="银行编号" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">bank_account_no: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="bank_account_no" class="form-control" id="bank_account_no" placeholder="银行卡号" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">account_name: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="account_name" class="form-control" id="account_name" placeholder="户名" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">card_type: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="card_type" class="form-control" id="card_type" placeholder="卡类型" value="DEBIT">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">card_attribute: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="card_attribute" class="form-control" id="card_attribute" placeholder="卡属性" value="C">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">cert_type: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="cert_type" class="form-control" id="cert_type" placeholder="证件类型" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">cert_no: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="cert_no" class="form-control" id="cert_no" placeholder="证件号码" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">phone_no: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="phone_no" class="form-control" id="phone_no" placeholder="银行预留手机号" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">validity_period: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="validity_period" class="form-control" id="validity_period" placeholder="有效期" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">verification_value: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="verification_value" class="form-control" id="verification_value" placeholder="CVV2" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">province: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="province" class="form-control" id="province" placeholder="省份" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">city: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="city" class="form-control" id="city" placeholder="城市" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">bank_branch: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="bank_branch" class="form-control" id="bank_branch" placeholder="支行名称" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">is_verified: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="is_verified" class="form-control" id="is_verified" placeholder="认证标志" value="1">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">alias: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="alias" class="form-control" id="alias" placeholder="别名" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">card_skin: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="card_skin" class="form-control" id="card_skin" placeholder="银行卡皮肤" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">is_signing: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="is_signing" class="form-control" id="is_signing" placeholder="是否签约" value="Y">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">status: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="status" class="form-control" id="status" placeholder="银行卡状态" value="1">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">pay_attribute: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="pay_attribute" class="form-control" id="pay_attribute" placeholder="支付属性" value="qpay">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">channel_code: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="channel_code" class="form-control" id="channel_code" placeholder="渠道编码" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">sign_num: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="sign_num" class="form-control" id="sign_num" placeholder="协议编码" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">sign_id: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="sign_id" class="form-control" id="sign_id" placeholder="协议ID" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">agreement_valid_date: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="agreement_valid_date" class="form-control" id="agreement_valid_date" placeholder="协议有效期" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">bank_name: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="bank_name" class="form-control" id="bank_name" placeholder="银行名称" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">extend_param: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="extend_param" class="form-control" id="extend_param" placeholder="扩展信息" value="">
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