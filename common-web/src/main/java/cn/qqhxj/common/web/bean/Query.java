package cn.qqhxj.common.web.bean;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author han xinjian
 * @date 2019-08-02 17:38
 **/
@ApiModel
public class Query<T> {
    private Map<String, Object> eq = new HashMap<>();
    private Map<String, Object> gt = new HashMap<>();
    private Map<String, Object> le = new HashMap<>();
    private Map<String, Object> lt = new HashMap<>();
    private Map<String, Object> like = new HashMap<>();
    private Map<String, Set> in = new HashMap<>();
    private Map<String, Set> notIn = new HashMap<>();
    private Map<String, Between> between = new HashMap<String, Between>();
    private Map<String, Between> notBetween = new HashMap<String, Between>();
    private Set<String> isNull = new HashSet<String>();
    private Set<String> isNotNull = new HashSet<String>();
    private Set<Order> order = new HashSet<Order>();
    private String select = "*";
    private T entity;
    private T update;
    private long pageSize = 10;
    private long pageCurrent = 1;
    private long count;
    private long current;

    @ApiModelProperty(hidden = true)
    public Page<T> getPage() {
        Page<T> page = new Page<>();
        page.setCurrent(pageCurrent);
        page.setSize(pageSize);
        return page;
    }

    @ApiModelProperty(hidden = true)
    public QueryWrapper<T> getQueryWrapper() {

        QueryWrapper<T> queryWrapper = new QueryWrapper<>();
        queryWrapper.setEntity(entity);
        queryWrapper.select(select);

        if (!eq.isEmpty()) {
            queryWrapper.allEq(eq);
        }
        if (!gt.isEmpty()) {
            gt.forEach(queryWrapper::gt);
        }
        if (!le.isEmpty()) {
            le.forEach(queryWrapper::le);
        }
        if (!lt.isEmpty()) {
            lt.forEach(queryWrapper::lt);
        }
        if (!like.isEmpty()) {
            like.forEach(queryWrapper::like);
        }

        if (count > 0 && current >= 0) {
            queryWrapper.last("limit " + current + " , " + count);
        }

        if (!isNotNull.isEmpty()) {
            isNotNull.forEach((column) -> {
                queryWrapper.isNotNull(!StringUtils.isEmpty(column), column);
            });
        }
        if (!isNull.isEmpty()) {
            isNull.forEach((column) -> {
                queryWrapper.isNull(!StringUtils.isEmpty(column), column);
            });
        }
        if (!in.isEmpty()) {
            in.forEach(queryWrapper::in);
        }
        if (!notIn.isEmpty()) {
            notIn.forEach(queryWrapper::notIn);
        }

        if (!between.isEmpty()) {
            between.forEach((key, value) -> {
                queryWrapper.between(key, null, null);
            });
        }
        if (!between.isEmpty()) {
            between.forEach((key, value) -> {
                queryWrapper.between(key, value.getStart(), value.getEnd());
            });
        }
        if (!notBetween.isEmpty()) {
            notBetween.forEach((key, value) -> {
                queryWrapper.notBetween(key, value.getStart(), value.getEnd());
            });
        }
        if (!order.isEmpty()) {
            order.forEach(item -> {
                queryWrapper.orderBy(true,
                        "ASC".equalsIgnoreCase(item.getType()), item.getColumn());
            });
        }

        return queryWrapper;
    }

    public Map<String, Object> getEq() {
        return eq;
    }

    public void setEq(Map<String, Object> eq) {
        this.eq = eq;
    }

    public Map<String, Object> getGt() {
        return gt;
    }

    public void setGt(Map<String, Object> gt) {
        this.gt = gt;
    }

    public Map<String, Object> getLe() {
        return le;
    }

    public void setLe(Map<String, Object> le) {
        this.le = le;
    }

    public Map<String, Object> getLt() {
        return lt;
    }

    public void setLt(Map<String, Object> lt) {
        this.lt = lt;
    }

    public Map<String, Object> getLike() {
        return like;
    }

    public void setLike(Map<String, Object> like) {
        this.like = like;
    }

    public Map<String, Set> getIn() {
        return in;
    }

    public void setIn(Map<String, Set> in) {
        this.in = in;
    }

    public Map<String, Set> getNotIn() {
        return notIn;
    }

    public void setNotIn(Map<String, Set> notIn) {
        this.notIn = notIn;
    }

    public Map<String, Between> getBetween() {
        return between;
    }

    public void setBetween(Map<String, Between> between) {
        this.between = between;
    }

    public Map<String, Between> getNotBetween() {
        return notBetween;
    }

    public void setNotBetween(Map<String, Between> notBetween) {
        this.notBetween = notBetween;
    }

    public Set<String> getIsNull() {
        return isNull;
    }

    public void setIsNull(Set<String> isNull) {
        this.isNull = isNull;
    }

    public Set<String> getIsNotNull() {
        return isNotNull;
    }

    public void setIsNotNull(Set<String> isNotNull) {
        this.isNotNull = isNotNull;
    }

    public Set<Order> getOrder() {
        return order;
    }

    public void setOrder(Set<Order> order) {
        this.order = order;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    public T getUpdate() {
        return update;
    }

    public void setUpdate(T update) {
        this.update = update;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPageCurrent() {
        return pageCurrent;
    }

    public void setPageCurrent(long pageCurrent) {
        this.pageCurrent = pageCurrent;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getCurrent() {
        return current;
    }

    public void setCurrent(long current) {
        this.current = current;
    }

    public static class Between {
        private Object start;
        private Object end;

        public Object getStart() {
            return start;
        }

        public void setStart(Object start) {
            this.start = start;
        }

        public Object getEnd() {
            return end;
        }

        public void setEnd(Object end) {
            this.end = end;
        }
    }


    public static class Order {
        private String column;
        private String type;

        public void setColumn(String column) {
            this.column = column;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getColumn() {
            return column;
        }

        public String getType() {
            return type;
        }
    }


}
