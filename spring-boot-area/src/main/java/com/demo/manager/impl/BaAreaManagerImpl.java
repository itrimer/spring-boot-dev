package com.demo.manager.impl;

import com.demo.dao.BaAreaDao;
import com.demo.manager.BaAreaManager;
import com.demo.model.BaArea;
import com.demo.util.PageUtils;
import com.demo.util.StringUtils;
import com.demo.vo.AreaInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class BaAreaManagerImpl implements BaAreaManager {
	@Resource
	BaAreaDao baAreaDao;

	@Override
	public BaAreaDao getDao() {
		return baAreaDao;
	}

	@Override
	public BaArea findOne(String id){
		return getDao().findOne(id);
	}

	@Override
	public Iterable<BaArea> findAll(){
		return getDao().findAll();
	}

	@Override
	public BaArea save(BaArea baArea) {
		return getDao().save(baArea);
	}

	@Override
	public Page<AreaInfo> query(final Map<String, String> params, int pageNo, int pageSize) throws Exception {
		final PageRequest pageRequest = PageUtils.buildPageRequest(pageNo, pageSize);

		Page<BaArea> pageQueryRst = baseQuery(params, pageNo, pageSize);

		List<AreaInfo> areaInfos = new ArrayList<>();
		Iterator<BaArea> iterator = pageQueryRst.iterator();
		while (iterator.hasNext()){
			BaArea baArea = iterator.next();
			AreaInfo areaInfo = buildArea(baArea.getAreaId(), null);
			if(areaInfo != null) {
				areaInfos.add(areaInfo);
			}
		}

		return new PageImpl<>(areaInfos, pageRequest, pageQueryRst.getTotalElements());
	}

	@Override
	public Page<BaArea> baseQuery(final Map<String, String> params, int pageNo, int pageSize) throws Exception {
		final PageRequest pageRequest = PageUtils.buildPageRequest(pageNo, pageSize);

		return baAreaDao.findAll(new Specification<BaArea>() {
			@Override
			public Predicate toPredicate(Root<BaArea> root,
										 CriteriaQuery<?> query, CriteriaBuilder cb) {

				List<Predicate> q = new ArrayList<>();
				if (StringUtils.isNotEmpty(params.get("id"))) {
					Path<String> path = root.get("id");
					Predicate qw = cb.equal(path,  params.get("id"));
					q.add(qw);
				}
				if (StringUtils.isNotEmpty(params.get("areaName"))) {
					Path<String> path = root.get("areaName");
					Predicate qw = cb.like(path, "%" + params.get("areaName") + "%");
					q.add(qw);
				}
				if (StringUtils.isNotEmpty(params.get("pinYin"))) {
					Path<String> path = root.get("pinYin");
					Predicate qw = cb.like(path, "%" + params.get("pinYin") + "%");
					q.add(qw);
				}
				if (StringUtils.isNotEmpty(params.get("simplePy"))) {
					Path<String> path = root.get("simplePy");
					Predicate qw = cb.equal(path, params.get("simplePy"));
					q.add(qw);
				}
				String prePinYin = params.get("prePinYin");
				if (StringUtils.isNotEmpty(prePinYin)) {
					Path<String> path = root.get("prePinYin");
					Predicate qw = null;
					if("null".equals(prePinYin)) {
						qw = cb.isNull(path);
					} else {
						qw = cb.equal(path, prePinYin);
					}
					q.add(qw);
				}

				query.where(q.toArray(new Predicate[q.size()]));

				query.orderBy(cb.desc(root.get("level")));
				return null;
			}
		}, pageRequest);
	}

	@Override
	public AreaInfo buildArea(String areaId, AreaInfo areaInfo) {
		BaArea baArea = baAreaDao.findOne(areaId);
		if(baArea == null){
			return areaInfo;
		}

		if(areaInfo == null) {
			areaInfo = new AreaInfo();
		}
		if(null == areaInfo.getAreaId()){
			BeanUtils.copyProperties(baArea, areaInfo);
		}
		areaInfo.setWholeName(buildWholeName(areaInfo.getWholeName(), baArea.getAreaName()));
		if(baArea.getLevel() == 1){
			areaInfo.setProvinceId(baArea.getAreaId());
		}
		if(baArea.getLevel() == 2){
			areaInfo.setCityId(baArea.getAreaId());
		}
		if(baArea.getLevel() == 3){
			areaInfo.setCountyId(baArea.getAreaId());
		}
		if("0".equals(baArea.getParentId())){
			return areaInfo;
		}
		return buildArea(baArea.getParentId(), areaInfo);
	}

	private String buildWholeName(String targetName, String parentAreaName) {
		if(StringUtils.isEmpty(targetName)){
			return parentAreaName;
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(parentAreaName).append(" ").append(targetName);
		return stringBuilder.toString();
	}

	@Override
	public int updatePinyin(String areaId, String pinYin, String prePinYin, String simplePy){
		return baAreaDao.updatePinyin(areaId, pinYin, prePinYin, simplePy);
	}
}