package com.rest.jersey.application;

import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.server.mvc.MvcFeature;


import com.rest.jersey.resource.UserResource;

public class MyApplication extends ResourceConfig {

    public MyApplication() {
        register(org.glassfish.jersey.server.spring.scope.RequestContextFilter.class);
        register(org.glassfish.jersey.server.spring.SpringComponentProvider.class);
        //        register(MvcFeature.class);
        //        property(MvcFeature.TEMPLATE_BASE_PATH, "/WEB-INF/views");
        register(UserResource.class);
    }

}
