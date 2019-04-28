package com.w2site;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by TR_VMHyper on 2019/4/28.
 *
 * @author TR_VMHyper
 */
@RestController
@SpringBootApplication
public class Application {
    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
