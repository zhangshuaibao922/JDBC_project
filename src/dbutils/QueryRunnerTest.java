package dbutils;

import been.Customer;
import connection.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.*;
import org.junit.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 开源JDBC工具类库，封装了针对于数据库的增删改查操作
 */

public class QueryRunnerTest {
    @Test
    public void testInsert() {
        Connection connection3 = null;
        try {
            QueryRunner runner = new QueryRunner();
            connection3 = JDBCUtils.getConnection3();
            String sql="insert into customers(name,email,birth)values(?,?,?)";
            int update = runner.update(connection3, sql, "蔡徐坤", "caixukun@126.com", "1997-09-08");
            System.out.println(update);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(connection3,null);
        }
    }
    @Test
    public void testQuery1()  {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql="select id,name,email,birth from customers where id=?";
            BeanHandler<Customer> handler=new BeanHandler<>(Customer.class);
            Customer query = runner.query(conn, sql, handler, 20);
            System.out.println(query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
    @Test
    public void testQuery2() throws Exception {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql="select id,name,email,birth from customers where id<?";
            BeanListHandler<Customer> handler = new BeanListHandler<>(Customer.class);
            List<Customer> list = runner.query(conn, sql, handler, 20);
            list.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
    @Test
    public void testQuery3()  {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql="select id,name,email,birth from customers where id=?";
            MapHandler handler = new MapHandler();
            Map<String, Object> map = runner.query(conn, sql, handler, 20);
            System.out.println(map);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
    @Test
    public void testQuery4()  {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql="select id,name,email,birth from customers where id<?";
            MapListHandler handler = new MapListHandler();
            List<Map<String, Object>> list = runner.query(conn, sql, handler, 20);
            list.forEach(System.out::println);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
    @Test
    public void testQuery5()  {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();
            String sql="select count(*) from customers";
            ScalarHandler handler = new ScalarHandler();
            Object query = runner.query(conn, sql, handler);
            System.out.println((long)query);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
    @Test
    public void testQuery6()  {
        Connection conn = null;
        try {
            QueryRunner runner = new QueryRunner();
            conn = JDBCUtils.getConnection3();

            String sql="select id,name,email,birth from customers where id=?";
            ResultSetHandler<Customer> handler=new ResultSetHandler<Customer>() {
                @Override
                public Customer handle(ResultSet resultSet) throws SQLException {
                    if(resultSet.next()){
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String email = resultSet.getString("email");
                        Date birth = resultSet.getDate("birth");
                        return new Customer(id, name, email, birth);
                    }
                    return null;
                }
            };
            Customer customer = runner.query(conn, sql, handler, 20);
            System.out.println(customer);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
}
