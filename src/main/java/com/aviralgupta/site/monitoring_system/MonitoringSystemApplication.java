package com.aviralgupta.site.monitoring_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.*;

@SpringBootApplication
public class MonitoringSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitoringSystemApplication.class, args);

//        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
//
//        ScheduledFuture<?> future = service.scheduleWithFixedDelay(() -> {
//            System.out.println("Hello world");
//        }, 0, 2, TimeUnit.SECONDS);



    }

}
