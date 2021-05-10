<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投资申请</title>
<script type="text/javascript">
function show(){
	var select = document.getElementById('selectEnv');
	var address = document.getElementById('url');
	var partnerId = document.getElementById('partner_id');
	var memberId = document.getElementById('member_id');
	var goodId = document.getElementById('goods_id');
	switch(select.value){
		case 'func':
			address.value='http://10.65.209.29:8090/wos/gateway';
			partnerId.value='200005485567';
			memberId.value = '10000000486901';
			goodId.value = '1029943';
			break;
		case 'real':
			address.value='https://api.wexfin.com/wos/gateway';
			partnerId.value='200011807404';
			memberId.value = '10000000000015';
			goodId.value ='1005806';
			break;
		default:
			address.value='';
			partnerId.value='';
			memberId.value = '';
			goodId.value = '';
			break;
	}			
}


function setRequestTime(){
	var requestTime = document.getElementById('request_time');
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
  	<div id="createMember">
  	  <div class="h4" >投资申请</div>
  	  	<div style="width: 800px">
  	  	<div>
  	      <form target="_blank" class="form-horizontal" role="form" name="login" action="../TradeInvestAdd" method="post" accept-charset="utf-8" onsubmit="return checkUrl();">
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
    								<option value="real">生产环境</option>
    								<option value="func">测试环境</option>
    							</select>
    						</div>					
						</td> 
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
    							<input type="text" name="request_time" class="form-control" id="request_time" placeholder="请求时间" value= "">
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
    							<input type="text" name="sign_type" class="form-control" id="sign_type" placeholder="签名类型" value="MD5">
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
						<td><label class="col-sm-2 control-label">platform_type: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="platform_type" class="form-control" id="platform_type" placeholder="平台类型" value="PC">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="col-sm-2 control-label" >service：</label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="service" class="form-control" id="service"  placeholder="接口名称" value="trade_invest_add">
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
						<td><label class="col-sm-2 control-label">goods_id: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="goods_id" class="form-control" id="goods_id" placeholder="商品ID" value="">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="col-sm-2 control-label">amount: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="amount" class="form-control" id="amount" placeholder="充值金额" value="10.00">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="col-sm-2 control-label">return_url: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="return_url" class="form-control" id="return_url" placeholder="会跳地址" value="http://www.baidu.com">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					
					<tr>
						<td><label class="col-sm-2 control-label">summary: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="summary" class="form-control" id="summary" placeholder="摘要信息" value="">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="col-sm-2 control-label">close_time: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="close_time" class="form-control" id="close_time" placeholder="交易关闭时间（分）" value="">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="col-sm-2 control-label">address_id: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="address_id" class="form-control" id="address_id" placeholder="收货地址ID" value="">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="col-sm-2 control-label">redpacket_id: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="redpacket_id" class="form-control" id="redpacket_id" placeholder="红包ID" value="">
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