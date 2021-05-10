<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="/head.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>定期还款计划列表</title>
<script type="text/javascript">
function show(){
	var select = document.getElementById('selectEnv');
	var address = document.getElementById('url');
	var partnerId = document.getElementById('partner_id');
	var memberId  = document.getElementById('member_id');

	switch(select.value){
		case 'func':
			address.value='http://10.65.209.29:8090/wos/gateway';
			partnerId.value='200005485567';
			memberId.value = '10000000486899';
			break;
		case 'real':
			address.value='https://api.wexfin.com/wos/gateway';
			partnerId.value='200011807404';
			memberId.value = '10000000000015';
			break;
		default:
			address.value='';
			partnerId.value='';
			memberId.value = '';
			break;
	}			
}


function setRequestTime(){
	var requestTime = document.getElementById('request_time');
	requestTime.value = new Date().Format('yyyyMMddHHmmss');
}

function setLoginName(){
	var logName = document.getElementById('login_name');
	var rand = ("0000000" + 100000000 * Math.random()).match(/(\d{8})(\.|$)/)[1];
	logName.value = rand + "@qq.com";
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
  	  <div class="h4" >定期还款计划列表</div>
  	  	<div style="width: 800px">
  	  	<div>
  	      <form target="_blank" class="form-horizontal" role="form" name="login" action="../RegularRepaymentPlanList" method="post" accept-charset="utf-8" onsubmit="return checkUrl();">
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
    							<input type="text" name="service" class="form-control" id="service"  placeholder="接口名称" value="regular_repayment_plan_list">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="col-sm-2 control-label">member_id: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="member_id" class="form-control" id="member_id" placeholder="借款人ID" value="">
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
						<td><label class="col-sm-2 control-label">bill_begin_time: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="bill_begin_time" class="form-control" id="bill_begin_time" placeholder="账单起始时间" value="">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="col-sm-2 control-label">bill_end_time: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="bill_end_time" class="form-control" id="bill_end_time" placeholder="账单结束时间" value="">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="col-sm-2 control-label">bill_status: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="bill_status" class="form-control" id="bill_status" placeholder="账单状态(WAITING_VERIFY/VERIFIED)" value="">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="col-sm-2 control-label">repayment_begin_time: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="repayment_begin_time" class="form-control" id="repayment_begin_time" placeholder="最迟还款起始时间" value="">
    						</div>					
						</td>
						<td></td>
					</tr>
					
					<tr>
						<td><label class="col-sm-2 control-label">repayment_end_time: </label></td>
						<td>
    						<div class="col-sm-10">
    							<input type="text" name="repayment_end_time" class="form-control" id="repayment_end_time" placeholder="最迟还款结束时间" value="">
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