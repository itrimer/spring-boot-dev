package com.demo.manager;

import com.demo.dao.BaseDao;

import java.io.Serializable;

public interface BaseManager<T, ID extends Serializable, DAO extends BaseDao<T, ID>> {

	public abstract DAO getDao();

}
