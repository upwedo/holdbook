package com.hold.book.dao;

import java.util.List;

import com.hold.book.entity.StockIndustry;

public interface StockIndustryDao extends BaseDao<StockIndustry> {

	public List<String> findDistinctIndustryName();
}
