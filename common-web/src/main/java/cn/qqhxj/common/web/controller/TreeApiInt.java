package cn.qqhxj.common.web.controller;

import cn.qqhxj.common.web.bean.Query;
import cn.qqhxj.common.web.bean.Result;
import cn.qqhxj.common.web.bean.TreeNode;
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
