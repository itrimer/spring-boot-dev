package com.demo.manager;

import com.demo.dao.BaAreaDao;
import com.demo.model.BaArea;

/**
 * BaAreaManager
 *
 */
public interface BaAreaManager extends BaseManager<BaArea, String, BaAreaDao>{
	public BaArea findOne(String id);

	public Iterable<BaArea> findAll();
}