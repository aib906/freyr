package com.aib906.freyr.core.manager.plugin;

import com.aib906.freyr.common.bean.Configuration;
import com.aib906.freyr.common.bean.PluginChain;
import com.aib906.freyr.common.exception.FreyrPluginException;
import com.aib906.freyr.common.plugin.Pluginable;
import com.aib906.freyr.core.loader.PluginClassLoader;
import com.aib906.freyr.core.loader.PluginLoaderManager;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;

/**
 * @Author: zjs
 * @Date: 2021/1/30 11:56 上午
 */
@Component
public class FreyrPluginManager extends AbstractPluginManager {

    @Autowired
    private PluginLoaderManager pluginLoaderManager;

    public PluginChain getPluginChain(Configuration configuration) {
        String identity = "aib906";
        //根据configuration加载plugin
        //构建对应的plugin
        //调用pluginLoader加载
        String jarPath = "/Users/zhangjianshi/aib906/plugin";
        if(pluginLoaderManager==null){
            pluginLoaderManager= new PluginLoaderManager();
        }
        PluginClassLoader pluginClassLoader = pluginLoaderManager.loadPath(identity, jarPath);

        try {
            Pluginable input = pluginClassLoader.getPlugin("com.aib906.freyr.plugin.MyInputPlugin");
            Pluginable filter = pluginClassLoader.getPlugin("com.aib906.freyr.plugin.MyFilterPlugin");
            Pluginable output = pluginClassLoader.getPlugin("com.aib906.freyr.plugin.MyOutputPlugin");
            PluginChain pluginChain;
            if (ObjectUtils.isEmpty(filter)) {
                pluginChain = PluginChain.getPluginChainForPlugins(input, output);
            }else {
                pluginChain = PluginChain.getPluginChainForPlugins(input, filter, output);
            }
            pluginChain.setName(identity);
            return pluginChain;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new FreyrPluginException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new FreyrPluginException(e);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new FreyrPluginException(e);
        }
    }

}
