package train;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.common.DbEnviornment;

public class InsertPassword {

	public void insert() {
		DbEnviornment de = new DbEnviornment();
		de.Connect("10.65.212.193:1521", "orayali", "qa", "qa");

		for (int i = 18749; i <= 68748; i++) {
			String memberId = "1000055" + i;
			String getOpSql = "select OPERATOR_ID from member.tm_operator where MEMBER_ID = '"
					+ memberId + "'";
			try {
				ResultSet rs = DbEnviornment.statement.executeQuery(getOpSql);
				if (rs.next()) {
					String opId = rs.getString("OPERATOR_ID");
					String insertSql = "insert into member.tr_password (ID, OPERATOR_ID, ACCOUNT_ID, PASSWORD, CREATE_TIME) values ('999"
							+ i
							+ "','"
							+ opId
							+ "','2001002001"
							+ memberId
							+ "00001','P4946',sysdate)";
					DbEnviornment.statement.executeQuery(insertSql);
				}
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		try {
			com.common.DbEnviornment.con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main (String[] args) {
		InsertPassword test = new InsertPassword();
		test.insert();
	}

}
