package com.aritra.blog;

import com.aritra.blog.config.StorageProperties;
import com.aritra.blog.service.StorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class BlogApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BlogApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BlogApp.class);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+06:00"));
        System.out.println("====== Timezone set to Dhaka ======");
    }

    @Bean
    CommandLineRunner init(StorageService storageService) {
        return (args) -> {
            //   storageService.deleteAll();
            storageService.init();
        };
    }
}
