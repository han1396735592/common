package cn.qqhxj.common.web.controller;

import com.baomidou.mybatisplus.extension.service.IService;

@FunctionalInterface
public interface Api<T> {
    IService<T> getService();
}
