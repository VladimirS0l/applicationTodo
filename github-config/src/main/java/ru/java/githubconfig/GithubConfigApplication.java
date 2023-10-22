package ru.java.githubconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class GithubConfigApplication {

    public static void main(String[] args) {
        SpringApplication.run(GithubConfigApplication.class, args);
    }

}
