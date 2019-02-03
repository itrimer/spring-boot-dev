package com.demo.dao;

import com.demo.model.Inventory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface InventoryDao extends CrudRepository<Inventory, Long> {
//    @Cacheable(key ="#p0")
    @Query(value = "select s.* from Inventory s where s.product_id=?1", nativeQuery = true)
    public Inventory getByProductId(Long productId);

    @Modifying
    @Transactional
    @Query(value = "update Inventory set qty=?2, lockQty=?3, version=version+1 where inventoryId=?1 and version=?4", nativeQuery = false)
    public int update(Long inventoryId, Long qty, Long lockQty, Long version);

}