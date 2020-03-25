package cn.qqhxj.common.web.controller;


import cn.qqhxj.common.web.bean.Query;
import cn.qqhxj.common.web.bean.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author han xinjian
 * @date 2019-08-03 18:53
 **/
public abstract class BaseController<T> implements ApiController<T> {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    protected IService<T> service;


    protected <A> Result<A> result(boolean flag, A data, String msg) {
        return flag ? Result.ok(data) : Result.err(msg);
    }

    protected <A> Result<A> result(A data) {
        if (data instanceof Boolean) {
            return result(((Boolean) data), data, null);
        }
        return result(data != null, data, null);
    }
    @PostMapping("/getOne")
    @Override
    public Result<T> getOne(@RequestBody T t) {
        return result(service.getOne(new QueryWrapper<>(t)));
    }

    @PostMapping("/queryOne")
    @Override
    public Result<T> queryOne(@RequestBody Query<T> query) {
        return result(service.getOne(query.getQueryWrapper()));
    }


    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 0);
    }


    @PostMapping("/add")
    @Override
    public Result add(@RequestBody T t) {
        return result(service.save(t), null, "数据插入失败");
    }

    @PostMapping("/batchAdd")
    @Override
    public Result batchAdd(@RequestBody List<T> ts) {
        return result(service.saveBatch(ts), null, "数据插入失败");
    }


    @GetMapping("/del")
    @Override
    public Result del(@RequestParam String id) {
        return result(service.removeById(id), null, "数据删除失败");
    }


    @GetMapping("/batchDelIds")
    @Override
    public Result batchDel(@RequestParam List<String> ids) {
        return result(service.removeByIds(ids), null, "数据删除失败");
    }

    @PostMapping("/batchDelQuery")
    @Override
    public Result batchDel(@RequestBody Query<T> query) {
        return result(service.remove(query.getQueryWrapper()), null, "数据删除失败");
    }

    @PostMapping("/update")
    @Override
    public Result update(@RequestBody T t) {
        return result(service.updateById(t), null, "数据修改失败");
    }


    @PostMapping("/batchUpdate")
    @Override
    public Result batchUpdate(@RequestBody List<T> ts) {
        return result(service.updateBatchById(ts), null, "数据修改失败");
    }

    @PostMapping("/batchUpdateQuery")
    @Override
    public Result batchUpdate(@RequestBody Query<T> query) {
        return result(service.update(query.getUpdate(), query.getQueryWrapper()), null, "数据修改失败");
    }

    @GetMapping("/get")
    @Override
    public Result<T> get(@RequestParam String id) {
        return result(service.getById(id));
    }

    @GetMapping("/getIds")
    @Override
    public Result<List<T>> batchGet(@RequestParam List<String> ids) {
        List<T> ts = ((List<T>) service.listByIds(ids));
        return result(ts);
    }

    @GetMapping("/getAll")
    @Override
    public Result<List<T>> getAll(String select) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(select);
        return result(service.list(queryWrapper));
    }

    /**
     * result
     *
     * @param query 查询参数
     * @return list
     */
    @PostMapping("/query")
    @Override
    public Result<List<T>> query(@RequestBody Query<T> query) {
        return result(service.list(query.getQueryWrapper()));
    }


    /**
     * list
     *
     * @param current 当前
     * @param count   长度
     * @return result
     */

    @GetMapping("/list")
    @Override
    public Result<List<T>> list(@RequestParam Long current, @RequestParam Long count) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        queryWrapper.last("limit " + current + " , " + count);
        return result(service.list(queryWrapper));
    }

    @GetMapping("/page")
    @Override
    public Result<IPage<T>> page(@RequestParam Long current, @RequestParam Long size) {
        Page<T> page = new Page<>();
        page.setSize(size);
        page.setCurrent(current);
        return result(service.page(page));
    }

    @PostMapping("/pageQuery")
    @Override
    public Result<IPage<T>> page(@RequestBody Query<T> query) {
        return result(service.page(query.getPage(), query.getQueryWrapper()));
    }

}
