// 这是一个获取数据库连接，以及关闭连接的工具类

package com.west2.DAO;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

public class JdbcUtils {
    private static String driver = null;
    private static String url = null;
    private static String username = null;
    private static String password = null;

    static {
        try {
            // 从资源文件中获取信息流
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties = new Properties();
            properties.load(in);

            driver = properties.getProperty("driver");
            url = properties.getProperty("url");
            username = properties.getProperty("username");
            password = properties.getProperty("password");

            // 仅加载一次驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // 获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // 释放连接资源
    public static void release(Connection conn, PreparedStatement pst,Statement st, ResultSet rs)
            throws MyException {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("释放ResultSet失败！");
            }
        }
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("释放PreparedStatement失败！");
            }
        }
        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("释放Statement失败！");
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new MyException("释放Connection失败！");
            }
        }
    }

    /**
     * 预处理SQL，获取PreparedStatement对象，并对sql参数进行赋值操作
     *
     * @param con   数据库连接对象
     * @param sql   sql语句
     * @param param 实际参数
     * @return 返回PreparedStatement对象
     * @throws SQLException SQL异常
     */
    public static PreparedStatement handleSql(Connection con, String sql, Object[] param) throws SQLException {
        PreparedStatement st = con.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        int paramCnt = st.getParameterMetaData().getParameterCount();
        if (paramCnt != 0 && param != null && paramCnt == param.length) {
            for (int i = 0; i < paramCnt; i++) {
                st.setObject(i + 1, param[i]);
            }
        }
        return st;
    }

    /**
     * 有参数更新方法：insert,delete,update
     *
     * @param con   数据库连接对象
     * @param sql   目标执行的SQL语句
     * @param param 对应的SQL语句的参数
     * @return SQL语句对应的数据表的影响的主键
     */
    public static List update(Connection con, String sql, Object[] param,PreparedStatement pst,ResultSet rs) {
        List<Integer> result = new ArrayList<>();
        try {
            con.setAutoCommit(false);//开启事务
            con.setReadOnly(false);
            pst = handleSql(con, sql, param);
            pst.executeUpdate();
            rs = pst.getGeneratedKeys();
            int i = 0;
            while (rs.next()){
                int generatedKey = rs.getInt(1);
                System.out.println(generatedKey);
                result.add(generatedKey);
            }
            con.commit();//业务完毕，提交事务
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



    /**
     * query 有参数查询方法
     *
     * @param con   数据库连接对象
     * @param sql   要执行的sql语句
     * @param pst   PreparedStatement对象
     * @param rs    ResultSet结果集对象
     * @param param 对应的SQL语句的参数
     * @return List<Object[]>
     * @throws SQLException SQL异常
     */
    public static List<Object[]> queryOrder(Connection con, String sql, PreparedStatement pst ,
                                            ResultSet rs, Object[] param) throws SQLException {
        List<Object[]> t=null;
        ArrayListHandler rsh=new ArrayListHandler();
        try {
            // 只读
            //con.setReadOnly(true);
            pst = handleSql(con, sql, param);
            rs = pst.executeQuery();
            //con.setReadOnly(false);
            t = rsh.handle(rs);
        } catch (SQLException e) {
            throw e;
        }
        return t;
    }

    /**
     * query 无参数查询方法
     *
     * @param sql   要执行的sql语句
     * @param st    Statement对象
     * @param rs    ResultSet结果集对象
     * @return List<Object[]>
     * @throws SQLException SQL异常
     */
    public static List<Object[]> queryOrder(String sql, Statement st, ResultSet rs) throws SQLException {
        List<Object[]> t=null;
        ArrayListHandler rsh=new ArrayListHandler();
        try{
            rs = st.executeQuery(sql);
            t=rsh.handle(rs);
        } catch (SQLException e) {
            throw e;
        }
        return t;
    }

}
