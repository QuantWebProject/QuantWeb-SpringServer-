package com.quantweb.springserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringserverApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringserverApplication.class, args);
  }
}
