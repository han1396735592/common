package cn.qqhxj.common.web.dao;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Mapper
public interface BaseDao<T> extends BaseMapper<T> {

    /**
     * @param sql
     * @param wrapper
     * @return
     */
    @Select("${sql} ${ew.customSqlSegment}")
    List<Map> query(@Param("sql") String sql, @Param(Constants.WRAPPER) Wrapper wrapper);

    /**
     * @param sql
     * @param wrapper
     * @param tClass
     * @param <E>
     * @return
     */
    default <E> List<E> query(@Param("sql") String sql, @Param(Constants.WRAPPER) Wrapper wrapper, Class<E> tClass) {
        List<Map> maps = query(sql, wrapper);
        return maps.stream().map((item) -> {
            return JSONObject.parseObject(JSONObject.toJSONString(item), tClass);
        }).collect(Collectors.toList());
    }

    default List<Map> query(String sql) {
        return query(sql, new QueryWrapper());
    }
}
