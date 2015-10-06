package com.whu.finance.dao;
import java.io.Serializable;
import java.util.List;

public interface BaseDao<T> {
	/**
	 * ����ʵ��(��һ��˲̬��ʵ���־û���ʶ��)
	 * @param t
	 */
    public void saveEntity(T t);
    /**
     * �޸�ָ����ʵ��(��һ���ѹ�״̬�Ķ��������̬������µ����ݿ�)
     * @param t
     */
    public void updateEntity(T t);
    /**
     * ��������޸�ʵ��(�������û�г־û���ʶ���ԣ��������save(),����update() �������)
     * @param t
     */
    public void saveOrUpdateEntity(T t);
    /**
     * �����¼֮ǰ���ж����ݿ����Ƿ��Ѵ���
     * @param t
     * @param hql
     * @param objects
     * @return true����ɹ���false��¼�Ѵ���
     */
    public boolean saveWithoutSame(T t,String hql, Object...objects);
    /**
     * ɾ��ʵ��
     * @param t
     */
    public void deleteEntity(T t);
    /**
     *ִ��sql���
     * @param sql
     * @param objects
     */
    public void executeSQL(String sql,Object...objects);
    /**
     * ִ�ж�������hql���
     * @param hql
     * @param objects
     */
    public void batchEntityByHql(String hql,Object...objects);
    /**
     * ��ȡʵ������(���ӳ٣���Session��������������sql)
     * @param id
     * @return T �־û����� 
     */
    public T getEntity(Serializable id);
    /**
     * ��ȡʵ���������(�ӳټ��أ���Session�������������ٴ���)
     * @param id
     * @return
     */
    public T loadEntity(Serializable id);
    /**
     * ������hql��ѯ���ֻ��һ���ļ�¼(һ��������û���¼��ѯ)
     * @param hql
     * @param objects
     * @return
     */
    public Object uniqueResult(String hql,Object...objects); 
    /**
     * �������Ĳ�ѯ���
     * @param hql
     * @param objects
     * @return
     */
    public List<T> findEntityByHql(String hql,Object...objects);
    /**
     * ��ѯ����ʵ��
     * @return
     */
    public List<T> findAllEntity();
    /**
     * ������sql��ѯʵ��(getSession)
     * @param sql
     * @param objects
     * @return
     */
    public List<T> findObjectBySQL(String sql,Object...objects);
    /**
     * ������sql��ѯʵ��(openSession)
     * @param sql
     * @param objects
     * @return
     */
    public List<T> findObjectBySQL2(String sql,Object...objects);
    
}
