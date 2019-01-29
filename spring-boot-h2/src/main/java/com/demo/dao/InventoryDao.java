package com.demo.dao;

import com.demo.model.Inventory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryDao extends CrudRepository<Inventory, Long> {
//    @Cacheable(key ="#p0")
    @Query(value = "select s.* from Inventory s where s.product_id=?1", nativeQuery = true)
    public Inventory getByProductId(Long productId);

}