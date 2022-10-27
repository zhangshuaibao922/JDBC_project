package shuaibaozhang.dao;


import been.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class CustomerDAOImpl extends BaseDAO implements CustomerDAO{
    @Override
    public void insert(Connection conn, Customer cust) {
        String sql="insert into customers(name,email,birth) values (?,?,?)";
        update(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth());
    }

    @Override
    public void deleteByID(Connection conn, int id) {
        String sql="delete from customers where id=?";
        update(conn,sql,id);
    }

    @Override
    public void update(Connection conn, Customer cust) {
        String sql="update customers set name=?,email=?,birth =? where id=?";
        update(conn,sql,cust.getName(),cust.getEmail(),cust.getBirth(),cust.getId());
    }

    @Override
    public Customer getCustomerByID(Connection conn, int id) {
        String sql="select id,name,email,birth from customers where id=?";
        Customer instance = getInstance(conn, Customer.class, sql, id);
        return instance;
    }

    @Override
    public List<Customer> getAll(Connection conn) {
        String sql="select id,name,email,birth from customers";
        List<Customer> list = getForList(conn, Customer.class, sql);
        return list;
    }

    @Override
    public Long getCount(Connection conn) {
        String sql="select count(*) from customers";
        Long value = getValue(conn, sql);
        return value;
    }

    @Override
    public Date getMaxBirth(Connection conn) {
        String sql="select max(birth) from customers";
        Date value = getValue(conn, sql);
        return value;
    }
}
