package com.aib906.freyr.plugin;

import cn.hutool.core.date.DateUtil;
import com.aib906.freyr.common.plugin.BaseInputPlugin;

import java.util.Map;

/**
 * @Author: zjs
 * @Date: 2021/2/5 10:59 上午
 */

public class MyInputPlugin implements BaseInputPlugin {
    @Override
    public String getPluginName() {
        return null;
    }

    @Override
    public String getIdentity() {
        return null;
    }

    @Override
    public String getPluginDesc() {
        return null;
    }

    @Override
    public Map<String, String> getAttributes() {
        return null;
    }

    @Override
    public void init() {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Object run(Object o) {
        String now = DateUtil.now();
        System.out.println(now);
        System.out.println(o);
        return "input finished";
    }
}
