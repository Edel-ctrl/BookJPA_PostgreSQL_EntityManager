package telran.java57.bookpostgresql.dao;

import telran.java57.bookpostgresql.model.Author;

import java.util.Optional;

public interface AuthorRepository {

    Optional<Author> findById(String name);

    Author save(Author author);

    void deleteById(String authorName);
}
