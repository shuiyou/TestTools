<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>查询客户在途及持有份额</title>
    <script type="text/javascript">
        function show(){
            var select = document.getElementById('selectEnv');
            var address = document.getElementById('url');
            var partnerId = document.getElementById('partner_id');
            switch(select.value){
                case 'func':
                    address.value='http://10.65.209.21:9316/wos-fundsale/gateway.do';
                    partnerId.value='200006253126';
                    break;
                case 'test':
                    address.value='http://testapi.fundwex.com/wos-fundsale/gateway.do';
                    partnerId.value='200008482687';
                    break;
                case 'real':
                    address.value='https://api.wexfin.com/wos/gateway';
                    partnerId.value='200059796882';
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
			var rsakey = document.getElementById('rsakey');
            switch(select.value){
                case 'func':
                    encryptKey.value='MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwnBuEcKxJByhogleB/rygtyYfGiVrDxDxBKQirdDTOdOy15mhvgXY6AzNfFf5ck0WXfL00SXDwukw3XnKUzGbHl0rXzpLMR+CLpvyLj6QGoBhJ4EUgnfuyFKhbOK2ti5VEgj3ATFIfU+OMiXzv7ybb+LNtFoJ1oXyAk8JnH+KuwIDAQAB';
					rsakey.value='MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALqAF4eSBC+Ax4PzZBd0HXwqHW5HI+5E8EkGNPdDUfiUL2jyO9z8+TdsPubUZAqeSAWvUOVdWuqYFaaY1U8mmM1YdKTS84x/ZLEHxMHKEqLgkmG0XKPxtJHmtsQ0yJtCd1Ya1tr1+JS4pmxP2Uhmauvs1IwijgJxzKe+Qbs5JpX7AgMBAAECgYEAkGLu1ISVPPmkfWGHkijRUvmAA4qMJMG2jbKCexPheGOxOLyg3/vyLZheMnMoaJgWBmCuboSSCKFp0artKfQlwzB0h2ZvRcuj/uNEZnSYsRVivF/huHLhKWZLBqVCt5CtDx8H7h+X6SgGCmWGE546LofrR6TBU+O/+rOBC/F1h2ECQQDzZtG+6S0yXOKmK09NdgHe6tLHGCI+z9farVJRkBTsq4tp8BMvvldH3mPwzLfR2YtR9blSoDMpveNe//ensyhZAkEAxCdOH66ktnFV9VNya9MaIQdNr15btonOD3IN2n7I28MDeRKMrvo/s4cVpKo2Kbb2dOYSMafqcO/tMWocW1hmcwJBAKRDvsfty9/SSe/FCNcJDggoSCmvQuVLFazyDb0X1NdIimTrbbdp6LOLBb2sG15XR6v/fpuhnAXOrhUeAEBAK7kCQHDQGkJmcxzqugFKaPhShSiRxsAhiKXHQ9fmRMlxOZwK4Kh1XqwUCSb7fhsiOdxr+frph3U/mboygzx0RrLSeLkCQCNFirhPlaZrsul7Js8P+hOMiAg8ihfLcPS080i2Ai9Morzq8mbFQ5wTO5NfJCfJlrnwC0w7vxXVgiy6yRgnxvE=';
                    break;
                case 'test':
                    encryptKey.value='MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwnBuEcKxJByhogleB/rygtyYfGiVrDxDxBKQirdDTOdOy15mhvgXY6AzNfFf5ck0WXfL00SXDwukw3XnKUzGbHl0rXzpLMR+CLpvyLj6QGoBhJ4EUgnfuyFKhbOK2ti5VEgj3ATFIfU+OMiXzv7ybb+LNtFoJ1oXyAk8JnH+KuwIDAQAB';
					rsakey.value='MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALqAF4eSBC+Ax4PzZBd0HXwqHW5HI+5E8EkGNPdDUfiUL2jyO9z8+TdsPubUZAqeSAWvUOVdWuqYFaaY1U8mmM1YdKTS84x/ZLEHxMHKEqLgkmG0XKPxtJHmtsQ0yJtCd1Ya1tr1+JS4pmxP2Uhmauvs1IwijgJxzKe+Qbs5JpX7AgMBAAECgYEAkGLu1ISVPPmkfWGHkijRUvmAA4qMJMG2jbKCexPheGOxOLyg3/vyLZheMnMoaJgWBmCuboSSCKFp0artKfQlwzB0h2ZvRcuj/uNEZnSYsRVivF/huHLhKWZLBqVCt5CtDx8H7h+X6SgGCmWGE546LofrR6TBU+O/+rOBC/F1h2ECQQDzZtG+6S0yXOKmK09NdgHe6tLHGCI+z9farVJRkBTsq4tp8BMvvldH3mPwzLfR2YtR9blSoDMpveNe//ensyhZAkEAxCdOH66ktnFV9VNya9MaIQdNr15btonOD3IN2n7I28MDeRKMrvo/s4cVpKo2Kbb2dOYSMafqcO/tMWocW1hmcwJBAKRDvsfty9/SSe/FCNcJDggoSCmvQuVLFazyDb0X1NdIimTrbbdp6LOLBb2sG15XR6v/fpuhnAXOrhUeAEBAK7kCQHDQGkJmcxzqugFKaPhShSiRxsAhiKXHQ9fmRMlxOZwK4Kh1XqwUCSb7fhsiOdxr+frph3U/mboygzx0RrLSeLkCQCNFirhPlaZrsul7Js8P+hOMiAg8ihfLcPS080i2Ai9Morzq8mbFQ5wTO5NfJCfJlrnwC0w7vxXVgiy6yRgnxvE=';
                    break;
                case 'real':
                    encryptKey.value='MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwnBuEcKxJByhogleB/rygtyYfGiVrDxDxBKQirdDTOdOy15mhvgXY6AzNfFf5ck0WXfL00SXDwukw3XnKUzGbHl0rXzpLMR+CLpvyLj6QGoBhJ4EUgnfuyFKhbOK2ti5VEgj3ATFIfU+OMiXzv7ybb+LNtFoJ1oXyAk8JnH+KuwIDAQAB';
					rsakey.value='MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAMaLI4hNHXEwFxi7ffwdI0777uHlfbZeapDR60wnjbIwqW9+Xwee/xYXyhY5SRKT+93CIg+dMjaua8bmzVks5PKfT8XUioVRy3UGFSNEKeDbCsbrVW51ITr/k0cM4GYF/Pns/7y7kjOhSglvNGyiNAEWws+OwruWy8vrqNh++f2tAgMBAAECgYEAv7ASbERXyrItenl613CiaF2gc4UIbIyXwp6J2IrPqVyGGdkektMVYRUyzWYdgMxTffUywXR5yIV6FR084X4Qw+I1UtaZFFJV/RZxqocySkW14vMgm00ebAlvBfe0B4jXK3p0Hl/Rw1U1tzVcrshi7aBD4vl6mSC7ikYWO/Lg7VkCQQDy4YW4/HAoRQaUr3D13dObDDN3d2+a1mRZS6X7HM+27UyuHIzaX43WoG7nGs6DipBCw7D5A8jUBWu7/9819wNHAkEA0USIctVZ+BVA/Vd7v1xA8LoC9iOCpyUuxQxwrXM4+X7r4K0ljgekp6Uks+SuOeh5yfsb6LyuHN/SAb7Dy0DpawJAGcwseO6PNyfFwCs2U1chb7mHCexstUSKWhW35okz4tknqOKtb1Bw71gRD4kAYGZqVj0U4g4t2Z9NxyUxXzkCZwJAELdLuviF5kY0WchKLz/8Q/5nzCeMATP8J2tV+NTyYSOdTeYm0NCVe3q6ktAahq+mQtcXJKElu1K5oMAPPcYinwJBAOIFZavYKWoeyRPxrx9tWwzk+pG3oABVm/uZmfx5dNSdRj6+gcq4lfjU33Q1Wa5MBMUDtEhycLTfBW1Q7cxN52E=';
                    break;
                default:
                    encryptKey.value='';
					rsakey.value='';
                    break;
            }
        }

        function setRequestTime(){
            var requestTime = document.getElementById('request_time');
            requestTime.value = new Date().Format('yyyyMMddHHmmss');
        }

        function setUid(){
            var requestTime = document.getElementById('login_name');
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
        <div class="h4" >查询客户在途及持有份额</div>
        <div style="width: 800px">
            <div>
                <form target="_blank" class="form-horizontal" role="form" name="login" action="../FundMemberShareListGet" method="post" accept-charset="utf-8" onsubmit="return checkUrl();">
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
                                    <input type="text" name="service" class="form-control" id="service"  placeholder="接口名称" value="fund_member_share_list_get">
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
                            <td><label class="col-sm-2 control-label">platform_type: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="platform_type" class="form-control" id="platform_type" placeholder="平台类型" value="PC">
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
                            <td><label class="col-sm-2 control-label">start_time: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="start_time" class="form-control" id="start_time" placeholder="开始时间" value="20120101120000">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">end_time: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="end_time" class="form-control" id="end_time" placeholder="结束时间" value="20200101120000">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">page_no: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="page_no" class="form-control" id="page_no" placeholder="当前页号" value="1">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">page_size: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="page_size" class="form-control" id="page_size" placeholder="每页记录数" value="20">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">member_id: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="member_id" class="form-control" id="member_id" placeholder="会员ID" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">fund_code_list: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="fund_code_list" class="form-control" id="fund_code_list" placeholder="基金代码列表" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">trade_account_no: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="trade_account_no" class="form-control" id="trade_account_no" placeholder="交易账号" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">filter_fund_code_list: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="filter_fund_code_list" class="form-control" id="filter_fund_code_list" placeholder="需过滤的基金代码" value="">
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
                                    <input type="text" name="rsakey" class="form-control" id="rsakey" placeholder="RSA签名私钥" value="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBALqAF4eSBC+Ax4PzZBd0HXwqHW5HI+5E8EkGNPdDUfiUL2jyO9z8+TdsPubUZAqeSAWvUOVdWuqYFaaY1U8mmM1YdKTS84x/ZLEHxMHKEqLgkmG0XKPxtJHmtsQ0yJtCd1Ya1tr1+JS4pmxP2Uhmauvs1IwijgJxzKe+Qbs5JpX7AgMBAAECgYEAkGLu1ISVPPmkfWGHkijRUvmAA4qMJMG2jbKCexPheGOxOLyg3/vyLZheMnMoaJgWBmCuboSSCKFp0artKfQlwzB0h2ZvRcuj/uNEZnSYsRVivF/huHLhKWZLBqVCt5CtDx8H7h+X6SgGCmWGE546LofrR6TBU+O/+rOBC/F1h2ECQQDzZtG+6S0yXOKmK09NdgHe6tLHGCI+z9farVJRkBTsq4tp8BMvvldH3mPwzLfR2YtR9blSoDMpveNe//ensyhZAkEAxCdOH66ktnFV9VNya9MaIQdNr15btonOD3IN2n7I28MDeRKMrvo/s4cVpKo2Kbb2dOYSMafqcO/tMWocW1hmcwJBAKRDvsfty9/SSe/FCNcJDggoSCmvQuVLFazyDb0X1NdIimTrbbdp6LOLBb2sG15XR6v/fpuhnAXOrhUeAEBAK7kCQHDQGkJmcxzqugFKaPhShSiRxsAhiKXHQ9fmRMlxOZwK4Kh1XqwUCSb7fhsiOdxr+frph3U/mboygzx0RrLSeLkCQCNFirhPlaZrsul7Js8P+hOMiAg8ihfLcPS080i2Ai9Morzq8mbFQ5wTO5NfJCfJlrnwC0w7vxXVgiy6yRgnxvE=">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">ENCRYPT_KEY: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="encryptkey" class="form-control" id="encryptkey" placeholder="加密公钥" value="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCwnBuEcKxJByhogleB/rygtyYfGiVrDxDxBKQirdDTOdOy15mhvgXY6AzNfFf5ck0WXfL00SXDwukw3XnKUzGbHl0rXzpLMR+CLpvyLj6QGoBhJ4EUgnfuyFKhbOK2ti5VEgj3ATFIfU+OMiXzv7ybb+LNtFoJ1oXyAk8JnH+KuwIDAQAB">
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