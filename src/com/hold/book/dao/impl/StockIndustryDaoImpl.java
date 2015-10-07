package com.hold.book.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.hold.book.dao.StockIndustryDao;
import com.hold.book.entity.StockIndustry;

@Repository("stockIndustryDao")
public class StockIndustryDaoImpl extends BaseDaoImpl<StockIndustry> implements StockIndustryDao{

	@Resource(name="sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public List<String> findDistinctIndustryName() {
		String sql = "select distinct industryName from StockIndustry";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		return query.list();
	}
	
}
