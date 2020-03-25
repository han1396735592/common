package cn.qqhxj.common.web.util;

import cn.qqhxj.common.web.bean.TreeNode;

import java.io.Serializable;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author han xinjian
 * @date 2019-03-30 19:07
 **/
public class TreeUtil {

    public static <T extends TreeNode> List<T> listToTree(List<T> list, Serializable id) {
        if (list.isEmpty()) {
            return null;
        }
        List<T> collect = list.stream()
                .filter(node ->
                        String.valueOf(id).equals(String.valueOf(node.getPid())))
                .collect(Collectors.toList());
        for (TreeNode treeNode : collect) {
            list.remove(treeNode);
            treeNode.setChildren(listToTree(list, treeNode.getId()));
        }
        return collect.isEmpty() ? null : collect;
    }

    public static <T extends TreeNode> List<T> map(List<T> list, Function<T, T> mapper) {
        Stream<T> stream = list.stream().map(mapper);
        return stream.collect(Collectors.toList());
    }

}
