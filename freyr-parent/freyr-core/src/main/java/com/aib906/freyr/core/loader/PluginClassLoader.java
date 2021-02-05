package com.aib906.freyr.core.loader;

import com.aib906.freyr.common.plugin.BaseFilterPlugin;
import com.aib906.freyr.common.plugin.BaseInputPlugin;
import com.aib906.freyr.common.plugin.BaseOutputPlugin;
import com.aib906.freyr.common.plugin.Pluginable;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: zjs
 * @Date: 2021/1/30 4:59 下午
 */
public class PluginClassLoader extends URLClassLoader implements PluginLoader {

    private String loaderIdentity;

    /**
     * 已注册的插件
     *
     * @Key: plugin的name
     * @Value: concurrentHashMap  其中 key为版本号  value为Pluginable实例 / class
     */
    private final ConcurrentHashMap<String, ConcurrentHashMap<String, Pluginable>> registeredPlugin = new ConcurrentHashMap<>();

    public PluginClassLoader(String identity, URL[] urls) {
        super(urls);
        this.loaderIdentity = identity;
    }

    public PluginClassLoader(String loaderIdentity, URL[] urls, ClassLoader parent) {
        super(urls, parent);
        this.loaderIdentity = loaderIdentity;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    @Override
    public Pluginable getPlugin(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> pluginClz = this.findClass(className);
        Object pluginObj = pluginClz.newInstance();
        if(pluginObj instanceof BaseInputPlugin) {
            BaseInputPlugin inputPlugin = (BaseInputPlugin) pluginObj;
            inputPlugin.init();
            return inputPlugin;
        }else if(pluginObj instanceof BaseFilterPlugin){
            BaseFilterPlugin filterPlugin = (BaseFilterPlugin) pluginObj;
            filterPlugin.init();
            return filterPlugin;
        }else if(pluginObj instanceof BaseOutputPlugin){
            BaseOutputPlugin outputPlugin = (BaseOutputPlugin) pluginObj;
            outputPlugin.init();
            return outputPlugin;
        }else {
            throw new RuntimeException("未匹配到相对应的插件类型");
        }
    }
}
