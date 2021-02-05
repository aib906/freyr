package com.aib906.freyr.common.plugin;

import java.util.Map;

/**
 * @Author: zjs
 * @Date: 2021/1/30 5:01 下午
 */
public interface BasePluginInfo {

    String getPluginName();

    String getIdentity();

    String getPluginDesc();

    Map<String, String> getAttributes();
}
