package com.demo.manager.impl;

import com.demo.dao.BaAreaDao;
import com.demo.manager.BaAreaManager;
import com.demo.model.BaArea;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BaAreaManagerImpl implements BaAreaManager {
	@Resource
	BaAreaDao baAreaDao;

	@Override
	public BaAreaDao getDao() {
		return baAreaDao;
	}

	@Override
	public BaArea findOne(String id){
		return getDao().findOne(id);
	}

	@Override
	public Iterable<BaArea> findAll(){
		return getDao().findAll();
	}

	@Override
	public BaArea save(BaArea baArea) {
		return getDao().save(baArea);
	}
}