package com.hold.book.dao;
import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	/**
	 * 保存实例(把一个瞬态的实例持久化标识符)
	 * @param t
	 */
    public void saveEntity(T t);
    /**
     * 修改指定的实例(把一个脱管状态的对象或自由态对象更新到数据库)
     * @param t
     */
    public void updateEntity(T t);
    /**
     * 保存或者修改实例(如果对象没有持久化标识属性，对其调用save(),否则update() 这个对象)
     * @param t
     */
    public void saveOrUpdateEntity(T t);
    /**
     * 插入记录之前先判断数据库中是否已存在
     * @param t
     * @param hql
     * @param objects
     * @return true插入成功，false记录已存在
     */
    public boolean saveWithoutSame(T t,String hql, Object...objects);
    /**
     * 删除实例
     * @param t
     */
    public void deleteEntity(T t);
    /**
     *执行sql语句
     * @param sql
     * @param objects
     */
    public void executeSQL(String sql,Object...objects);
    /**
     * 执行多条件的hql语句
     * @param hql
     * @param objects
     */
    public void batchEntityByHql(String hql,Object...objects);
    /**
     * 获取实例对象(无延迟，先Session缓存后二级缓存再sql)
     * @param id
     * @return T 持久化对象 
     */
    public T getEntity(Serializable id);
    /**
     * 获取实例代理对象(延迟加载，先Session缓存后二级缓存再代理)
     * @param id
     * @return
     */
    public T loadEntity(Serializable id);
    /**
     * 多条件hql查询最多只有一条的记录(一般出现在用户登录查询)
     * @param hql
     * @param objects
     * @return
     */
    public Object uniqueResult(String hql,Object...objects); 
    /**
     * 多条件的查询结果
     * @param hql
     * @param objects
     * @return
     */
    public List<T> findEntityByHql(String hql,Object...objects);
    /**
     * 查询所有实体
     * @return
     */
    public List<T> findAllEntity();
    /**
     * 多条件sql查询实例(getSession)
     * @param sql
     * @param objects
     * @return
     */
    public List<T> findObjectBySQL(String sql,Object...objects);
    /**
     * 多条件sql查询实例(openSession)
     * @param sql
     * @param objects
     * @return
     */
    public List<T> findObjectBySQL2(String sql,Object...objects);
    
}
