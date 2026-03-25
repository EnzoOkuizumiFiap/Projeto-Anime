package br.com.fiap.anime;

import br.com.fiap.anime.controllers.AnimeController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class AnimeApplication {
    public static void main(String[] args) {
        SpringApplication.run(AnimeApplication.class, args);
    }

    @GetMapping("/")
    public AnimeController.HealthStatus healthCheck() {
        return new AnimeController.HealthStatus("OK", "API is Running");
    }
}
