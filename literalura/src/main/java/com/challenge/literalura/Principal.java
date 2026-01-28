package com.challenge.literalura;

import com.challenge.literalura.dto.BookDTO;
import com.challenge.literalura.dto.GutendexResponse;
import com.challenge.literalura.service.GutendexClient;
import com.challenge.literalura.service.LibroService;

import java.util.Scanner;

public class Principal {

    private final Scanner scanner = new Scanner(System.in);
    private final GutendexClient client = new GutendexClient();
    private final LibroService libroService;

    public Principal(LibroService libroService) {
        this.libroService = libroService;
    }

    public void muestraMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                    
                    📚 LITERALURA 📚
                    1 - Buscar libro por título (API)
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un año
                    5 - Listar libros por idioma
                    0 - Salir
                    """);

            System.out.print("Elige una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1 -> buscarLibro();
                case 2 -> libroService.listarLibros();
                case 3 -> libroService.listarAutores();
                case 4 -> autoresVivos();
                case 5 -> librosPorIdioma();
                case 0 -> System.out.println("Hasta pronto 👋");
                default -> System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibro() {
        System.out.print("Escribe el título del libro: ");
        String titulo = scanner.nextLine();

        GutendexResponse response = client.buscarLibro(titulo);

        if (response.getResults() == null || response.getResults().isEmpty()) {
            System.out.println("❌ Libro no encontrado en la API");
            return;
        }

        BookDTO libro = response.getResults().get(0);

        System.out.println("""
                📖 RESULTADO
                Título: %s
                Idiomas: %s
                Descargas: %d
                """.formatted(
                libro.getTitle(),
                libro.getLanguages(),
                libro.getDownload_count()
        ));

        libroService.guardarLibro(libro);
    }

    private void autoresVivos() {
        System.out.print("Ingrese el año: ");
        int anio = scanner.nextInt();
        scanner.nextLine();
        libroService.autoresVivosEnAnio(anio);
    }

    private void librosPorIdioma() {
        System.out.print("Ingrese el idioma (ej: en, es, fr): ");
        String idioma = scanner.nextLine();
        libroService.librosPorIdioma(idioma);
    }
}

