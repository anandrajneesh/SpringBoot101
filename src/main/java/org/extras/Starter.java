package org.extras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by Anand_Rajneesh on 2/20/2017.
 */
@SpringBootApplication(scanBasePackages = "org.extras")
@EnableConfigurationProperties
public class Starter {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Starter.class, args);
    }
}
