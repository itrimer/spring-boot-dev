package com.demo.manager.impl;

import com.demo.manager.BaseManager;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public abstract class BaseManagerImpl<T, ID extends Serializable, DAO extends CrudRepository<T, ID>>
        implements BaseManager<T, ID, DAO> {

}