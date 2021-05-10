<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到微汇测试部门工具页面</title>
</head>
<body>
<div style="position: relative;margin-top: 60PX;margin-bottom: 50PX" class="container">
  <table class="table table-bordered" cellpadding="5" align="center">
    <tr>
        <th class="table table-bordered">通用数据</th>
        <th class="table table-bordered" colspan="2">数据内容</th>
    </tr>
    <tr>
        <td class="table table-bordered">HOST配置</td>
        <td class="table table-bordered">
          <div align='center'>
            <table style="table-layout:fixed;word-wrap:break-word" align="center" width="100%">
                <tr>
                  <td class="table table-bordered">测试环境</td>
                  <td class="table table-bordered">
                    10.65.1.14 funcmerchant.pay.sina.com.cn<br>
                    10.65.1.16 funcpay.sina.com.cn<br>
                    222.73.39.37 func2pay.sina.com.cn<br>
                    202.108.37.212 i.api.weibo.cn<br>
                    222.73.39.40 static.pay.sina.com.cn<br>
                    10.65.1.13 yalipay.sina.com.cn
                  </td>
                </tr>
                <tr>
                  <td class="table table-bordered">验证环境</td>
                  <td class="table table-bordered">
                    180.153.89.68 gate.pay.sina.com.cn mas.weibopay.com mcp.weibopay.com<br>
                    180.153.89.68 pay.sina.com.cn merchant.pay.sina.com.cn poss.weibopay.com<br>
                    180.153.89.68 guardian.weibopay.com intra.weibopay.com wlog.whintra.com static.pay.sina.com.cn
                  </td>
                </tr>
            </table>
          </div>
        </td>
    </tr>
    <tr>
        <td class="table table-bordered">产品编码</td>
        <td class="table table-bordered">
          <div>
            <table style="table-layout:fixed;word-wrap:break-word" align="center" width="100%">
                <tr>
                  <th class="table table-bordered">收单</th>
                  <td class="table table-bordered">20040001 1001(网银) 2001(余额,信用支付)</td>
                </tr>
                <tr>
                  <th class="table table-bordered">提现</th>
                  <td class="table table-bordered">10030001 3001</td>
                </tr>
                <tr>
                  <th class="table table-bordered">转账</th>
                  <td class="table table-bordered">10020001 2001</td>
                </tr>
                <tr>
                  <th class="table table-bordered">付款到账户</th>
                  <td class="table table-bordered">10020001 2001</td>
                </tr>
                <tr>
                  <th class="table table-bordered">付款到卡</th>
                  <td class="table table-bordered">30040004 3001</td>
                </tr>
                <tr>
                  <th class="table table-bordered">批量付款到卡</th>
                  <td class="table table-bordered">30040006 3001</td>
                </tr>
                <tr>
                  <th class="table table-bordered">预售权</th>
                  <td class="table table-bordered">20080001 1001</td>
                </tr>
                <tr>
                  <th class="table table-bordered">分账</th>
                  <td class="table table-bordered">30020001 2001</td>
                </tr>
                <tr>
                  <th class="table table-bordered">N/A</th>
                  <td class="table table-bordered">N/A</td>
                </tr>
            </table>
          </div>
        </td>
    </tr>
    <tr>
        <td class="table table-bordered">批量付款到卡</td>
        <td class="table table-bordered">
            <table style="table-layout:fixed;word-wrap:break-word" align="center" width="100%">
                <tr>
                  <th class="table table-bordered">模板1(普通快速均支持)</th>
                  <td class="table table-bordered">20130717865001^胡晓骅^招商银行^6214850211527582^上海市^上海市^招商银行股份有限公司上海天钥桥支行^1^2^付款到银行卡测试</td>
                </tr>
                <tr>
                  <th class="table table-bordered">模板2(仅快速支持)</th>
                  <td class="table table-bordered">20130717865001^胡晓骅^招商银行^6214850211527582^1^2^付款到银行卡测试</td>
                </tr>
            </table>
            
          </td>
    </tr>
    <tr>
        <td class="table table-bordered">批量退款</td>
        <td class="table table-bordered">20130717605001^78339311890304^2^付款到银行卡退款测试</td>
    </tr>
    <tr>
        <td class="table table-bordered">分账数据</td>
        <td class="table table-bordered">{sharingData:[{sharingType:"1",sharingIdentityType:"1",sharingIdentity:"1565237880",sharingAmount:"2",sharingFeePercent:"100",sharingMemo:""}]} </td>
    </tr>
  </table>
</div>
</body>
</html>