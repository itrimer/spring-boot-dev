package com.demo.manager;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface BaseManager<T, ID extends Serializable, DAO extends CrudRepository<T, ID>> {

    public abstract DAO getDao();

}
