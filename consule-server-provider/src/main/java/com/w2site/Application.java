package com.w2site;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by TR_VMHyper on 2019/4/28.
 *
 * @author TR_VMHyper
 */
@EnableTurbine
@RestController
@EnableCircuitBreaker
@EnableHystrixDashboard
@SpringBootApplication
public class Application {

    @Value("${spring.cloud.consul.discovery.serviceName}")
    private String serviceName;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/uri")
    public String url() {
        List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
        if (list != null && list.size() > 0) {
            return list.get(0).getUri().toString();
        }
        return null;
    }

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
