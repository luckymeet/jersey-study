package com;

import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class RestApplication extends ResourceConfig {

    public RestApplication() {
        //资源类所在的包路径
        packages("com.resource");
        //注册 JSON 转换器
//        register(MoxyJsonFeature.class);
    }

}
