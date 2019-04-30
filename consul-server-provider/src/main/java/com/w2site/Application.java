package com.w2site;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by TR_VMHyper on 2019/4/28.
 *
 * @author TR_VMHyper
 */
@RestController
@SpringBootApplication
public class Application {

  @Value("${spring.application.name}")
  private String serverName;

  @Autowired private RestTemplate restTemplate;
  @Autowired private DiscoveryClient discoveryClient;

  @RequestMapping("/")
  public String home() {
    return "Hello world";
  }

  @RequestMapping("/uri")
  public String uri() {
    List<ServiceInstance> instances = discoveryClient.getInstances(serverName);
    if (isEmpty(instances)) {
      return instances.get(0).getUri().toString();
    }
    return null;
  }

  /**
   * 判断集合是否为空
   *
   * @param instances
   * @return
   */
  private boolean isEmpty(List<?> instances) {
    return instances != null && instances.size() > 0;
  }

  @RequestMapping("/health")
  public String health() {
    return "UP";
  }

  @RequestMapping("/health/info")
  public String getHealth() {
    String health = restTemplate.getForObject("http://" + serverName + "/health", String.class);
    return "WEAK" + health + " " + System.currentTimeMillis();
  }

  @Bean
  @LoadBalanced
  public RestTemplate loadBalancedRestTemplate() {
    return new RestTemplate();
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
