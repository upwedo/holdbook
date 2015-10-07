package com.hold.book.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.hold.book.dao.BaseDao;


public class BaseDaoImpl<T> implements BaseDao<T> {
	@Resource(name="sessionFactory")
     private SessionFactory sessionFactory;
     private Class clazz;
     public BaseDaoImpl(){
    	 ParameterizedType genType = (ParameterizedType) this.getClass().getGenericSuperclass();   
         Type[] params = genType.getActualTypeArguments();   
         this.clazz = (Class) params[0];
     }
	@Override
	public void saveEntity(T t) {
		sessionFactory.getCurrentSession().save(t);
	}

	@Override
	public void updateEntity(T t) {
		sessionFactory.getCurrentSession().update(t);
	}

	@Override
	public void saveOrUpdateEntity(T t) {
		sessionFactory.getCurrentSession().saveOrUpdate(t);
	}

	@Override
	public boolean saveWithoutSame(T t,String hql,Object... objects) {
		List<T>list=findEntityByHql(hql, objects);
		if(list.size()>0){
			return false;
		}else{
			saveEntity(t);
			return true;
		}
	}

	@Override
	public void deleteEntity(T t) {
		sessionFactory.getCurrentSession().delete(t);
	}

	@Override
	public void executeSQL(String sql, Object... objects) {
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(sql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
	}

	@Override
	public void batchEntityByHql(String hql, Object... objects) {
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		query.executeUpdate();
		
	}

	@Override
	public T getEntity(Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	@Override
	public T loadEntity(Serializable id) {
		return (T) sessionFactory.getCurrentSession().get(clazz, id);
	}

	@Override
	public Object uniqueResult(String hql, Object... objects) {
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return query.uniqueResult();
	}

	@Override
	public List<T> findEntityByHql(String hql, Object... objects) {
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		return query.list();
	}

	@Override
	public List<T> findAllEntity() {
		Query query=sessionFactory.getCurrentSession().createQuery("from "+clazz.getName());
		return query.list();
	}

	@Override
	public List<T> findObjectBySQL(String sql, Object... objects) {
		SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery(sql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		query.addEntity(clazz);
		return query.list();
	}

	@Override
	public List<T> findObjectBySQL2(String sql, Object... objects) {
		Session sess=sessionFactory.openSession();
		SQLQuery query=sess.createSQLQuery(sql);
		for(int i=0;i<objects.length;i++){
			query.setParameter(i, objects[i]);
		}
		query.addEntity(clazz);
		List<T> res=query.list();
		sess.close();
		return res;
	}

}
