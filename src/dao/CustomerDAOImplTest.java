package dao;

import been.Customer;
import org.junit.jupiter.api.Test;
import util.JDBCUtils;

import java.sql.Connection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOImplTest {
    CustomerDAOImpl dao=new CustomerDAOImpl();

    @Test
    void insert() {

    }

    @Test
    void update() {
    }

    @Test
    void getCustomerByID() {
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
    }

    @Test
    void getMaxBirth() {
    }
}