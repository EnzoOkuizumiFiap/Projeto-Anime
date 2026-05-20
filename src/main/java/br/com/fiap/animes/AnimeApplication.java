package br.com.fiap.animes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableCaching
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class AnimeApplication {

    public static void main(String[] args) {
        SpringApplication.run(AnimeApplication.class, args);
    }
}
