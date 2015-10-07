package com.hold.book.service.impl;
import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hold.book.dao.BaseDao;
import com.hold.book.entity.AllEntityTable;
import com.hold.book.service.AllEntityTableService;
@Service("allEntityTableServiceImpl")
public class AllEntityTableServiceImpl extends BaseServiceImpl<AllEntityTable> implements AllEntityTableService{
public AllEntityTableServiceImpl(){
super();
}
@Resource(name="allEntityTableDao")
public void setBaseDao(BaseDao<AllEntityTable> baseDao){
super.setBaseDao(baseDao);
}
@Override
public void start() {
// TODO Auto-generated method stub

}
}