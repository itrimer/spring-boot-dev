package com.demo.dao;

import org.springframework.data.repository.CrudRepository;

import java.io.Serializable;

public interface BaseDao<T, ID extends Serializable> extends CrudRepository<T, ID> {
}