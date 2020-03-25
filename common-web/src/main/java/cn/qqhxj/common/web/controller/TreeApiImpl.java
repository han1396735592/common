package cn.qqhxj.common.web.controller;

import cn.qqhxj.common.web.Query;
import cn.qqhxj.common.web.Result;
import cn.qqhxj.common.web.TreeNode;
import cn.qqhxj.common.web.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;

public interface TreeApiImpl<T extends TreeNode<T>> extends ApiImpl<T>, TreeApiInt<T> {
    @GetMapping("/tree")
    @Override
    default Result<List<T>> tree(@RequestParam(defaultValue = "*") String select, @RequestParam(required = false) Serializable pid) {
        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(select);
        List<T> list = getService().list(queryWrapper);
        List<T> ts = TreeUtil.listToTree(list, pid);
        return result(ts);
    }

    @PostMapping("/treePageQuery")
    @Override
    default Result<IPage<T>> treePageQuery(@RequestBody Query<T> query) {
        query.setPageSize(-1);
        Result<IPage<T>> page = ApiImpl.super.pageQuery(query);
        IPage<T> data = page.getData();
        List<T> records = data.getRecords();
        List<T> ts = TreeUtil.listToTree(records, null);
        data.setRecords(ts);
        return page;
    }
}
