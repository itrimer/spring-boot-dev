package com.demo.util;

/**
 * Created by zhigang.huang on 2017/10/17.
 */

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

/**
 * 分页数据工具类。
 *
 * @author YangZhenghua
 * @version V1.0 2014-6-24 初版
 *
 */
public class PageUtils {

    /**
     * 创建分页请求。
     *
     * @author YangZhenghua
     * @date 2014-7-14
     *
     * @param pageNum 当前页
     * @param pageSize 每页条数
     * @param sortType 排序字段
     * @param direction 排序方向
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize, String sortType, String direction) {
        Sort sort = null;

        if (!StringUtils.isNotEmpty(sortType)) {
            return new PageRequest(pageNum - 1, pageSize);
        }
        if (StringUtils.isNotEmpty(direction)) {
            if (Direction.ASC.equals(direction)) {
                sort = new Sort(Direction.ASC, sortType);
            } else {
                sort = new Sort(Direction.DESC, sortType);
            }
            return new PageRequest(pageNum - 1, pageSize, sort);
        } else {
            sort = new Sort(Direction.ASC, sortType);
            return new PageRequest(pageNum - 1, pageSize, sort);
        }
    }

    /**
     * 创建分页请求(该方法可以放到util类中).
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize, String sortType) {
        return buildPageRequest(pageNum, pageSize, sortType, null);
    }

    /**
     * 创建分页请求
     *
     * @author YangZhenghua
     * @date 2014-11-12
     *
     * @param pageNum
     * @param pageSize
     * @param sort
     * @return
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize, Sort sort) {
        return new PageRequest(pageNum - 1, pageSize, sort);
    }

    /**
     * 创建分页请求(该方法可以放到util类中).
     */
    public static PageRequest buildPageRequest(int pageNum, int pageSize) {
        return buildPageRequest(pageNum, pageSize, null, null);
    }

}
