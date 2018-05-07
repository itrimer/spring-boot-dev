package com.demo.dao;

import com.demo.model.BaArea;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BaAreaDao extends CrudRepository<BaArea, String>, JpaSpecificationExecutor<BaArea> {

	@Query(value = "select s from BaArea s where s.prePinYin is null")
	public List<BaArea> selectPinyinIsNull();

	@Transactional
	// 使用query 注解进行update 或者 delete 语句时，需要添加 modifying 注解修饰
	@Query(value = "update BaArea set pinYin=?2, prePinYin=?3, simplePy=?4 where id=?1", nativeQuery = false)
	@Modifying
	public int updatePinyin(String areaId, String pinYin, String prePinYin, String simplePy);
}