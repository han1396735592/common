package cn.qqhxj.common.web.controller;

import cn.qqhxj.common.web.Query;
import cn.qqhxj.common.web.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface SwaggerApi<T> extends ApiImpl<T> {

    @Override
    default <A> Result<A> result(boolean flag, A data, String msg) {
        return ApiImpl.super.result(flag, data, msg);
    }

    @Override
    default  <A> Result<A> result(A data) {
        return ApiImpl.super.result(data);
    }

    @ApiOperation("添加")
    @Override
    default Result add(@ApiParam @RequestBody T t) {
        return ApiImpl.super.add(t);
    }

    @ApiOperation("批量添加")
    @Override
    default Result batchAdd(@ApiParam @RequestBody List<T> ts) {
        return ApiImpl.super.batchAdd(ts);
    }

    @ApiOperation("根据ID删除数据")
    @Override
    default Result del(@ApiParam @RequestParam String id) {
        return ApiImpl.super.del(id);
    }

    @ApiOperation("根据ID批量删除")
    @Override
    default Result batchDel(@RequestParam List<String> ids) {
        return ApiImpl.super.batchDel(ids);
    }

    @ApiOperation("根据条件删除")
    @Override
    default Result batchDelQuery(@RequestBody Query<T> query) {
        return ApiImpl.super.batchDelQuery(query);
    }

    @ApiOperation("根据ID更新数据")
    @Override
    default Result update(@ApiParam @RequestBody T t) {
        return ApiImpl.super.update(t);
    }

    @ApiOperation("根据ID批量更新数据")
    @Override
    default Result batchUpdate(@RequestBody List<T> ts) {
        return ApiImpl.super.batchUpdate(ts);
    }

    @ApiOperation("根据条件批量更新数据")
    @Override
    default Result batchUpdateQuery(@RequestBody Query<T> query) {
        return ApiImpl.super.batchUpdateQuery(query);
    }

    @ApiOperation("根据ID查询数据")
    @Override
    default Result<T> get(@RequestParam String id) {
        return ApiImpl.super.get(id);
    }

    @ApiOperation("根据ID批量删除数据")
    @Override
    default Result<List<T>> batchGet(@RequestParam List<String> ids) {
        return ApiImpl.super.batchGet(ids);
    }

    @ApiOperation("获取所有的数据")
    @Override
    default Result<List<T>> getAll(@RequestParam(required = false, defaultValue = "*") String select) {
        return ApiImpl.super.getAll(select);
    }

    @ApiOperation("根据查询条件获取数据")
    @Override
    default Result<List<T>> query(@RequestBody Query<T> query) {
        return ApiImpl.super.query(query);
    }

    @ApiOperation("根据多条数据")
    @Override
    default Result<List<T>> list(@RequestParam Long current, @RequestParam Long count) {
        return ApiImpl.super.list(current, count);
    }

    @ApiOperation("分页获取数据")
    @Override
    default Result<IPage<T>> page(Long current, Long size) {
        return ApiImpl.super.page(current, size);
    }

    @ApiOperation("根据查询条件分页获取数据")
    @Override
    default Result<IPage<T>> pageQuery(@RequestBody Query<T> query) {
        return ApiImpl.super.pageQuery(query);
    }
}
