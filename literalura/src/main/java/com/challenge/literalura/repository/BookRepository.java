package com.challenge.literalura.repository;

import com.challenge.literalura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByTitleIgnoreCase(String title);

    List<Book> findByLanguage(String language);
}
