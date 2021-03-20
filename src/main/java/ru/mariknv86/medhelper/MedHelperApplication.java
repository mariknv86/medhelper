package ru.mariknv86.medhelper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"ru.mariknv86.medhelper"})
@EntityScan("ru.mariknv86.medhelper")
@EnableJpaRepositories("ru.mariknv86.medhelper")
public class MedHelperApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedHelperApplication.class, args);

    }
}
