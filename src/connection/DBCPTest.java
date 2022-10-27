package connection;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DBCPTest {

    @Test
    public void testGetconnection() throws SQLException {

        //创建DBCP数据库连接池
        BasicDataSource source=new BasicDataSource();
        //设置基本信息
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/test");
        source.setUsername("root");
        source.setPassword("root");
        //还可以设置其他涉及数据库连接池管理的相关属性
        source.setInitialSize(10);
        source.setMaxActive(10);
        //....
        Connection connection = source.getConnection();
        System.out.println(connection);
    }

    @Test
    public void testGetconnection1() throws Exception{
        Properties pros = new Properties();
        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("dbcp.properties");
//        FileInputStream is=new FileInputStream(new File("resource\\dbcp.properties"));
        pros.load(is);
        DataSource source = BasicDataSourceFactory.createDataSource(pros);
        Connection connection = source.getConnection();
        System.out.println(connection);
    }
}
