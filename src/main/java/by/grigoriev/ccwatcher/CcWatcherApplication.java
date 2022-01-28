package by.grigoriev.ccwatcher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CcWatcherApplication {

    public static void main(String[] args) {
        SpringApplication.run(CcWatcherApplication.class, args);
    }
}
