package com.performance.dataJob;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.common.DbEnviornment;

@Controller
public class DataCollectionJob extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long delayTime = 5;
	private long durationTime = 30;
	private String action = "";

	@RequestMapping(value = "hostingCollectTradeCollection")
	public void TradeOrderCollection(HttpServletRequest request,
			HttpServletResponse response) {
		action = request.getParameter("action");

		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println("Start");
				String time = "";
				String count = "";
				try {
					DbEnviornment funcDe = new DbEnviornment();
					funcDe.Connect("orayali.whintra.com:1521", "orayali",
							"reader", "reader");
					String sql = "select to_CHAR(gmt_create, 'yyyy-mm-dd hh24:mi:ss'),count(1) from trade.T_TRADE_ORDER t where t.GMT_CREATE >= sysdate - (1/24/60/60*"
							+ durationTime
							+ "+1/24/60/60*"
							+ delayTime
							+ ") and t.GMT_CREATE <= sysdate - 1/24/60/60*"
							+ delayTime
							+ " AND t.BIZ_PRODUCT_CODE = '301003' AND t.PARTNER_ID = '200003670082' GROUP BY to_CHAR(gmt_create, 'yyyy-mm-dd hh24:mi:ss') ORDER BY to_CHAR(gmt_create, 'yyyy-mm-dd hh24:mi:ss') ASC";
					ResultSet funcRs = DbEnviornment.statement
							.executeQuery(sql);
					DbEnviornment devDe = new DbEnviornment();
					devDe.Connect("10.65.193.12:1521", "whpay", "whqa", "whqa");
					while (funcRs.next()) {
						if (action.equals("start")) {
							time = funcRs
									.getString("TO_CHAR(GMT_CREATE,'YYYY-MM-DDHH24:MI:SS')");
							count = funcRs.getString("COUNT(1)");
							System.out.println(time);
							System.out.println(count);
							try {
								DbEnviornment.statement
										.executeQuery("INSERT into T_TRADE_ORDER_COUNT (T_TRADE_ORDER_CREATE_TIME, COUNT) values (to_date('"
												+ time
												+ "', 'yyyy-mm-dd hh24:mi:ss'), '"
												+ count + "')");
							} catch (Exception e) {
								System.out.println("主键冲突, 继续执行!");
								continue;
							}
						} else if (action.equals("stop")) {
							try {
								Thread.sleep(35000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
					funcRs.close();
					com.common.DbEnviornment.statement.close();
					com.common.DbEnviornment.con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		};

		ScheduledExecutorService service = Executors
				.newSingleThreadScheduledExecutor();
		System.out.println(service.isShutdown());

		if (action.equals("start")) {
			service.scheduleAtFixedRate(runnable, 0, durationTime,
					TimeUnit.SECONDS);
			System.out.println("开始执行数据采集任务!");
		} else if (action.equals("stop")) {
			service.shutdownNow();
			System.out.println("数据采集任务停止, 线程池正在关闭!");
		}
	}

}
