package com.demo.manager;

import java.io.Serializable;

public interface BaseManager<T, ID extends Serializable, DAO extends org.springframework.data.repository.CrudRepository<T, ID>> {

	public abstract DAO getDao();

}
