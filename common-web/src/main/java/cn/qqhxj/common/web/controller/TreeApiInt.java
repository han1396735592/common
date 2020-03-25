package cn.qqhxj.common.web.controller;

import cn.qqhxj.common.web.Query;
import cn.qqhxj.common.web.Result;
import cn.qqhxj.common.web.TreeNode;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.List;

public interface TreeApiInt<T extends TreeNode<T>> extends ApiInt<T> {
    Result<List<T>> tree(String select, Serializable pid);
    /**
     * 根据条件分页查询
     *
     * @param query query
     * @return result
     */
    Result<IPage<T>> treePageQuery(Query<T> query);
}
