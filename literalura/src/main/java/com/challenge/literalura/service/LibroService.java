package com.challenge.literalura.service;

import com.challenge.literalura.dto.AuthorDTO;
import com.challenge.literalura.dto.BookDTO;
import com.challenge.literalura.model.Author;
import com.challenge.literalura.model.Book;
import com.challenge.literalura.repository.AuthorRepository;
import com.challenge.literalura.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public LibroService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }


    public void guardarLibro(BookDTO dto) {

        if (bookRepository.findByTitleIgnoreCase(dto.getTitle()).isPresent()) {
            System.out.println(" El libro ya existe en la base de datos");
            return;
        }

        Book libro = new Book();
        libro.setTitle(dto.getTitle());
        libro.setLanguage(dto.getLanguages().get(0));
        libro.setDownloadCount(dto.getDownload_count());

        AuthorDTO autorDTO = dto.getAuthors().get(0);

        Author autor = authorRepository
                .findAll()
                .stream()
                .filter(a -> a.getName().equalsIgnoreCase(autorDTO.getName()))
                .findFirst()
                .orElseGet(() -> {
                    Author nuevo = new Author();
                    nuevo.setName(autorDTO.getName());
                    nuevo.setBirthYear(autorDTO.getBirth_year());
                    nuevo.setDeathYear(autorDTO.getDeath_year());
                    return authorRepository.save(nuevo);
                });

        libro.setAuthor(autor);
        bookRepository.save(libro);

        System.out.println(" Libro guardado correctamente");
    }


    public void listarLibros() {
        List<Book> libros = bookRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("📭 No hay libros registrados");
            return;
        }

        libros.forEach(libro -> {
            System.out.println("""
                    📘 Título: %s
                    Autor: %s
                    Idioma: %s
                    Descargas: %d
                    """.formatted(
                    libro.getTitle(),
                    libro.getAuthor().getName(),
                    libro.getLanguage(),
                    libro.getDownloadCount()
            ));
        });
    }


    public void listarAutores() {
        List<Author> autores = authorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("📭 No hay autores registrados");
            return;
        }

        autores.forEach(autor -> {
            System.out.println("""
                    👤 Autor: %s
                    Nacimiento: %d
                    Fallecimiento: %s
                    """.formatted(
                    autor.getName(),
                    autor.getBirthYear(),
                    autor.getDeathYear() != null ? autor.getDeathYear() : "Vivo"
            ));
        });
    }


    public void autoresVivosEnAnio(int anio) {
        List<Author> autores = authorRepository.findAll();

        autores.stream()
                .filter(a ->
                        a.getBirthYear() != null &&
                                a.getBirthYear() <= anio &&
                                (a.getDeathYear() == null || a.getDeathYear() > anio)
                )
                .forEach(a ->
                        System.out.println("👤 " + a.getName())
                );
    }


    public void librosPorIdioma(String idioma) {
        List<Book> libros = bookRepository.findByLanguage(idioma);

        if (libros.isEmpty()) {
            System.out.println("📭 No hay libros en ese idioma");
            return;
        }

        libros.forEach(libro ->
                System.out.println("📘 " + libro.getTitle())
        );
    }
}


