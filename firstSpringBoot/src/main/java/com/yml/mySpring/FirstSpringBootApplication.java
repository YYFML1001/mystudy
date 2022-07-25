package com.yml.mySpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * springboot项目的启动类
 */
@SpringBootApplication //springboot核心注解，主要同于开启spring自动配置
public class FirstSpringBootApplication {

    //springboot的代码必须放到启动类的统计或者夏季目录中
    public static void main(String[] args) {
        SpringApplication.run(FirstSpringBootApplication.class, args);
    }

}
