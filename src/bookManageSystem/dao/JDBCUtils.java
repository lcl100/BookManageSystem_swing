package bookManageSystem.dao;

import java.sql.*;

/**
 * 连接JDBC类
 */
public class JDBCUtils {

    /**
     * 加载驱动，并建立数据库连接
     *
     * @return 返回数据库连接
     * @throws SQLException           抛出SQLException
     * @throws ClassNotFoundException 抛出ClassNotFoundException
     */
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        // 设置数据库驱动
        Class.forName("com.mysql.jdbc.Driver");
        // 数据库链接URL及数据库名称
        String url = "jdbc:mysql://localhost:3306/db_bookSystem";
        // 登录账户名
        String username = "root";
        // 登录账户密码
        String password = "admin";
        // 创建连接
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    /**
     * 关闭数据库连接，释放资源
     *
     * @param stmt Statement对象
     * @param conn Connection对象
     */
    public static void release(Statement stmt, Connection conn) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            stmt = null;
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            conn = null;
        }
    }

    /**
     * 关闭数据库连接，释放资源
     *
     * @param rs   ResultSet对象
     * @param stmt Statement对象
     * @param conn Connection对象
     */
    public static void release(ResultSet rs, Statement stmt, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            rs = null;
        }
        release(stmt, conn);
    }

}
