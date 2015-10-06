package com.whu.finance.dao.impl;
 import javax.annotation.Resource;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import com.whu.finance.entity.AllEntityTable;
@Repository("allEntityTableDao")
public class AllEntityTableDaoImpl extends BaseDaoImpl<AllEntityTable>{
@Resource(name="sessionFactory")
private SessionFactory sessionFactory;
}