package cn.qqhxj.common.web.controller;

import cn.qqhxj.common.web.Query;
import cn.qqhxj.common.web.Result;
import cn.qqhxj.common.web.TreeNode;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.List;


public interface TreeSwaggerApi<T extends TreeNode<T>> extends SwaggerApi<T>, TreeApiImpl<T> {

    @ApiOperation("获取树")
    @Override
    default Result<List<T>> tree(@RequestParam(defaultValue = "*") String select, @RequestParam(required = false) Serializable pid) {
        return TreeApiImpl.super.tree(select, pid);
    }

    @Override
    @ApiOperation("根据查询条件分页获取数据")
    default Result<IPage<T>> treePageQuery(@RequestBody Query<T> query) {
        return TreeApiImpl.super.treePageQuery(query);
    }
}
