package com.demo.dao;

import com.demo.model.BaArea;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BaAreaDao extends CrudRepository<BaArea, String> {
    // 使用query 注解进行update 或者 delete 语句时，需要添加 modifying 注解修饰
    @Query(value="delete from s", nativeQuery=true)
    @Modifying
    public void s();
}