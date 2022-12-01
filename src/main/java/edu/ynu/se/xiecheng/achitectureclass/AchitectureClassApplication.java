package edu.ynu.se.xiecheng.achitectureclass;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class AchitectureClassApplication {

    public static void main(String[] args) {
        SpringApplication.run(AchitectureClassApplication.class, args);
    }

}
