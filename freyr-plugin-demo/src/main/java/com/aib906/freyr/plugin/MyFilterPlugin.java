package com.aib906.freyr.plugin;

import com.aib906.freyr.common.plugin.BaseFilterPlugin;

import java.util.Map;

/**
 * @Author: zjs
 * @Date: 2021/2/5 11:01 上午
 */
public class MyFilterPlugin implements BaseFilterPlugin {
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
        System.out.println(o);
        return "filter finished";
    }
}
