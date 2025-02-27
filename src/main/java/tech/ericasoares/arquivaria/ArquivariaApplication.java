package tech.ericasoares.arquivaria;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ArquivariaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArquivariaApplication.class, args);
    }

    // Encripted password for user using BCrypt encoder
    @Bean
    ApplicationRunner runner(PasswordEncoder passwordEncoder) {
        return args -> System.out.println(passwordEncoder.encode("password"));
    }
}
