package telran.java57.bookpostgresql.service;

import telran.java57.bookpostgresql.dto.AuthorDto;
import telran.java57.bookpostgresql.dto.BookDto;

public interface BookService {
    Boolean addBook(BookDto bookDto);

    BookDto findBookByIsbn(String isbn);

    BookDto removeBookByIsbn(String isbn);

    BookDto updateTitleForBook(String isbn, String title);

    Iterable<BookDto> findBooksByAuthor(String authorName);

    Iterable<BookDto> findBooksByPublisher(String publisherName);

    Iterable<AuthorDto> findBookAuthors(String isbn);

    Iterable<String> findPublishersByAuthor(String authorName);

    AuthorDto removeAuthor(String authorName);
}
