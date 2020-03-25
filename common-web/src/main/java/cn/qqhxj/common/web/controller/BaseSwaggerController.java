package cn.qqhxj.common.web.controller;

import cn.qqhxj.common.web.bean.Query;
import cn.qqhxj.common.web.bean.Result;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author han xinjian
 * @date 2019-08-03 19:21
 **/
public abstract class BaseSwaggerController<T> extends BaseController<T> {

    @ApiOperation("根据查询获取数据")
    @Override
    public Result<T> getOne(@RequestBody T t) {
        return super.getOne(t);
    }

    @ApiOperation("根据查询获取数据")
    @Override
    public Result<T> queryOne(@RequestBody Query<T> query) {
        return super.queryOne(query);
    }

    @Override
    protected <A> Result<A> result(boolean flag, A data, String msg) {
        return super.result(flag, data, msg);
    }

    @Override
    protected <A> Result<A> result(A data) {
        return super.result(data);
    }

    @ApiOperation("添加")
    @Override
    public Result add(@ApiParam @RequestBody T t) {
        return super.add(t);
    }

    @ApiOperation("批量添加")
    @Override
    public Result batchAdd(@ApiParam @RequestBody List<T> ts) {
        return super.batchAdd(ts);
    }

    @ApiOperation("根据ID删除数据")
    @Override
    public Result del(@ApiParam @RequestParam String id) {
        return super.del(id);
    }

    @ApiOperation("根据ID批量删除")
    @Override
    public Result batchDel(@RequestParam List<String> ids) {
        return super.batchDel(ids);
    }

    @ApiOperation("根据条件删除")
    @Override
    public Result batchDel(@RequestBody Query<T> query) {
        return super.batchDel(query);
    }

    @ApiOperation("根据ID更新数据")
    @Override
    public Result update(@RequestBody T t) {
        return super.update(t);
    }

    @ApiOperation("根据ID批量更新数据")
    @Override
    public Result batchUpdate(@RequestBody List<T> ts) {
        return super.batchUpdate(ts);
    }

    @ApiOperation("根据条件批量更新数据")
    @Override
    public Result batchUpdate(@RequestBody Query<T> query) {
        return super.batchUpdate(query);
    }

    @ApiOperation("根据ID查询数据")
    @Override
    public Result<T> get(@RequestParam String id) {
        return super.get(id);
    }

    @ApiOperation("根据ID批量删除数据")
    @Override
    public Result<List<T>> batchGet(@RequestParam List<String> ids) {
        return super.batchGet(ids);
    }

    @ApiOperation("获取所有的数据")
    @Override
    public Result<List<T>> getAll(@RequestParam(required = false, defaultValue = "*") String select) {
        return super.getAll(select);
    }

    @ApiOperation("根据查询条件获取数据")
    @Override
    public Result<List<T>> query(@RequestBody Query<T> query) {
        return super.query(query);
    }

    @ApiOperation("根据多条数据")
    @Override
    public Result<List<T>> list(@RequestParam Long current, @RequestParam Long count) {
        return super.list(current, count);
    }

    @ApiOperation("分页获取数据")
    @Override
    public Result<IPage<T>> page(Long current, Long size) {
        return super.page(current, size);
    }

    @ApiOperation("根据查询条件分页获取数据")
    @Override
    public Result<IPage<T>> page(@RequestBody Query<T> query) {
        return super.page(query);
    }
}
