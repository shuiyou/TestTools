<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style type="text/css">
.dropdown-submenu {
	position: relative;
}

.dropdown-submenu>.dropdown-menu {
	top: 0;
	left: 100%;
	margin-top: -6px;
	margin-left: -1px;
	-webkit-border-radius: 0 6px 6px 6px;
	-moz-border-radius: 0 6px 6px;
	border-radius: 0 6px 6px 6px;
}

.dropdown-submenu:hover>.dropdown-menu {
	display: block;
}

.dropdown-submenu>a:after {
	display: block;
	content: " ";
	float: right;
	width: 0;
	height: 0;
	border-color: transparent;
	border-style: solid;
	border-width: 5px 0 5px 5px;
	border-left-color: #ccc;
	margin-top: 5px;
	margin-right: -10px;
}

.dropdown-submenu:hover>a:after {
	border-left-color: #fff;
}

.dropdown-submenu.pull-left {
	float: none;
}

.dropdown-submenu.pull-left>.dropdown-menu {
	left: -100%;
	margin-left: 10px;
	-webkit-border-radius: 6px 0 6px 6px;
	-moz-border-radius: 6px 0 6px 6px;
	border-radius: 6px 0 6px 6px;
}

.dropdown-submenu ul li.current ul {
	display: block;
}
</style>
</head>
<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="/TestTools/welcome.jsp">质量控制中心</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown">常用网址<span class="caret"></span></a>

						<ul class="dropdown-menu">
							<li><a target="_blank"
								href="http://10.65.209.19:8088/uni-login/login">测试环境Guardian</a></li>
							<li><a target="_blank"
								href="https://testguardian.whintra.com/uni-login/login">联调环境Guardian</a></li>
							<li><a target="_blank"
								href="https://guardian.weibopay.com/uni-login/login">生产环境Guardian</a></li>
							<li><a target="_blank" href="http://ci.weihui.com/jenkins">Jenkins</a></li>
							<li><a target="_blank"
								href="http://wiki.weihui.com:9080/login.action">Wiki</a></li>
							<li><a target="_blank"
								href="http://jira.weihui.com:8080/secure/Dashboard.jspa">Jira</a></li>
							<li><a target="_blank"
								href="http://apex.weihui.com/ords/f?p=101">生产环境数据库查询</a></li>
							<li><a target="_blank"
								href="http://wiki.weihui.com:9080/display/env/Home">服务器部署列表</a></li>
							<li><a target="_blank" href="https://10.129.15.22/">生产环境跳板机</a></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown">接口测试<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a target="_blank" href="http://ci.weihui.com:9090/">接口自动化</a></li>
							<li class="dropdown-submenu"><a href="##">资金托管会员网关</a>
								<ul class="dropdown-menu">
									<!-- 使用绝对路径指定jsp页面 -->
									<li class="dropdown-submenu"><a href="##">认证信息</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/mgs/create_activate_member.jsp">创建激活会员</a></li>
											<li><a href="/TestTools/mgs/set_member_host_role.jsp">设置存管角色</a></li>
											<li><a href="/TestTools/mgs/set_real_name.jsp">设置实名信息</a></li>
											<li><a href="/TestTools/mgs/binding_verify.jsp">绑定认证信息</a></li>
											<li><a href="/TestTools/mgs/unbinding_verify.jsp">解绑认证信息</a></li>
											<li><a href="/TestTools/mgs/query_verify.jsp">查询认证信息</a></li>
											<li><a href="/TestTools/mgs/modify_verify_mobile.jsp">修改认证手机重定向</a></li>
											<li><a href="/TestTools/mgs/find_verify_mobile.jsp">找回认证手机重定向</a></li>
											<li><a href="/TestTools/mgs/set_pay_password.jsp">设置支付密码</a></li>
											<li><a href="/TestTools/mgs/modify_pay_password.jsp">修改支付密码</a></li>
											<li><a href="/TestTools/mgs/find_pay_password.jsp">找回支付密码</a></li>
											<li><a href="/TestTools/mgs/validate_pay_password.jsp">验证支付密码</a></li>
											<li><a href="/TestTools/mgs/query_is_set_pay_password.jsp">查询是否设置支付密码</a></li>
											<li><a href="/TestTools/mgs/open_account.jsp">单独开户</a></li>
											<li><a href="/TestTools/mgs/query_security_info.jsp">查询用户安全信息认证</a></li>
											<li><a href="/TestTools/mgs/web_real_name_pic_auth.jsp">身份证图片认证重定向</a></li>
											<li><a href="/TestTools/mgs/set_merchant_config.jsp">商户更新修改ACS配置</a></li>
											<li><a href="/TestTools/mgs/real_name_pic_auth.jsp">实名图像实时认证</a></li>
											<li><a href="/TestTools/mgs/init_member_by_process.jsp">综合初始化会员</a></li>
											<li><a href="/TestTools/mgs/query_identity_photo.jsp">水纹照</a></li>
											<li><a href="/TestTools/mgs/query_merchant_config.jsp">商户委托扣款配置参数查询</a></li>
											<li><a href="/TestTools/mgs/query_hostbank_account_info.jsp">查询托管银行账户账号</a></li>
											<li><a href="/TestTools/mgs/merchant_report.jsp">商户信息报备</a></li>
										</ul></li>

									<li class="dropdown-submenu"><a href="##">余额收支明细</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/mgs/query_balance.jsp">查询余额</a></li>
											<li><a href="/TestTools/mgs/query_account_details.jsp">查询收支明细</a></li>
											<li><a href="/TestTools/mgs/balance_freeze.jsp">冻结余额</a></li>
											<li><a href="/TestTools/mgs/balance_unfreeze.jsp">解冻余额</a></li>
											<li><a href="/TestTools/mgs/query_fund_quota.jsp">查询基金快赎额度余额</a></li>
											<li><a href="/TestTools/mgs/query_ctrl_result.jsp">查询冻结解冻结果</a></li>
											<li><a href="/TestTools/mgs/query_middle_account.jsp">查询中间账户</a></li>
											<li><a href="/TestTools/mgs/query_all_balance.jsp">身份证关联信息查询</a></li>
											<li><a href="/TestTools/mgs/query_trade_related_no.jsp">查询交易关联号下可代付金额</a></li>
											
											<li><a
												href="/TestTools/mgs/query_is_set_bank_password.jsp">查询是否设置银行密码</a></li>
										</ul></li>
										
									<li class="dropdown-submenu"><a href="##">子商户平台</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/mgs/smerchant_url_get.jsp">获取子商户链接</a></li>
										</ul></li>


									<li class="dropdown-submenu"><a href="##">银行卡</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/mgs/binding_bank_card.jsp">绑定银行卡</a></li>
											<li><a href="/TestTools/mgs/page_binding_bank_card.jsp">绑定银行卡（上海，华瑞）</a></li>
											<li><a
												href="/TestTools/mgs/binding_bank_card_advance.jsp">绑卡推进</a></li>
											<li><a href="/TestTools/mgs/unbinding_bank_card.jsp">解绑银行卡</a></li>
											<li><a
												href="/TestTools/mgs/unbinding_bank_card_advance.jsp">解绑银行卡推进</a></li>
											<li><a href="/TestTools/mgs/query_bank_card.jsp">查询银行卡</a></li>
											<li><a href="/TestTools/mgs/web_binding_bank_card.jsp">我的银行卡</a></li>
											<li><a href="/TestTools/mgs/change_bank_mobile.jsp">修改银行卡预留手机</a></li>
											<li><a href="/TestTools/mgs/change_bank_mobile_advance.jsp">修改银行卡预留手机推进</a></li>
											<li><a href="/TestTools/mgs/show_member_infos_sina.jsp">sina页面展示用户信息</a></li>
											<li><a href="/TestTools/mgs/web_member_infos.jsp">个人中心重定向</a></li>

										</ul></li>


									<li class="dropdown-submenu"><a href="##">企业会员资质</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/mgs/audit_member_infos.jsp">请求审核企业会员资质</a></li>
											<li><a href="/TestTools/mgs/smt_fund_agent_buy.jsp">请求经办人信息</a></li>
											<li><a href="/TestTools/mgs/query_fund_agent_buy.jsp">查询经办人信息</a></li>
											<li><a href="/TestTools/mgs/query_member_infos.jsp">查询企业会员信息</a></li>
											<li><a href="/TestTools/mgs/query_audit_result.jsp">查询企业会员审核结果</a></li>
										</ul></li>

									<li class="dropdown-submenu"><a href="##">代扣</a>
										<ul class="dropdown-menu">
											<li><a
												href="/TestTools/mgs/handle_withhold_authority.jsp">代扣授权重定向</a></li>
											<li><a
												href="/TestTools/mgs/modify_withhold_authority.jsp">修改代扣授权重定向</a></li>
											<li><a
												href="/TestTools/mgs/relieve_withhold_authority.jsp">解除代扣授权重定向</a></li>
											<li><a
												href="/TestTools/mgs/query_withhold_authority.jsp">查看用户是否代扣授权</a></li>
										</ul></li>

								</ul></li>
							<li class="dropdown-submenu"><a href="##">EGS会员网关</a>
								<ul class="dropdown-menu">
									<li><a href="/TestTools/egs/egs_change_bindingcard.jsp">EGS会员换绑卡通知</a></li>
									<li><a href="/TestTools/egs/egs_member_revoke.jsp">EGS注销会员</a></li>

								</ul></li>
								<li class="dropdown-submenu"><a href="##">闪付</a>
								<ul class="dropdown-menu">
									<li><a href="/TestTools/mgs/quick_pass.jsp">闪付</a></li>

								</ul></li>

                           	<li class="dropdown-submenu"><a href="##">统一网关</a>
								<ul class="dropdown-menu">
									<!-- 使用绝对路径指定jsp页面 -->
									<li class="dropdown-submenu"><a href="##">保险支付</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/ugs/create_insure_trade.jsp">创建投保交易</a></li>
											<li><a href="/TestTools/ugs/create_surrender_trade.jsp">创建退保交易</a></li>
											<li><a href="/TestTools/ugs/query_insure_trade.jsp">查询投保订单</a></li>
											<li><a href="/TestTools/ugs/query_surrender_trade.jsp">查询退保订单</a></li>
										</ul></li>
									<li class="dropdown-submenu"><a href="##">跨境支付</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/ugs/cross_create_trade.jsp">人民币收单</a></li>
											<li><a href="/TestTools/ugs/cross_query_trade.jsp">收单订单查询</a></li>
											<li><a href="/TestTools/ugs/cross_pay_trade.jsp">收单再次支付</a></li>
											<li><a href="/TestTools/ugs/cross_refund.jsp">退款</a></li>
											<li><a href="/TestTools/ugs/cross_query_refund.jsp">退款订单查询</a></li>
											<li><a href="/TestTools/ugs/cross_apply_inbound.jsp">申请入境下发</a></li>
											<li><a href="/TestTools/ugs/cross_query_inbound_batch.jsp">入境批次查询</a></li>
											<li><a href="/TestTools/ugs/cross_query_inbound_detail.jsp">入境明细查询</a></li>
										</ul></li>
								</ul></li>
								
							<li class="dropdown-submenu"><a href="##">资金托管交易网关</a>
								<ul class="dropdown-menu">

									<li class="dropdown-submenu"><a href="##">资金托管代付</a>
										<ul class="dropdown-menu">
											<li><a
												href="/TestTools/mas/create_single_hosting_pay_trade.jsp">创建单笔托管代付到账户交易</a></li>
											<li><a
												href="/TestTools/mas/create_single_hosting_pay_to_card_trade.jsp">创建单笔托管代付到卡交易</a></li>
											<li><a
												href="/TestTools/mas/create_batch_hosting_pay_trade.jsp">创建批量托管代付到账户交易</a></li>
											<li><a
												href="/TestTools/mas/create_batch_hosting_pay_to_card_trade.jsp">创建批量托管代付到卡交易</a></li>
										</ul></li>
									<li class="dropdown-submenu"><a href="##">预授权</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/mas/create_pre_auth_trade.jsp">预授权申请</a></li>
											<li><a href="/TestTools/mas/finish_pre_auth_trade.jsp">预授权完成</a></li>
											<li><a href="/TestTools/mas/cancel_pre_auth_trade.jsp">预授权撤销</a></li>
										</ul></li>
									<li class="dropdown-submenu"><a href="##">支付和转账</a>
										<ul class="dropdown-menu">
											<li><a
												href="/TestTools/mas/create_hosting_collect_trade.jsp">创建托管代收交易</a></li>
											<li><a href="/TestTools/mas/pay_hosting_trade.jsp">托管交易支付</a></li>
											<li><a href="/TestTools/mas/create_hosting_transfer.jsp">转账接口</a></li>
											<li><a href="/TestTools/mas/advance_hosting_pay.jsp">支付推进</a></li>
											<li><a
												href="/TestTools/mas/file_hosting_collect_trade.jsp">代收文件交易</a></li>
											<li><a href="/TestTools/mas/p2p_repay_compensation.jsp">还代偿交易</a></li>
											<li><a href="/TestTools/mas/p2p_special_deposit.jsp">子账户入金交易</a></li>
											<li><a href="/TestTools/mas/p2p_special_withdraw.jsp">子账户出金交易</a></li>
											<li><a
												href="/TestTools/mas/create_single_hosting_instant_trade.jsp">资金托管及时到账交易</a></li>

										</ul></li>
									<li class="dropdown-submenu"><a href="##">查询接口</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/mas/query_pay_result.jsp">支付结果查询</a></li>
											<li><a href="/TestTools/mas/query_hosting_trade.jsp">托管交易查询</a></li>
											<li><a href="/TestTools/mas/query_hosting_batch_trade.jsp">托管交易批次查询</a></li>
											<li><a href="/TestTools/mas/query_hosting_refund.jsp">托管退款查询</a></li>
											<li><a href="/TestTools/mas/query_hosting_deposit.jsp">托管充值查询</a></li>
											<li><a href="/TestTools/mas/query_hosting_withdraw.jsp">托管提现查询</a></li>
											<li><a href="/TestTools/mas/query_fund_yield.jsp">查存钱罐收益率</a></li>
											<li><a href="/TestTools/mgs/query_fund_quota.jsp">查询基金单日限额</a></li>
										</ul></li>
									<li class="dropdown-submenu"><a href="##">充值、提现、退款</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/mas/create_hosting_deposit.jsp">托管充值</a></li>
											<li><a href="/TestTools/mas/create_hosting_withdraw.jsp">托管提现</a></li>
											<li><a href="/TestTools/mas/create_hosting_refund.jsp">托管退款</a></li>
										</ul></li>
									<li class="dropdown-submenu"><a href="##">基金支付</a>
										<ul class="dropdown-menu">
											<li><a
												href="/TestTools/mas/fund_create_purchase_trade.jsp">创建申购交易</a></li>
											<li><a
												href="/TestTools/mas/fund_create_purchase_refund.jsp">撤单交易</a></li>
											<li><a
												href="/TestTools/mas/fund_query_purchase_trade.jsp">申购交易查询</a></li>
											<li><a
												href="/TestTools/mas/fund_query_purchase_refund.jsp">撤单交易查询</a></li>
											<li><a href="/TestTools/mas/fund_apply_redeem_pay.jsp">申请赎回代付</a></li>
											<li><a href="/TestTools/mas/fund_apply_purchase_pay.jsp">申请申购代付</a></li>
											<li><a
												href="/TestTools/mas/fund_query_redeem_detail.jsp">赎回代付查询</a></li>
											<li><a href="/TestTools/mas/fund_redeem_pay_retry.jsp">赎回重试</a></li>
										</ul></li>
									<li class="dropdown-submenu"><a href="##">P2P</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/mas/create_bid_info.jsp">创建标的</a></li>
											<li><a href="/TestTools/mas/create_bid_info_temp.jsp">创建标的加密</a></li>
											<li><a href="/TestTools/mas/create_debt_migration.jsp">债转迁移</a></li>
											<li><a
												href="/TestTools/mas/create_p2p_hosting_borrowing_target.jsp">创建标的(旧版)</a></li>
											<li><a href="/TestTools/mas/query_bid.jsp">查询标的</a></li>
											<li><a href="/TestTools/mas/cancel_bid.jsp">撤销标的</a></li>
										</ul></li>

								</ul></li>
							<li class="dropdown-submenu"><a href="##">新B2C收单网关</a>
								<ul class="dropdown-menu">
									<li><a href="/TestTools/b2c/create_instant_order.jsp">创建即时到帐交易</a></li>
									<li><a href="/TestTools/b2c/create_ensure_order.jsp">创建担保交易</a></li>
									<li><a href="/TestTools/b2c/confirm_ensure_order.jsp">确认收货</a></li>
									<li><a href="/TestTools/b2c/pay_b2c_order.jsp">交易支付</a></li>
									<li><a href="/TestTools/b2c/query_b2c_order.jsp">交易查询(对账查询)</a></li>
									<li><a href="/TestTools/b2c/query_b2c_refund.jsp">退款查询</a></li>
									<li><a href="/TestTools/b2c/query_b2c_batch_fundout_order.jsp">B2C批量出款查询</a></li>
									<li><a href="/TestTools/b2c/close_b2c_order.jsp">订单关闭</a></li>
									<li><a href="/TestTools/b2c/create_close_delay.jsp">延长订单关闭时间</a></li>
									<li><a href="/TestTools/b2c/create_b2c_refund.jsp">交易退款</a></li>
									<li><a href="/TestTools/b2c/create_single_pay2account.jsp">单笔付款到账户</a></li>
									<li><a href="/TestTools/b2c/create_batch_pay2account.jsp">批量付款到账户</a></li>
									<li><a href="/TestTools/b2c/create_single_pay2bank.jsp">单笔付款到银行卡</a></li>
									<li><a href="/TestTools/b2c/create_batch_pay2bank.jsp">批量付款到银行卡</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a href="##">量子支付</a>
								<ul class="dropdown-menu">
									<li><a href="/TestTools/b2c/create_b2c_order.jsp">交易创建</a></li>
									<li><a href="/TestTools/b2c/receipt_split.jsp">分账</a></li>
									<li><a href="/TestTools/b2c/query_receipt_split.jsp">分账查询</a></li>
									<li><a href="/TestTools/b2c/receipt_refund_split.jsp">退分账</a></li>
									<li><a href="/TestTools/b2c/pay_order.jsp">交易推进</a></li>
									<li><a href="/TestTools/b2c/settle_b2c_order.jsp">交易结算</a></li>
									<li><a href="/TestTools/mas/cancel_b2c_order.jsp">交易撤销</a></li>
									<li><a href="/TestTools/mas/extend_settle_time.jsp">延长交易时间</a></li>
								</ul>
							</li>


							<li class="dropdown-submenu"><a href="##">支付工具接口</a>
								<ul class="dropdown-menu">
									<li><a href="/TestTools/pas/validate_identity.jsp">证件信息校验</a></li>
									<li><a href="/TestTools/pas/validate_phone_id.jsp">手机实名信息校验</a></li>
									<li><a href="/TestTools/pas/validate_bank_card.jsp">银行卡验证</a></li>
									<li><a
										href="/TestTools/pas/validate_bank_card_advance.jsp">银行卡验证推进</a></li>
									<li><a href="/TestTools/pas/auth_identity.jsp">身份授权接口</a></li>
									<li><a href="/TestTools/pas/auth_identity_advance.jsp">身份授权接口推进</a></li>
									<li><a
										href="/TestTools/pas/validate_identity_return_photo.jsp">身份返照接口</a></li>
									<li><a href="/TestTools/pas/validate_identity_pic.jsp">OCR身份信息接口</a></li>
									<li><a href="/TestTools/pas/validate_identity_pic_new_encrypt.jsp">OCR身份信息优化</a></li>
									<li><a href="/TestTools/pas/one_to_one_face_compare.jsp">一比一人脸比对</a></li>
									<li><a href="/TestTools/pas/one_to_one_face_compare_new_encrypt.jsp">一比一人脸比对(优化)</a></li>
									<li><a href="/TestTools/pas/validate_id_photo.jsp">人证比对不返照</a></li>
									<li><a href="/TestTools/pas/validate_id_photo_new_encrypt.jsp">人证比对不返照(优化)</a></li>
									<li><a href="/TestTools/pas/validate_id_photo_return_photo.jsp">人证比对返照</a></li>
									<li><a href="/TestTools/pas/validate_id_photo_return_photo_new_encrypt.jsp">人证比对返照(优化)</a></li>
									<li><a href="/TestTools/pas/query_loan.jsp">多头借贷查询</a></li>
									<li><a href="/TestTools/pas/query_bill.jsp">查询账单接口</a></li>
									<li><a href="/TestTools/pas/query_report.jsp">个人报告查询接口</a></li>
									<li><a href="/TestTools/pas/validate_education.jsp">查询教育接口</a></li>
									<li><a href="/TestTools/pas/query_erp_info.jsp">查询企业信息</a></li>
									<li><a href="/TestTools/pas/query_erp_error_info.jsp">查询企业异常信息</a></li>
									<li><a href="/TestTools/pas/query_erp_list_pages.jsp">企业列表信息查询接口</a></li>
									<li><a href="/TestTools/pas/validate_out_invest.jsp">自然人对外投资查询接口</a></li>
									<li><a href="/TestTools/pas/query_branch_list_pages.jsp">银行分支行信息查询接口</a></li>
									<li><a href="/TestTools/pas/query_flow_statis.jsp">账单查询接口(pas流量查询)</a></li>
									<li><a>敬请期待...</a></li>
								</ul></li>
							<li class="dropdown-submenu"><a href="##">老B2C收单网关</a>
								<ul class="dropdown-menu">
									<li><a target="_blank"
										href="https://func-mas-order.weibopay.com/acquire-order-channel/test.html">MAS收单</a></li>
									<li><a target="_blank"
										href="https://func-mas-order.weibopay.com/acquire-order-channel/test_refund.html">MAS退款</a></li>
									<li><a target="_blank"
										href="http://func-mas-order.weibopay.com/acquire-order-channel/test_batch_pay2bankcard.html">批量付款</a></li>
									<li><a target="_blank"
										href="http://func-mas-order.weibopay.com/acquire-order-channel/test_batch_rapid_pay2bankcard.html">批量付款(快速)</a></li>
									<li><a target="_blank"
										href="http://func-mas-order.weibopay.com/acquire-order-channel/test_batch_refund.html">批量退款</a></li>
									<li><a target="_blank"
										href="https://func-mas-order.weibopay.com/acquire-order-channel/test_query.html">订单查询</a></li>
									<li><a target="_blank"
										href="http://func-mas-order.weibopay.com/acquire-order-channel/test_cancel.html">订单关闭</a></li>
								</ul></li>


							<li class="dropdown-submenu"><a href="##">wos开放平台</a>
								<ul class="dropdown-menu">
									<!-- 使用绝对路径指定jsp页面 -->
									<li class="dropdown-submenu"><a href="##">交易类接口</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/wos/trade_deposit_add.jsp">充值申请</a></li>
											<li><a href="/TestTools/wos/trade_withdraw_add.jsp">提现申请</a></li>
											<li><a href="/TestTools/wos/trade_invest_add.jsp">投资申请</a></li>
											<li><a href="/TestTools/wos/trade_repayment_add.jsp">还款申请</a></li>
										</ul></li>

									<li class="dropdown-submenu"><a href="##">会员类接口</a>
										<ul class="dropdown-menu">
											<li><a
												href="/TestTools/wos/personal_member_info_add.jsp">创建个人会员</a></li>
											<li><a href="/TestTools/wos/company_member_info_add.jsp">创建企业会员</a></li>
											<li><a href="/TestTools/wos/member_info_get_by_id.jsp">根据会员ID查询</a></li>
										</ul></li>

									<li class="dropdown-submenu"><a href="##">定期类接口</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/wos/regular_asset_list.jsp">定期资产列表</a></li>
											<li><a href="/TestTools/wos/regular_loan_list.jsp">定期借款列表</a></li>
											<li><a
												href="/TestTools/wos/regular_repayment_plan_list.jsp">定期还款计划列表</a></li>
										</ul></li>

									<li class="dropdown-submenu"><a href="##">营销类接口</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/wos/coupon_consumable_list.jsp">指定交易可用红包列表查询</a></li>
											<li><a href="/TestTools/wos/coupon_list.jsp">用户已持有的红包查询</a></li>
											<li><a href="/TestTools/wos/coupon_get.jsp">红包详情查询</a></li>
										</ul></li>

									<li class="dropdown-submenu"><a href="##">商品类接口</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/wos/goods_info_get.jsp">定期理财商品单笔查询</a></li>
											<li><a href="/TestTools/wos/goods_sold_state_get.jsp">销售状态单笔查询</a></li>
											<li><a href="/TestTools/wos/goods_canbuy_get.jsp">查询商品是否可买</a></li>
											<li><a href="/TestTools/wos/goods_invest_list.jsp">投资记录批量查询</a></li>
										</ul></li>

									<li class="dropdown-submenu"><a href="##">站点信息接口</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/wos/site_banner_list.jsp">站点banner(轮播位)批量查询</a></li>
											<li><a href="/TestTools/wos/site_notice_list.jsp">站点公告批量查询</a></li>
											<li><a href="/TestTools/wos/site_recommend_list.jsp">站点推荐位批量查询</a></li>
											<li><a href="/TestTools/wos/site_usr_msg_list.jsp">用户站内信批量查询</a></li>
										</ul></li>

								</ul></li>
							<li class="dropdown-submenu"><a href="##">基金网关 </a>
								<ul class="dropdown-menu">
									<!-- 使用绝对路径指定jsp页面 -->
									<li class="dropdown-submenu"><a href="##">账户管理</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/fund/fund_member_add.jsp">创建会员</a></li>
											<li><a href="/TestTools/fund/fund_member_base_update.jsp">修改会员信息</a></li>
											<li><a href="/TestTools/fund/fund_ta_acct_get.jsp">查询TA账号</a></li>
											<li><a href="/TestTools/fund/fund_member_password_update.jsp">设置交易密码</a></li>
											<li><a href="/TestTools/fund/fund_member_password_check.jsp">校验交易密码</a></li>
											<li><a href="/TestTools/fund/fund_member_bind_bankcard_add.jsp">绑定银行卡</a></li>
											<li><a href="/TestTools/fund/fund_member_bind_bankcard_advance.jsp">绑定银行卡推进</a></li>
											<li><a href="/TestTools/fund/fund_member_bank_list_get.jsp">查询支持银行卡列表</a></li>
											<li><a href="/TestTools/fund/fund_member_verifyinfo_check.jsp">校验用户实名</a></li>
											<li><a href="/TestTools/fund/fund_member_bind_phone_add.jsp">绑定手机号</a></li>
											<li><a href="/TestTools/fund/fund_member_identity_update.jsp">更新会员标识</a></li>
											<li><a href="/TestTools/fund/fund_member_unbind_bankcard_add.jsp">解绑银行卡</a></li>
											<li><a href="/TestTools/fund/fund_member_account_by_cert_get.jsp">查询会员信息</a></li>
											<li><a href="/TestTools/fund/fund_member_profit_detail_get.jsp">日收益查询</a></li>
											<li><a href="/TestTools/fund/fund_member_bank_update_mobile.jsp">修改银行预留手机号</a></li>
											<li><a href="/TestTools/fund/fund_member_bank_update_mobile_advance.jsp">修改银行预留手机号推进</a></li>
											<li><a href="/TestTools/fund/fund_member_payment_method_list_get.jsp">查询银行卡列表</a></li>
											<li><a href="/TestTools/fund/fund_member_account_get.jsp">获取会员信息</a></li>
											<li><a href="/TestTools/fund/fund_member_investor_update.jsp">更新投资者信息</a></li>
										</ul></li>

									<li class="dropdown-submenu"><a href="##">风险评测</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/fund/fund_risk_survey_list_get.jsp">获取风险测评调查问卷</a></li>
											<li><a href="/TestTools/fund/fund_risk_answer_survey_add.jsp">提交风险测评调查问卷</a></li>
										</ul></li>

									<li class="dropdown-submenu"><a href="##">换卡相关</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/fund/fund_member_change_bankcard_check.jsp">银行卡换卡检查</a></li>
											<li><a href="/TestTools/fund/fund_member_change_bankcard.jsp">银行卡换卡</a></li>
											<li><a href="/TestTools/fund/fund_member_change_bankcard_get.jsp">查询银行卡换卡结果</a></li>
										</ul></li>
									<li class="dropdown-submenu"><a href="##">验卡以及推进</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/fund/fund_member_card_bind_validate.jsp">验证绑卡</a></li>
											<li><a href="/TestTools/fund/fund_member_card_bind_validate_push.jsp">验证绑卡推进</a></li>
										</ul></li>
									<li class="dropdown-submenu"><a href="##">存钱罐相关</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/fund/fund_deposit_add.jsp">存钱罐充值</a></li>
											<li><a href="/TestTools/fund/fund_withdraw_add.jsp">存钱罐提现</a></li>
											<li><a href="/TestTools/fund/trade_deposit_add_new.jsp">网银存钱罐充值</a></li>
											<li><a href="/TestTools/fund/member_account_balance_get.jsp">查询存钱罐余额</a></li>
											<li><a href="/TestTools/fund/member_account_detail_list.jsp">查询存钱罐交易明细</a></li>
											<li><a href="/TestTools/fund/saving_pot_fund_profit_list.jsp">查询存钱罐收益率</a></li>
										</ul></li>
									<li class="dropdown-submenu"><a href="##">基金交易</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/fund/fund_trade_buy_order_add.jsp">申购基金</a></li>
											<li><a href="/TestTools/fund/fund_trade_combination_order_add.jsp">一键下单</a></li>
											<li><a href="/TestTools/fund/fund_trade_order_cancel.jsp">基金撤单</a></li>
											<li><a href="/TestTools/fund/fund_trade_redeem_order_add.jsp">赎回基金</a></li>
											<li><a href="/TestTools/fund/fund_trade_convert_order_add.jsp">基金转换</a></li>
											<li><a href="/TestTools/fund/fund_trade_redeem_and_buy_order_add.jsp">基金赎回转申购</a></li>
											<li><a href="/TestTools/fund/fund_dividend_method_order_add.jsp">修改分红方式</a></li>
										</ul></li>
									<li class="dropdown-submenu"><a href="##">基金定投</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/fund/fund_invest_plan_add.jsp">创建基金定投</a></li>
											<li><a href="/TestTools/fund/fund_invest_plan_update.jsp">修改定投规则</a></li>
											<li><a href="/TestTools/fund/fund_invest_plan_status_update.jsp">修改定投协议状态</a></li>
											<li><a href="/TestTools/fund/fund_invest_plan_list_get.jsp">获取基金定投列表</a></li>
											<li><a href="/TestTools/fund/fund_invest_plan_order_list_get.jsp">查询基金定投交易记录</a></li>
										</ul></li>
									<li class="dropdown-submenu"><a href="##">数据查询</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/fund/fund_product_list_get.jsp">查询基金列表</a></li>
											<li><a href="/TestTools/fund/fund_trade_limit_get.jsp">查询基金交易限制</a></li>

										</ul></li>
									<li class="dropdown-submenu"><a href="##">交易查询</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/fund/fund_order_get.jsp">查询单笔委托交易</a></li>
											<li><a href="/TestTools/fund/fund_member_share_list_get.jsp">查询客户在途及持有份额</a></li>
											<li><a href="/TestTools/fund/fund_estimate_buy_fee_get.jsp">查询交易手续费及折扣</a></li>
											<li><a href="/TestTools/fund/fund_dividend_list_get.jsp">查询分红明细</a></li>
											<li><a href="/TestTools/fund/fund_trade_date_get.jsp">查询交易日</a></li>
											<li><a href="/TestTools/fund/fund_order_list_get.jsp">查询委托申请交易</a></li>
											<li><a href="/TestTools/fund/fund_confirm_order_list_get.jsp">查询委托确认记录</a></li>
											<li><a href="/TestTools/fund/fund_member_total_profit_get.jsp">查询用户总收益</a></li>
											<li><a href="/TestTools/fund/fund_member_trade_account_no_get.jsp">查询用户交易账号</a></li>
											<li><a href="/TestTools/fund/fund_rate_and_discount_get.jsp">查询交易费率及折扣率</a></li>
											<li><a href="/TestTools/fund/trade_detail_get.jsp">交易订单查询</a></li>
											<li><a href="/TestTools/fund/fund_trade_order_no_get.jsp">获取交易订单号</a></li>
										</ul></li>
								</ul></li>

							<li class="dropdown-submenu"><a href="##">上海银行网关</a>
								<ul class="dropdown-menu">
									<!-- 使用绝对路径指定jsp页面 -->
									<li class="dropdown-submenu"><a href="##">会员网关</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/sh_bank/init_member_by_process.jsp">综合创建激活会员</a></li>
											<li><a href="/TestTools/sh_bank/query_member_infos.jsp">查询会员信息</a></li>
											<li><a href="/TestTools/sh_bank/set_trade_password_page.jsp">设置交易密码</a></li>
											<li><a href="/TestTools/sh_bank/modify_pay_password_page.jsp">修改支付密码</a></li>
											<li><a href="/TestTools/sh_bank/find_pay_passwor_page.jsp">找回支付密码</a></li>
											<li><a href="/TestTools/sh_bank/modify_verify_mobile_page.jsp">修改认证手机</a></li>
											<li><a href="/TestTools/sh_bank/find_verify_mobile_page.jsp">找回认证手机</a></li>
											<li><a href="/TestTools/sh_bank/binding_bank_card.jsp">绑定银行卡</a></li>
											<li><a href="/TestTools/sh_bank/unbinding_bank_card.jsp">解绑银行卡</a></li>
											<li><a href="/TestTools/sh_bank/query_balance.jsp">查询客户子账号余额</a></li>
											<li><a href="/TestTools/sh_bank/query_account_details.jsp">查询客户子账号明细</a></li>
											<li><a href="/TestTools/sh_bank/sign_auto_apply_page.jsp">P2P委托扣款签约申请</a></li>
											<li><a href="/TestTools/sh_bank/terminate_sign_apply_page.jsp">P2P解除委托扣款签约申请</a></li>
											<li><a href="">上传实名图像</a></li>
											<li><a href="">上传P2P标的交易债权信息</a></li>
										</ul>
									</li>
									<li class="dropdown-submenu"><a href="##">交易网关</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/sh_bank/account_fund_in.jsp">客户子账户入金</a></li>
											<li><a href="/TestTools/sh_bank/account_fund_out.jsp">客户子账户出金</a></li>
											<li><a href="/TestTools/sh_bank/account_fund_transfer.jsp">客户子账号转账</a></li>
											<li><a href="/TestTools/sh_bank/account_fund_advance.jsp">客户子账号交易推进</a></li>
											<li><a href="/TestTools/sh_bank/create_bid_info.jsp">P2P标的信息录入</a></li>
											<li><a href="/TestTools/sh_bank/query_bid_info.jsp">P2P标的信息查询</a></li>
											<li><a href="/TestTools/sh_bank/cancel_bid_info.jsp">P2P标的信息撤销</a></li>
											<li><a href="">查询账户交易结果</a></li>
											<li><a href="/TestTools/sh_bank/account_fundout_page.jsp">客户子账号提现</a></li>
											<li><a href="/TestTools/sh_bank/bid_apply_page.jsp">P2P投标申请</a></li>
											<li><a href="/TestTools/sh_bank/debt_assign_apply_page.jsp">P2P债券转让申请</a></li>
											<li><a href="/TestTools/sh_bank/pay_back_apply_page.jsp">P2P还款申请页面</a></li>

										</ul>
									</li>
								</ul>
							</li>

							<li class="dropdown-submenu"><a href="##">微钱包网关</a>
								<ul class="dropdown-menu">
									<!-- 使用绝对路径指定jsp页面 -->
									<li class="dropdown-submenu"><a href="##">微钱包登录注册</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/wgs/login_send_verify_code.jsp">发送验证码</a></li>
											<li><a href="/TestTools/wgs/login_check_verify_code.jsp">校验登陆验证码</a></li>
											<li><a href="/TestTools/wgs/login_get_salt.jsp">获取盐</a></li>
											<li><a href="/TestTools/wgs/register.jsp">注册</a></li>
											<li><a href="/TestTools/wgs/Login_Reset_Password_CheckReal_Name.jsp">重置密码-校验实名信息</a></li>
											<li><a href="/TestTools/wgs/Login_Reset_Password_Reset.jsp">重置密码</a></li>
											<li><a href="/TestTools/wgs/login_check_password.jsp">校验登陆密码</a></li>
											<li><a href="/TestTools/wgs/Login_ResetPassword_CheckVerifyCode.jsp">重置密码-校验验证码</a></li>

										</ul>
									</li>
									<li class="dropdown-submenu"><a href="##">个人中心</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/wgs/personal_certify_getsummary.jsp">获取认证信息</a></li>
											<li><a href="/TestTools/wgs/basic_getsalt.jsp">授权basic获取盐</a></li>
											<li><a href="/TestTools/wgs/basic_send_verify_code.jsp">basic发送验证码</a></li>
											<li><a href="/TestTools/wgs/personal_certify_checkrealname.jsp">认证实名</a></li>
											<li><a href="/TestTools/wgs/Basic_Resend_VerifyCode.jsp">重发验证码</a></li>
											<li><a href="/TestTools/wgs/personal_certify_checkIdentityCard.jsp">认证证件</a></li>
										</ul>
									</li>
									<li class="dropdown-submenu"><a href="##">基础功能</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/wgs/basic_versioncheck.jsp">获取最新版本/延期访问令牌</a></li>
										</ul>
									</li>

									<li class="dropdown-submenu"><a href="##">银行卡</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/wgs/personal_bank_card_get_summary.jsp">查询银行卡列表</a></li>
											<li><a href="/TestTools/wgs/Personal_BankCard_Binding.jsp">绑定银行卡</a></li>
											<li><a href="/TestTools/wgs/Personal_BankCard_BindingAd.jsp">绑定银行卡推进</a></li>
											<li><a href="/TestTools/wgs/Personal_BankCard_Unbinding.jsp">解绑银行卡</a></li>
											<li><a href="/TestTools/wgs/Personal_BankCard_QueryCardBin.jsp">卡bin鉴权</a></li>
											<li><a href="/TestTools/wgs/Personal_BankCard_SupportBank.jsp">查询支持银行</a></li>
											<li><a href="/TestTools/wgs/Personal_BankCard_ChangeMobile.jsp">更换银行预留手机号</a></li>
											<li><a href="/TestTools/wgs/Personal_BankCard_ChangeMobileAd.jsp">更换银行预留手机号推进</a></li>

										</ul>
									</li>

									<li class="dropdown-submenu"><a href="##">存钱罐</a>
										<ul class="dropdown-menu">
											<li><a href="/TestTools/wgs/Saving_Pot_Estimate_Yield.jsp">预计收益/存钱罐充值/存钱罐提现</a></li>
											<li><a href="/TestTools/wgs/Savingpot_accountDetail_query.jsp">查询收支明细</a></li>
											<li><a href="/TestTools/wgs/SavingPot_DepositAndWithdraw_detail.jsp">充值订单详情/提现详情</a></li>
											<li><a href="/TestTools/wgs/SavingPot_YieldQuery.jsp">查询收益情况/查询7日年化收益统计/查询万份收益统计</a></li>
											<li><a href="/TestTools/wgs/savingpot_common_annualizedYield.jsp">查询七日年化收益</a></li>
										</ul>
									</li>
								</ul>
							</li>



							<li class="dropdown-submenu"><a href="##">消费支付</a>
                                <ul class="dropdown-menu">
                                    <!-- 使用绝对路径指定jsp页面 -->
                                    <li class="dropdown-submenu"><a href="##">消费支付</a>
                                        <ul class="dropdown-menu">
                                            <li><a href="/TestTools/consume_pay/create_single_consume_trade.jsp">创建单笔消费交易接口</a></li>
                                            <li><a href="/TestTools/consume_pay/create_single_consume_transfer.jsp">创建单笔消费转账接口</a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
						</ul>
					</li>


					<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown">实用工具<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/TestTools/tools/clean_member.jsp">清除会员</a></li>
							<li><a href="/TestTools/tools/clean_member_info.jsp">清除会员信息</a></li>
							<li><a href="/TestTools/tools/insert_inner_account.jsp">创建dpm内部户</a></li>
							<li><a href="/TestTools/tools/clean_erm_info.jsp">清楚新企业会员erm库数据</a></li>
							<li><a href="/TestTools/tools/encrypt_and_decrypt.jsp">加密/解密</a></li>
							<li><a href="/TestTools/tools/urlencode_and_urldecode.jsp">URL编码/解码</a></li>
							<li><a href="/TestTools/tools/clean_member_test.jsp">清除会员(联调)</a></li>
							<li><a href="/TestTools/tools/clean_member_info_test.jsp">清除会员信息(联调)</a></li>
							<li><a href="/TestTools/tools/clean_cmember.jsp">云金融会员一键清除</a></li>
							<li><a href="/TestTools/tools/bank_data_record.jsp">银行测试数据记录</a></li>
							<li><a href="/TestTools/tools/query_bank_data.jsp">银行测试数据查询</a></li>
							<li><a href="/TestTools/tools/manual_task.jsp">手工触发任务</a></li>
                            <li><a href="/TestTools/tools/base64_encrypt_image.jsp">pas照片加密工具</a></li>
                        </ul></li>

					<li class="dropdown"><a href="#" class="dropdown-toggle"
											data-toggle="dropdown">压测辅助工具<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/TestTools/performance/reset_druid.jsp">druid重置</a></li>
							<li><a
								href="/TestTools/performance/tradeOrderCollection.jsp">trade表落地统计</a></li>
							<li><a href="/TestTools/performance/pfsCollection.jsp">pfs表落地统计</a></li>
							<li><a href="/TestTools/performance/cmfCollection.jsp">cmf表落地统计</a></li>
						</ul></li>
				</ul>
				<!-- nav navbar-nav -->
			</div>
			<!--/.nav-collapse -->
		</div>
		<!-- container -->
	</div>
	<!-- navbar navbar-inverse navbar-fixed-top -->

	<div class="navbar navbar-inverse navbar-fixed-bottom"
		role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">CopyRight @
					上海微汇金融信息服务有限公司--测试部门</a>
			</div>
		</div>
	</div>



	<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
	<script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>

	<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
	<script
		src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>