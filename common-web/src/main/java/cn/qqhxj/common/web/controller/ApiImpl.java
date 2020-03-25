package cn.qqhxj.common.web.controller;

import cn.qqhxj.common.web.Query;
import cn.qqhxj.common.web.Result;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface ApiImpl<T> extends ApiInt<T> {
    default <A> Result<A> result(boolean flag, A data, String msg) {
        return flag ? Result.ok(data) : Result.err(msg);
    }

    default <A> Result<A> result(A data) {
        if (data instanceof Boolean) {
            return result(((Boolean) data), data, null);
        }
        return result(data != null, data, null);
    }

    default Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 0);
    }


    @PostMapping("/add")
    @Override
    default Result add(@RequestBody T t) {
        return result(getService().save(t), null, "数据插入失败");
    }

    @PostMapping("/batchAdd")
    @Override
    default Result batchAdd(@RequestBody List<T> ts) {
        return result(getService().saveBatch(ts), null, "数据插入失败");
    }


    @GetMapping("/del")
    @Override
    default Result del(@RequestParam String id) {
        return result(getService().removeById(id), null, "数据删除失败");
    }


    @GetMapping("/batchDelIds")
    @Override
    default Result batchDel(@RequestParam List<String> ids) {
        ArrayList<String> arrayList = new ArrayList<>();
        for (String id : ids) {
            String[] split = id.split(",");
            List<String> strings = Arrays.asList(split);
            arrayList.addAll(strings);
        }
        return result(getService().removeByIds(arrayList), null, "数据删除失败");
    }

    @PostMapping("/batchDelQuery")
    @Override
    default Result batchDelQuery(@RequestBody Query<T> query) {
        return result(getService().remove(query.getQueryWrapper()), null, "数据删除失败");
    }

    @PostMapping("/update")
    @Override
    default Result update(@RequestBody T t) {
        return result(getService().updateById(t), null, "数据修改失败");
    }


    @PostMapping("/batchUpdate")
    @Override
    default Result batchUpdate(@RequestBody List<T> ts) {
        return result(getService().updateBatchById(ts), null, "数据修改失败");
    }

    @PostMapping("/batchUpdateQuery")
    @Override
    default Result batchUpdateQuery(@RequestBody Query<T> query) {
        return result(getService().update(query.getUpdate(), query.getQueryWrapper()), null, "数据修改失败");
    }

    @GetMapping("/get")
    @Override
    default Result<T> get(@RequestParam String id) {
        return result(getService().getById(id));
    }

    @GetMapping("/getIds")
    @Override
    default Result<List<T>> batchGet(@RequestParam List<String> ids) {
        List<T> ts = ((List<T>) getService().listByIds(ids));
        return result(ts);
    }

    @GetMapping("/getAll")
    @Override
    default Result<List<T>> getAll(String select) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(select);
        return result(getService().list(queryWrapper));
    }

    /**
     * result
     *
     * @param query 查询参数
     * @return list
     */
    @PostMapping("/query")
    @Override
    default Result<List<T>> query(@RequestBody Query<T> query) {
        return result(getService().list(query.getQueryWrapper()));
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
    default Result<List<T>> list(@RequestParam Long current, @RequestParam Long count) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<T>();
        queryWrapper.last("limit " + current + " , " + count);
        return result(getService().list(queryWrapper));
    }

    @GetMapping("/page")
    @Override
    default Result<IPage<T>> page(@RequestParam Long current, @RequestParam Long size) {
        Page<T> page = new Page<>();
        page.setSize(size);
        page.setCurrent(current);
        return result(getService().page(page));
    }

    @PostMapping("/pageQuery")
    @Override
    default Result<IPage<T>> pageQuery(@RequestBody Query<T> query) {
        return result(getService().page(query.getPage(), query.getQueryWrapper()));
    }

}
