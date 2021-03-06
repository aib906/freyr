package com.aib906.freyr.core.loader;

import com.aib906.freyr.common.plugin.Pluginable;

/**
 * @Author: zjs
 * @Date: 2021/1/30 10:58 上午
 */
public interface PluginLoader {

    Pluginable getPlugin(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException;
}
