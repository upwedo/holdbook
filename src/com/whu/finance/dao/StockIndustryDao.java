package com.whu.finance.dao;

import java.util.List;

import com.whu.finance.entity.StockIndustry;

public interface StockIndustryDao extends BaseDao<StockIndustry> {

	public List<String> findDistinctIndustryName();
}
