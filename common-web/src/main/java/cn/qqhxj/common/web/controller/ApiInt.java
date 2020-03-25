package cn.qqhxj.common.web.controller;

import cn.qqhxj.common.web.Query;
import cn.qqhxj.common.web.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface ApiInt<T> extends Api<T> {

    /**
     * 增加 实体
     *
     * @param t 实体
     * @return result
     */
    Result add(T t);

    /**
     * 批量添加实体
     *
     * @param ts t list
     * @return result
     */
    Result batchAdd(List<T> ts);

    /**
     * 删除实体
     *
     * @param id id
     * @return result
     */
    Result del(String id);

    /**
     * 根据id批量删除实体
     *
     * @param ids ids
     * @return
     */
    Result batchDel(List<String> ids);

    /**
     * 根据条件批量删除内容
     *
     * @param query 删除条件
     * @return result
     */
    Result batchDelQuery(Query<T> query);

    /**
     * 修改实体
     *
     * @param t 实体
     * @return result
     */
    Result update(T t);

    /**
     * 根据id批量修改实体
     *
     * @param ts 实体
     * @return result
     */
    Result batchUpdate(List<T> ts);

    /**
     * 根据条件批量修改实体
     *
     * @param query 修改条件
     * @return
     */
    Result batchUpdateQuery(Query<T> query);


    /**
     * 获取实体
     *
     * @param id id
     * @return result
     */
    Result<T> get(String id);


    /**
     * 获取所有实体
     *
     * @return result
     */
    Result<List<T>> getAll(String select);

    /**
     * 根据id 获取所有的
     *
     * @param ids ids
     * @return result
     */
    Result<List<T>> batchGet(List<String> ids);


    /**
     * 根据查询条件获取多个
     *
     * @param query 查询参数
     * @return result
     */
    Result<List<T>> query(Query<T> query);


    /**
     * 获取list
     *
     * @param current 当前
     * @param count   长度
     * @return result
     */
    Result<List<T>> list(Long current, Long count);


    /**
     * 分页查询
     *
     * @param current 当前页 1
     * @param size    页的最大数
     * @return result
     */
    Result<IPage<T>> page(Long current, Long size);


    /**
     * 根据条件分页查询
     *
     * @param query query
     * @return result
     */
    Result<IPage<T>> pageQuery(Query<T> query);

}
