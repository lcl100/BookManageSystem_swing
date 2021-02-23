package bookManageSystem.dao;


import bookManageSystem.bean.BookBean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    /**
     * 操作结果：根据SQL语句执行数据库的增删改操作
     *
     * @param sql SQL语句
     * @return boolean 如果操作数据库成功返回true，否则返回false
     */
    public boolean dataChange(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        int num = 0;
        try {
            //获得数据的连接
            conn = JDBCUtils.getConnection();
            //获得Statement对象
            stmt = conn.createStatement();
            //发送SQL语句
            num = stmt.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        // 对受影响行数进行判断
        if (num > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 操作结果：根据参数sql获取数据库记录数据
     *
     * @param sql SQL语句
     * @return List 返回包含记录Records对象的集合
     */
    public List getRecordsDataBySql(String sql) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List list = new ArrayList();
        try {
            //获得数据的连接
            conn = JDBCUtils.getConnection();
            //获得Statement对象
            stmt = conn.createStatement();
            //发送SQL语句
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                BookBean bookBean = new BookBean();
                // 索引是从1开始的
                bookBean.setBookId(rs.getInt(1));
                bookBean.setBookName(rs.getString(2));
                bookBean.setBookAuthor(rs.getString(3));
                bookBean.setBookAuthorSex(rs.getString(4));
                bookBean.setBookPrice(rs.getFloat(5));
                bookBean.setBookDescription(rs.getString(6));
                bookBean.setBookTypeId(rs.getString(7));
                list.add(bookBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.release(rs, stmt, conn);
        }
        return list;
    }

    /**
     * 操作结果：将集合转换成数组
     *
     * @param list 集合
     * @return String[][] 二维数组
     */
    public String[][] ListToArray(List<BookBean> list) {
        String[][] array = new String[list.size()][7];
        for (int i = 0; i < list.size(); i++) {
            BookBean l = list.get(i);
            array[i][0] = String.valueOf(l.getBookId());
            array[i][1] = l.getBookName();
            array[i][2] = l.getBookAuthor();
            array[i][3] = l.getBookAuthorSex();
            array[i][4] = String.valueOf(l.getBookPrice());
            array[i][5] = l.getBookDescription();
            array[i][6] = l.getBookTypeId();
        }
        return array;
    }
}
