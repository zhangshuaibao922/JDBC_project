package shuaibaozhang.dao;

import been.Customer;

import java.sql.Connection;
import java.sql.Date;
import java.util.List;

/**
 * 此接口用于规范针对于customers表的常用操作
 * */
public interface CustomerDAO {
    /**
     * 将cust对象添加到数据库中
     * */
    void insert(Connection conn, Customer cust);

    /**
     * 将指定ID的记录删除
     * */
    void deleteByID(Connection conn,int id);
    /**
     * 根绝cust对象去修改数据表中指定的记录
     * */
    void update(Connection conn,Customer cust);
    /**
     * 根据ID查询对应的Customer对象
     * */
    Customer getCustomerByID(Connection conn,int id);
    /**
     * 查询表中所有记录构成的集合
     * */
    List<Customer> getAll(Connection conn);
    /**
     * 返回数据表中的数据的条目数
     * */
    Long getCount(Connection conn);
    /**
     * 返回数据表中的最大的生日
     * */
    Date getMaxBirth(Connection conn);
}
