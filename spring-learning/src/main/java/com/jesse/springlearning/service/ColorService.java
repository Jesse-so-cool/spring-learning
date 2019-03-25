package com.jesse.springlearning.service;

import com.jesse.springlearning.dao.ColorDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColorService {

    /**
     * 自动装配
     *
     * @Autowired 先通过类型找  如果找到多个再通过colorDao这个name去找
     * colorDao2的情况下 找到配置类下的ColorDao{label='2'}
     * 可标注在构造器 方法 参数 属性 上
     * @Resource 通过组件名称进行装配 是java提供的
     */
    //@Autowired
    private ColorDao colorDao2;

    @Override
    public String toString() {
        return "ColorService{" +
                "colorDao=" + colorDao2.toString() +
                '}';
    }

    //@Autowired
    public ColorService(ColorDao colorDao) {
        this.colorDao2 = colorDao;
    }

    public ColorService() {

    }

    //@Autowired
    public void setColorDao2(ColorDao colorDao2) {
        this.colorDao2 = colorDao2;
    }
}
