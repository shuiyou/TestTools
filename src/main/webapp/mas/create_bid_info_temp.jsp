<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>创建标的加密</title>
    <script type="text/javascript">
        function show() {
            var select = document.getElementById('selectEnv');
            var address = document.getElementById('url');
            var partnerId = document.getElementById('partner_id');
            switch (select.value) {
                case 'func':
                    address.value = 'http://10.65.209.29:8181/mas/gateway.do';
                    partnerId.value = '200003670082';
                    break;
                case 'test':
                    address.value = 'https://testgate.pay.sina.com.cn/mas/gateway.do';
                    partnerId.value = '200003670082';
                    break;
                case 'real':
                    address.value = 'https://gate.pay.sina.com.cn/mas/gateway.do';
                    partnerId.value = '200003673585';
                    break;
                default:
                    address.value = '';
                    partnerId.value = '';
                    break;
            }
        }

        function showEncryptKey() {
            var select = document.getElementById('selectEncryptKey');
            var encryptKey = document.getElementById('encryptkey');
            switch (select.value) {
                case 'func':
                    encryptKey.value = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDv0rdsn5FYPn0EjsCPqDyIsYRawNWGJDRHJBcdCldodjM5bpve+XYb4Rgm36F6iDjxDbEQbp/HhVPj0XgGlCRKpbluyJJt8ga5qkqIhWoOd/Cma1fCtviMUep21hIlg1ZFcWKgHQoGoNX7xMT8/0bEsldaKdwxOlv3qGxWfqNV5QIDAQAB';
                    break;
                case 'test':
                    encryptKey.value = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCBpueNweMbYdb+CMl8dUNv5g5THYLD9Z33cAMA4GNjmPYsbcNQLyO5QSlLNjpbCwopt7b5lFP8TGLUus4x0Ed6S4Wd9KmNw6NLbszNEmppP9HXlT9sT4/ShL0CpVF4ofFS8O/gXwCTJjYZJ0HvK3GBTSP2C9WlipTpWQ+9QJugewIDAQAB';
                    break;
                case 'real':
                    encryptKey.value = 'MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDgzUSRjJijlZudygIjXl9EzqbwQTdnZys88wt4OolaRo6Cwc8RckBupaDdw3Xx3rDzSRHwvO3uMpAYapLpIkVFRq0VmzQG80ZW9pTQ6UBTKx94H+C5jZ1dBudVUun0x6+sW+LZ8vcUY1dRqN62fPN7xSaZXEfgcgu1+pWb5lOYSwIDAQAB';
                    break;
                default:
                    encryptKey.value = '';
                    break;
            }
        }

        function changeBid_type() {
            var bid_type = document.getElementById('bid_type');
            bid_type.value =  document.getElementById('selectBid_type').value;
        }

        function changeRepay_type() {
            var repay_type = document.getElementById('repay_type');
            repay_type.value =  document.getElementById('selectRepay_type').value;
        }

        function changeProtocol_type() {
            var protocol_type = document.getElementById('protocol_type');
            protocol_type.value = document.getElementById('selectProtocol_type').value;
        }

        function setRequestTime() {
            var requestTime = document.getElementById('request_time');
            requestTime.value = new Date().Format('yyyyMMddHHmmss');
        }

        function setBegin_date() {
            var requestTime = document.getElementById('begin_date');
            requestTime.value = new Date().Format('yyyyMMddHHmmss');
        }

        function setTerm() {
            var requestTime = document.getElementById('term');
            requestTime.value = new Date().Format('yyyyMMddHHmmss');
        }

        function setOut_bid_no() {
            var requestTime = document.getElementById('out_bid_no');
            requestTime.value = new Date().Format('yyyyMMddHHmmss');
        }

        function checkUrl() {
            if (document.getElementById('url').value == "") {
                alert("请选择请求环境!");
                document.getElementById('selectEnv').focus();
                return false;
            }
        }

        Date.prototype.Format = function(formatStr) {
            var str = formatStr;
            var Week = [ '日', '一', '二', '三', '四', '五', '六' ];

            str = str.replace(/yyyy|YYYY/, this.getFullYear());
            str = str.replace(/yy|YY/,
                (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString()
                    : '0' + (this.getYear() % 100));

            str = str.replace(/MM/, this.getMonth() + 1 > 9 ? (this.getMonth() + 1)
                .toString() : '0' + (this.getMonth() + 1));
            str = str.replace(/M/g, this.getMonth());

            str = str.replace(/w|W/g, Week[this.getDay()]);

            str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate()
                .toString() : '0' + this.getDate());
            str = str.replace(/d|D/g, this.getDate());

            str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours()
                .toString() : '0' + this.getHours());
            str = str.replace(/h|H/g, this.getHours());
            str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes()
                .toString() : '0' + this.getMinutes());
            str = str.replace(/m/g, this.getMinutes());

            str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds()
                .toString() : '0' + this.getSeconds());
            str = str.replace(/s|S/g, this.getSeconds());

            return str;
        }
    </script>
