package cn.qqhxj.common.web.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author han xinjian
 * @date 2019-03-30 19:13
 **/
public interface TreeNode<T> {

    /**
     * id
     *
     * @return
     */
    Serializable getId();


    /**
     * pid
     *
     * @return
     */
    Serializable getPid();


    /**
     * 获取孩子们
     *
     * @return
     */
    List<T> getChildren();


    /**
     * 设置孩子们
     *
     * @param children
     */
    void setChildren(List<T> children);
}
