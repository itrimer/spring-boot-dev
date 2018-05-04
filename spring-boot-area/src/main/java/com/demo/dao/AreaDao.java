package com.demo.dao;

import com.demo.model.AreaInfo;
import com.demo.model.BaArea;
import org.springframework.data.repository.CrudRepository;

public interface AreaDao extends CrudRepository<BaArea, String> {

}