package com.common;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.apache.tools.ant.taskdefs.SQLExec.DelimiterType;
import org.apache.tools.ant.types.EnumeratedAttribute;

/**
 *
 * @author sunjie
 *
 */
public class DBOperation {

    private String DBAddress = "10.65.193.11:1521";
    private String DBName = "whpay";
    private String userName = "reader";
    private String password = "tF1P7IC7oKa6ua";
    private DbEnviornment de;

    public static void main(String[] args) throws SQLException {
        DBOperation dbo = new DBOperation();
        dbo.setDBAddress("db2.func.weibopay.com:1521");
        dbo.setDBName("whdb2");
        dbo.setUserName("manager");
        dbo.setPassword("manager");
        String fileName = "test.sql";
        dbo.ExecSql(fileName);
        System.out.println("执行成功");
    }

    public void setDBAddress(String DBAddress) {
        this.DBAddress = DBAddress;
    }

    public void setDBName(String DBName) {
        this.DBName = DBName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
        this.setPassword(userName);
    }

    public void setPassword(String password) {
        // this.password = password;
        OraclePasswd oPasswd = new OraclePasswd();
        this.password = oPasswd.getPasswd(userName);
        if (userName.equals("liantiao")) {
            this.userName = "mayunfei";
            this.password = "mayunfei";
        }
    }

    public void DbEnviornment(String host, String database, String username, String pwd) {

    }

    /**
     * 获取数据库字段
     *
     * @param sql
     * @param field
     * @return
     */
    public String getDBField(String sql, String field) {
        String fieldValue = null;
        try {
            de = new DbEnviornment();
            de.Connect(DBAddress, DBName, userName, password);
            ResultSet rs = DbEnviornment.statement.executeQuery(sql);
            while (rs.next()) {
                fieldValue = rs.getString(field);
            }
            rs.close();
            com.common.DbEnviornment.con.close();
            return fieldValue;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String checkDBField(Map<String, String> map, String sql) throws SQLException {
        StringBuilder error = new StringBuilder("[");
        int mapLenght = map.size();
        Iterator<String> keySet = map.keySet().iterator();
        ResultSet rs = null;
        try {
            de = new DbEnviornment();
            de.Connect(DBAddress, DBName, userName, password);
            rs = DbEnviornment.statement.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        rs.next();
        if (mapLenght > 0) {
            while (keySet.hasNext()) {
                String key = keySet.next();
                if (map.get(key).equals(rs.getString(key))) {
                } else {
                    error.append(key + "=" + rs.getString(key) + ",");
                }
            }
        } else {
            return "你逗我?没预期结果我怎么比较?";
        }

        rs.close();
        com.common.DbEnviornment.con.close();

        if (error.length() != 1) {
            error.replace(error.length() - 1, error.length(), "]");
        }
        if (error.toString().equals("[")) {
            return "true";
        } else {
            return error.toString();
        }
    }

    public boolean ExecSql(String fileName) throws SQLException {
        int i = 0;
        String filePath = "/nfs/config/func/qc/sql_proc/";
        try {
            SQLExec sqlExec = new SQLExec();
            //设置数据库参数
            String url = "jdbc:oracle:thin:@" + DBAddress + ":" + DBName;
            sqlExec.setDriver("oracle.jdbc.driver.OracleDriver");
            sqlExec.setUrl(url);
            sqlExec.setUserid(userName);
            sqlExec.setPassword(password);
            //要执行的脚本
            sqlExec.setSrc(new File(filePath + fileName));
            //有出错的语句该如何处理
            sqlExec.setOnerror((SQLExec.OnError) (EnumeratedAttribute.getInstance(SQLExec.OnError.class, "abort")));
            //	          sqlExec.setAutocommit(true);
            sqlExec.setDelimiter("/");
            DelimiterType delimiterType = new SQLExec.DelimiterType();
            delimiterType.setValue(SQLExec.DelimiterType.ROW);
            sqlExec.setDelimiterType(delimiterType);
            sqlExec.setPrint(true); //设置是否输出
            //输出到文件 sql.out 中；不设置该属性，默认输出到控制台
            sqlExec.setOutput(new File(filePath + "sql.out"));
            sqlExec.setProject(new Project()); // 要指定这个属性，不然会出错
            sqlExec.execute();
            i = i + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (i != 0);
    }
}
