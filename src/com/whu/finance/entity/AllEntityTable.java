package com.whu.finance.entity;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="allentitytable")
public class AllEntityTable {
    private String entityName;//entity名称
    private String daoImplName;//daoImplName名称
    private String serviceName;//service名称
    private String serviceImplName;//serviceName名称
    private String tableName;//表名
    @Id
    public String getEntityName() {
        return entityName;
    }
    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }
    public String getDaoImplName() {
        return daoImplName;
    }
    public void setDaoImplName(String daoImplName) {
        this.daoImplName = daoImplName;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getServiceImplName() {
        return serviceImplName;
    }
    public void setServiceImplName(String serviceImplName) {
        this.serviceImplName = serviceImplName;
    }
    public String getTableName() {
        return tableName;
    }
    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}