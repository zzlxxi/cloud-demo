package com.w2site.conf;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by TR_VMHyper on 2019/4/29.
 *
 * @author TR_VMHyper
 */
@Component
public class StoreIntegration {
    @HystrixCommand(fallbackMethod = "stubMyService",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE")
            })
    public Object getStores(Map<String, Object> parameters) {
        //do stuff that might fail
        return parameters;
    }

    public Object defaultStores(Map<String, Object> parameters) {
        /* something useful */
        return parameters;
    }
}
