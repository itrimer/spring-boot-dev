package com.demo.manager;

import com.demo.dao.BaAreaDao;
import com.demo.model.BaArea;
import com.demo.vo.AreaInfo;
import org.springframework.data.domain.Page;

import java.util.Map;

/**
 * BaAreaManager
 */
public interface BaAreaManager extends BaseManager<BaArea, String, BaAreaDao> {
    public BaArea findOne(String id);

    public Iterable<BaArea> findAll();

    public BaArea save(BaArea baArea);

    public Page<AreaInfo> query(final Map<String, String> params, int pageNo, int pageSize) throws Exception;

    public Page<BaArea> baseQuery(final Map<String, String> params, int pageNo, int pageSize) throws Exception;

    public AreaInfo buildArea(String areaId, AreaInfo areaInfo);

    public int updatePinyin(String areaId, String pinYin, String preinYin, String simplePy);

}