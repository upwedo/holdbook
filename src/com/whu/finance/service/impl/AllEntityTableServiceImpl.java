package com.whu.finance.service.impl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.whu.finance.dao.BaseDao;
import com.whu.finance.entity.AllEntityTable;
import com.whu.finance.service.AllEntityTableService;
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