package connection.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.dbutils.DbUtils;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
    private static ComboPooledDataSource cpds = new ComboPooledDataSource("helloc3p0");
    /**
     * 使用C3P0的数据库连接技术
     * */
    public static Connection getConnection1() throws SQLException {
        return cpds.getConnection();
    }
    /**
     * 使用DBCP的数据库连接技术
     * */
    private static DataSource source;
    static {
        try {
            Properties pros = new Properties();
            InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
//        FileInputStream is=new FileInputStream(new File("resource\\dbcp.properties"));
            pros.load(is);
            //创建一个DBCP数据库连接池
            source = BasicDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection2() throws Exception{
        return source.getConnection();
    }

    /**
     * 使用Druid数据库连接池技术
     * */
    private static DataSource source1;
    static{
        try {
            Properties pros = new Properties();
            FileInputStream is=new FileInputStream(new File("resource//druid.properties"));
            pros.load(is);
            source1=DruidDataSourceFactory.createDataSource(pros);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection3() throws Exception{
        return source1.getConnection();
    }
    public static void closeResource(Connection conn, Statement ps){
        try {
            if(ps != null)
                ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if(conn != null)
                conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeResource(Connection conn, Statement ps, ResultSet rs){
//        try {
//            DbUtils.close(conn);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            DbUtils.close(ps);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            DbUtils.close(rs);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        DbUtils.closeQuietly(conn);
        DbUtils.closeQuietly(ps);
        DbUtils.closeQuietly(rs);
    }
}
