package com.howtodoinjava.jerseydemo.Configuration;

import com.howtodoinjava.jerseydemo.Controller.TomTomController;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;
 
@Component
public class JerseyConfig extends ResourceConfig 
{
    public JerseyConfig() 
    {
        register(TomTomController.class);
    }
}
