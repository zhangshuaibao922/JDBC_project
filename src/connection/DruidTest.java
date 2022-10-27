package connection;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.junit.Test;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.util.Properties;

public class DruidTest {
    @Test
    public void getConnection() throws Exception{
        Properties pros = new Properties();
        FileInputStream is=new FileInputStream(new File("resource//druid.properties"));
        pros.load(is);
        DataSource source = DruidDataSourceFactory.createDataSource(pros);
        Connection connection = source.getConnection();
        System.out.println(connection);

    }
}
