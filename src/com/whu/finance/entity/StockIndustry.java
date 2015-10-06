package com.whu.finance.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="stockindustry")
public class StockIndustry {
	private String stockID;
	private String industryName;
	private String stockName;
	private String region;
	private Integer stockType;
	private Date date=new Date();
	
	@Id
	public String getStockID() {
		return stockID;
	}
	public void setStockID(String stockID) {
		this.stockID = stockID;
	}
	public String getIndustryName() {
		return industryName;
	}
	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public Integer getStockType() {
		return stockType;
	}
	public void setStockType(Integer stockType) {
		this.stockType = stockType;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
