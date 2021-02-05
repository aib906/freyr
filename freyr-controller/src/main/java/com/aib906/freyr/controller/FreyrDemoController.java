package com.aib906.freyr.controller;

import com.aib906.freyr.common.bean.Configuration;
import com.aib906.freyr.common.bean.PluginChain;
import com.aib906.freyr.common.bean.RestResult;
import com.aib906.freyr.common.exception.FreyrPluginException;
import com.aib906.freyr.common.plugin.Pluginable;
import com.aib906.freyr.core.container.FreyrCommonContainer;
import com.aib906.freyr.core.loader.PluginLoaderManager;
import com.aib906.freyr.core.manager.container.FreyrContainerManager;
import com.aib906.freyr.core.manager.plugin.FreyrPluginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;

/**
 * @Author: zjs
 * @Date: 2021/2/4 10:17 下午
 */
@RestController
public class FreyrDemoController {


    @Autowired
    private PluginLoaderManager pluginLoaderManager;

    @Autowired
    private FreyrPluginManager pluginManager;

    @Autowired
    private FreyrContainerManager containerManager;



    @GetMapping("/begin")
    public RestResult testBegin(){
        Configuration configuration = new Configuration();
        try {
            PluginChain pluginChain = pluginManager.getPluginChain(configuration);
            FreyrCommonContainer freyrCommonContainer = containerManager.provideContainer(pluginChain);
            freyrCommonContainer.start();
            return RestResult.success();
        } catch (FreyrPluginException e) {
            e.printStackTrace();
            return RestResult.error(e.getMessage());
        }
    }

    @GetMapping("/end")
    public RestResult testEnd(){
        try {
            containerManager.destroy("aib906");
            return RestResult.success();
        } catch (Exception e) {
            e.printStackTrace();
            return RestResult.error(e.getMessage());
        }
    }



}
