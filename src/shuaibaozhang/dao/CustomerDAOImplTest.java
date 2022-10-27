package shuaibaozhang.dao;

import been.Customer;
import org.junit.jupiter.api.Test;
import util.JDBCUtils;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

class CustomerDAOImplTest {
    CustomerDAOImpl dao=new CustomerDAOImpl();

    @Test
    void insert() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();

            dao.insert(conn,new Customer(1,"小飞","wedads@qq.com",new Date(324242)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }

    }

    @Test
    void deleteByID() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.deleteByID(conn,13);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    void update() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            dao.update(conn,new Customer(18,"顶针","xihuan@qq.com",new Date(123132131)));

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    void getCustomerByID() {
        Connection conn = null;
        try {
            conn = connection.util.JDBCUtils.getConnection3();
            Customer customerByID = dao.getCustomerByID(conn, 1);
            System.out.println(customerByID);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    void getAll() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            List<Customer> list = dao.getAll(conn);
            for (Customer c :
                    list) {
                System.out.println(c);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    void getCount() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Long count = dao.getCount(conn);
            System.out.println(count);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }

    @Test
    void getMaxBirth() {
        Connection conn = null;
        try {
            conn = JDBCUtils.getConnection();
            Date maxBirth = dao.getMaxBirth(conn);
            System.out.println(maxBirth);

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(conn,null);
        }
    }
}