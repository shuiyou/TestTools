package com.performance.collection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.common.DbEnviornment;
import com.meidusa.fastjson.JSON;

/**
 * 
 * @author sunjie
 *
 */
@Controller
public class TradeOrderCollection extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@RequestMapping(value = "collectTrade")
	public void CollectTrade(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ParseException,
			ServletException {
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String bizCode = request.getParameter("bizCode");
		String partnerId = request.getParameter("partnerId");
		List<String> timeList = new ArrayList<String>();
		List<Integer> countList = new ArrayList<Integer>();

		if (!startTime.isEmpty() && !endTime.isEmpty()) {
			SimpleDateFormat inputDateformatter = new SimpleDateFormat(
					"yyyyMMddHHmmss");
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");

			String formatStartTime = formatter.format(inputDateformatter
					.parse(startTime));
			String formatEndTime = formatter.format(inputDateformatter
					.parse(endTime));

			try {
				DbEnviornment devDe = new DbEnviornment();
				devDe.Connect("orayali.whintra.com:1521", "qa", "qa",
						"reader");
				String sql = "select to_CHAR(gmt_create, 'yyyy-mm-dd hh24:mi:ss') as orderDate,count(1) as orderCount from trade.T_TRADE_ORDER t where t.GMT_CREATE >= TO_DATE('"
						+ formatStartTime
						+ "', 'yyyy-mm-dd hh24:mi:ss') and t.GMT_CREATE <= TO_DATE('"
						+ formatEndTime
						+ "', 'yyyy-mm-dd hh24:mi:ss') AND t.BIZ_PRODUCT_CODE = '"
						+ bizCode
						+ "' AND t.PARTNER_ID = '"
						+ partnerId
						+ "' GROUP BY to_CHAR(gmt_create, 'yyyy-mm-dd hh24:mi:ss') ORDER BY to_CHAR(gmt_create, 'yyyy-mm-dd hh24:mi:ss') ASC";
				ResultSet queryResult = DbEnviornment.statement
						.executeQuery(sql);
				while (queryResult.next()) {
					timeList.add(queryResult.getString("orderDate"));
					countList.add(Integer.parseInt(queryResult
							.getString("orderCount")));
				}
				queryResult.close();
				com.common.DbEnviornment.statement.close();
				com.common.DbEnviornment.con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			try {
				DbEnviornment devDe = new DbEnviornment();
				devDe.Connect("orayali.whintra.com:1521", "orayali", "reader",
						"reader");
				String sql = "select to_CHAR(gmt_create, 'yyyy-mm-dd hh24:mi:ss') as orderDate,count(1) as orderCount from trade.T_TRADE_ORDER t where t.GMT_CREATE >= sysdate - 1/24/60 and t.GMT_CREATE <= sysdate AND t.BIZ_PRODUCT_CODE = '"
						+ bizCode
						+ "' AND t.PARTNER_ID = '"
						+ partnerId
						+ "' GROUP BY to_CHAR(gmt_create, 'yyyy-mm-dd hh24:mi:ss') ORDER BY to_CHAR(gmt_create, 'yyyy-mm-dd hh24:mi:ss') ASC";
				ResultSet queryResult = DbEnviornment.statement
						.executeQuery(sql);
				while (queryResult.next()) {
					timeList.add(queryResult.getString("orderDate"));
					countList.add(Integer.parseInt(queryResult
							.getString("orderCount")));
				}
				queryResult.close();
				com.common.DbEnviornment.statement.close();
				com.common.DbEnviornment.con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		Map<String, List> map = new HashedMap();
		map.put("timeList", timeList);
		map.put("countList", countList);

		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(map));

	}

}