</head>
<body>
<div style="position: relative; margin-top: 50PX; margin-bottom: 50PX"
     class="container">
    <div id="createMember">
        <div class="h4">创建标的加密</div>
        <div style="width: 800px">
            <div>
                <form target="_blank" class="form-horizontal" role="form"
                      name="login" action="../CreateBidInfoTemp" method="post"
                      accept-charset="utf-8" onsubmit="return checkUrl();">
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
                                    <input type="text" name="url" class="form-control" id="url"
                                           placeholder="请求接口地址" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <select id="selectEnv" class="form-control"
                                            onchange="show();">
                                        <option>请选择</option>
                                        <option value="func">测试环境</option>
                                        <option value="test">联调环境</option>
                                        <option value="real">生产环境</option>
                                    </select>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">service：</label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="service" class="form-control"
                                           id="service" placeholder="接口名称" value="create_bid_info">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">version:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="version" class="form-control"
                                           id="version" placeholder="接口版本" value="1.0">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">request_time:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="request_time" class="form-control"
                                           id="request_time" placeholder="请求时间">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="button" value="生成请求时间"
                                           onclick="setRequestTime()" />
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">partner_id:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="partner_id" class="form-control"
                                           id="partner_id" placeholder="合作者身份ID" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">_input_charset:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="_input_charset" class="form-control"
                                           id="_input_charset" placeholder="参数字符编码集" value="UTF-8">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">sign_type:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="sign_type" class="form-control"
                                           id="sign_type" placeholder="签名类型" value="RSA">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">sign_version:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="sign_version" class="form-control"
                                           id="sign_version" placeholder="签名版本号" value="1.0">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">encrypt_version:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="encrypt_version"
                                           class="form-control" id="encrypt_version"
                                           placeholder="加密版本号" value="1.0">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">notify_url:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="notify_url" class="form-control"
                                           id="notify_url" placeholder="系统异步回调"
                                           value="http://10.65.110.27:8888/receiveNotify">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">return_url:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="return_url" class="form-control"
                                           id="return_url" placeholder="页面跳转同步返回页面路径"
                                           value="http://10.65.110.27:8888/receiveNotify">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">memo: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="memo" class="form-control" id="memo"
                                           placeholder="备注" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">out_bid_no:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="out_bid_no" class="form-control"
                                           id="out_bid_no" placeholder="商户标的号" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="button" value="生成商户标的号" onclick="setOut_bid_no()" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">summary:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="summary" class="form-control"
                                           id="summary" placeholder="摘要" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">web_site_name:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="web_site_name" class="form-control"
                                           id="web_site_name" placeholder="网站名称" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">bid_name:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="bid_name" class="form-control"
                                           id="bid_name" placeholder="标的名称" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">bid_type:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="bid_type" class="form-control"
                                           id="bid_type" placeholder="标的类型" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <select id="selectBid_type" class="form-control" onchange="changeBid_type();">
                                        <option>请选择</option>
                                        <option value="CREDIT">CREDIT</option>
                                        <option value="MORTGAGE">MORTGAGE</option>
                                        <option value="ASSIGNMENT_DEBT">ASSIGNMENT_DEBT</option>
                                        <option value="OTHER">OTHER</option>
                                        <option value="BORROW">BORROW</option>
                                        <option value="DEALER">DEALER</option>
                                        <option value="WTHK">WTHK</option>
                                    </select>
                                </div>
                            </td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">bid_amount:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="bid_amount" class="form-control"
                                           id="bid_amount" placeholder="标的金额" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">bid_year_rate:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="bid_year_rate" class="form-control"
                                           id="bid_year_rate" placeholder="年化收益率" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">bid_duration:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="bid_duration" class="form-control"
                                           id="bid_duration" placeholder="借款期限" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">repay_type:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="repay_type" class="form-control"
                                           id="repay_type" placeholder="还款方式" value="">
                                </div>
                            </td>
                            <td>
                                <select id="selectRepay_type" onchange="changeRepay_type();" class="form-control">
                                    <option>请选择</option>
                                    <option value="REPAY_CAPITAL_WITH_INTEREST">REPAY_CAPITAL_WITH_INTEREST</option>
                                    <option value="AVERAGE_CAPITAL">AVERAGE_CAPITAL</option>
                                    <option value="AVERAGE_CAPITAL_PLUS_INTERES">AVERAGE_CAPITAL_PLUS_INTERES</option>
                                    <option value="SCHEDULED_INTEREST_PAYMENTS_DUE">SCHEDULED_INTEREST_PAYMENTS_DUE</option>
                                    <option value="OTHER">OTHER</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">protocol_type:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="protocol_type" class="form-control"
                                           id="protocol_type" placeholder="协议类型" value="">
                                </div>
                            </td>
                            <td>
                                <select id="selectProtocol_type" class="form-control" onchange="changeProtocol_type();">
                                    <option>请选择</option>
                                    <option value="ASSIGNMENT_DEBT">ASSIGNMENT_DEBT</option>
                                    <option value="LOAN_AGREEMENT">LOAN_AGREEMENT</option>
                                    <option value="OTHER">OTHER</option>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">bid_product_type:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="bid_product_type"
                                           class="form-control" id="bid_product_type"
                                           placeholder="标的产品类型" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">ecommend_inst:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="ecommend_inst" class="form-control"
                                           id="ecommend_inst" placeholder="推荐机构" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">limit_min_bid_copys:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="limit_min_bid_copys"
                                           class="form-control" id="limit_min_bid_copys"
                                           placeholder="限定最低投标份数" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">limit_per_copy_amount:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="limit_per_copy_amount"
                                           class="form-control" id="limit_per_copy_amount"
                                           placeholder="限定每份投标金额" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">limit_max_bid_amount:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="limit_max_bid_amount"
                                           class="form-control" id="limit_max_bid_amount"
                                           placeholder="限定最多投标金额" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">limit_min_bid_amount:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="limit_min_bid_amount"
                                           class="form-control" id="limit_min_bid_amount"
                                           placeholder="限定最少投标金额" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">url: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="url2" class="form-control" id="url2"
                                           placeholder="标的URL" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">begin_date:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="begin_date" class="form-control"
                                           id="begin_date" placeholder="标的开始时间" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="button" value="生成标的开始时间" onclick="setBegin_date()" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">term: </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="term" class="form-control" id="term"
                                           placeholder="还款期限" value="">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="button" value="生成还款日期" onclick="setTerm()" />
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">guarantee_method:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="guarantee_method"
                                           class="form-control" id="guarantee_method"
                                           placeholder="担保方式" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">borrower_info_list:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="borrower_info_list"
                                           class="form-control" id="borrower_info_list"
                                           placeholder="借款人信息列表" value="changeMe~UID~100~买房1~18300000001~01012345678~上海~10~100~本科~已婚~上海街道~a@126.com~summary~1^a|b^d">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">int_pay_type:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="int_pay_type"
                                           class="form-control" id="int_pay_type"
                                           placeholder="" value="MONTH_FIXED_DAY">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">int_pay_day:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="int_pay_day"
                                           class="form-control" id="int_pay_day"
                                           placeholder="" value="21">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">extend_param:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="extend_param" class="form-control"
                                           id="extend_param" placeholder="扩展信息" value="isRsaEncrypt^Y">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">raise_begin_date:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="raise_begin_date" class="form-control"
                                           id="raise_begin_date" placeholder="华瑞募集开始日期" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">raise_end_date:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="raise_end_date" class="form-control"
                                           id="raise_end_date" placeholder="华瑞募集结束日期" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">assure_account_identity:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="assure_account_identity" class="form-control"
                                           id="assure_account_identity" placeholder="华瑞担保方账户" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">dealer_account_identity:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="dealer_account_identity" class="form-control"
                                           id=dealer_account_identity placeholder="华瑞经销商账户-经销商标的必填项" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">repay_account_identity:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="repay_account_identity" class="form-control"
                                           id="repay_account_identity" placeholder="华瑞委托还款账户-委托还款标的必填项" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">repay_identity_type:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="repay_identity_type" class="form-control"
                                           id="repay_identity_type" placeholder="委托还款人会员标识类型" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">repay_identity_id:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="repay_identity_id" class="form-control"
                                           id="repay_identity_id" placeholder="委托还款人会员标识" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">dealer_identity_type:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="dealer_identity_type" class="form-control"
                                           id="dealer_identity_type" placeholder="经销商会员标识类型" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">dealer_identity_id:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="dealer_identity_id" class="form-control"
                                           id="dealer_identity_id" placeholder="经销商会员标识" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">assure_identity_type:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="assure_identity_type" class="form-control"
                                           id="assure_identity_type" placeholder="担保方会员标识类型" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>
                        <tr>
                            <td><label class="col-sm-2 control-label">assure_identity_id:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="assure_identity_id" class="form-control"
                                           id="assure_identity_id" placeholder="担保方会员标识" value="">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">MD5_SIGN_KEY:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="md5key" class="form-control"
                                           id="md5key" placeholder="MD5签名KEY"
                                           value="1234567890qwertyuiopasdfghjklzxc">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">RSA_SIGN_KEY:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="rsakey" class="form-control"
                                           id="rsakey" placeholder="RSA签名私钥"
                                           value="MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAO/6rPCvyCC+IMalLzTy3cVBz/+wamCFNiq9qKEilEBDTttP7Rd/GAS51lsfCrsISbg5td/w25+wulDfuMbjjlW9Afh0p7Jscmbo1skqIOIUPYfVQEL687B0EmJufMlljfu52b2efVAyWZF9QBG1vx/AJz1EVyfskMaYVqPiTesZAgMBAAECgYEAtVnkk0bjoArOTg/KquLWQRlJDFrPKP3CP25wHsU4749t6kJuU5FSH1Ao81d0Dn9m5neGQCOOdRFi23cV9gdFKYMhwPE6+nTAloxI3vb8K9NNMe0zcFksva9c9bUaMGH2p40szMoOpO6TrSHO9Hx4GJ6UfsUUqkFFlN76XprwE+ECQQD9rXwfbr9GKh9QMNvnwo9xxyVl4kI88iq0X6G4qVXo1Tv6/DBDJNkX1mbXKFYL5NOW1waZzR+Z/XcKWAmUT8J9AkEA8i0WT/ieNsF3IuFvrIYG4WUadbUqObcYP4Y7Vt836zggRbu0qvYiqAv92Leruaq3ZN1khxp6gZKl/OJHXc5xzQJACqr1AU1i9cxnrLOhS8m+xoYdaH9vUajNavBqmJ1mY3g0IYXhcbFm/72gbYPgundQ/pLkUCt0HMGv89tn67i+8QJBALV6UgkVnsIbkkKCOyRGv2syT3S7kOv1J+eamGcOGSJcSdrXwZiHoArcCZrYcIhOxOWB/m47ymfE1Dw/+QjzxlUCQCmnGFUO9zN862mKYjEkjDN65n1IUB9Fmc1msHkIZAQaQknmxmCIOHC75u4W0PGRyVzq8KkxpNBq62ICl7xmsPM=">
                                </div>
                            </td>
                            <td></td>
                        </tr>

                        <tr>
                            <td><label class="col-sm-2 control-label">ENCRYPT_KEY:
                            </label></td>
                            <td>
                                <div class="col-sm-10">
                                    <input type="text" name="encryptkey" class="form-control"
                                           id="encryptkey" placeholder="加密公钥"
                                           value="MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDv0rdsn5FYPn0EjsCPqDyIsYRawNWGJDRHJBcdCldodjM5bpve+XYb4Rgm36F6iDjxDbEQbp/HhVPj0XgGlCRKpbluyJJt8ga5qkqIhWoOd/Cma1fCtviMUep21hIlg1ZFcWKgHQoGoNX7xMT8/0bEsldaKdwxOlv3qGxWfqNV5QIDAQAB">
                                </div>
                            </td>
                            <td>
                                <div class="col-sm-10">
                                    <select id="selectEncryptKey" class="form-control"
                                            onchange="showEncryptKey();">
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
                                    <button type="submit" class="btn btn-primary"
                                            style="width: 280px; text-align: center">发送请求</button>
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