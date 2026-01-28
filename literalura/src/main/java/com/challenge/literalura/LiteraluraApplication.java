package com.challenge.literalura;

import com.challenge.literalura.service.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

    private final LibroService libroService;

    public LiteraluraApplication(LibroService libroService) {
        this.libroService = libroService;
    }

    public static void main(String[] args) {
        SpringApplication.run(LiteraluraApplication.class, args);
    }

    @Override
    public void run(String... args) {
        Principal principal = new Principal(libroService);
        principal.muestraMenu();
    }
}


