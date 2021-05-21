package com.francis.weekeighttasktodoapplication;

/*Created by Francis Oyiogu
  May 20, 2021
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class WeekEightTaskToDoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeekEightTaskToDoApplication.class, args);
    }


}
