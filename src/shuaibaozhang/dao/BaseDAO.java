package shuaibaozhang.dao;

import util.JDBCUtils;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 封装了针对于数据表的通用的操作
 */
public abstract class BaseDAO {
    /**
     * 通用的增删改操作（考虑了事务）
     * */
    public int update(Connection conn, String sql, Object...args){
        PreparedStatement ps = null;
        try {
            //1.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);
            //2.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            //3.执行
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            //4.资源关闭
            JDBCUtils.closeResource(null,ps);
        }
    }
/**
 * 通用的查询操作，用于返回数据表中的一条记录（考虑了事务）
 * */
    public  <T> T getInstance(Connection conn,Class<T> clazz,String sql,Object...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            //获取结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            if(rs.next()){
                T t= clazz.newInstance();
                //处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object object = rs.getObject(i + 1);
                    //获取每个列的列名
    //                String columnName = rsmd.getColumnName();
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //给t对象指定的columnLabel属性赋值为object，通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                return t;
            }
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(null,ps,rs);
        }
        return null;
    }

    /**
     * 通用的查询操作，用于返回数据表中的多条记录构成的集合（考虑了事务）
     * */
    public <T> List<T> getForList(Connection conn, Class<T> clazz, String sql, Object...args){
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            //获取结果集的元数据：ResultSetMetaData
            ResultSetMetaData rsmd = rs.getMetaData();
            //通过ResultSetMetaData获取结果集中的列数
            int columnCount = rsmd.getColumnCount();
            ArrayList<T> list = new ArrayList<>();
            while(rs.next()){
                T t= clazz.newInstance();
                //处理结果集一行数据中的每一个列
                for (int i = 0; i < columnCount; i++) {
                    //获取列值
                    Object object = rs.getObject(i + 1);
                    //获取每个列的列名
                    //                String columnName = rsmd.getColumnName();
                    String columnLabel = rsmd.getColumnLabel(i + 1);
                    //给t对象指定的columnLabel属性赋值为object，通过反射
                    Field field = clazz.getDeclaredField(columnLabel);
                    field.setAccessible(true);
                    field.set(t,object);
                }
                list.add(t);
            }
            return list;
        } catch (SQLException | InstantiationException | IllegalAccessException | NoSuchFieldException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(null,ps,rs);
        }
    }

    /**
     * 查询特殊值的方法
     * */
    public <T> T getValue(Connection conn,String sql,Object...args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            rs = ps.executeQuery();
            if(rs.next()){
                return (T) rs.getObject(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            JDBCUtils.closeResource(null,ps,rs);
        }
        return null;
    }
}
