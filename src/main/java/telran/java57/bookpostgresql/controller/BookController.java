package telran.java57.bookpostgresql.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import telran.java57.bookpostgresql.dto.AuthorDto;
import telran.java57.bookpostgresql.dto.BookDto;
import telran.java57.bookpostgresql.service.BookService;

@RestController
@RequiredArgsConstructor
public class BookController {
    final BookService bookService;

    @PostMapping("/book")
    public Boolean addBook(@RequestBody BookDto bookDto){
        return bookService.addBook(bookDto);
    }

    @GetMapping("/book/{isbn}")
    public BookDto findBookByIsbn(@PathVariable String isbn){
        return bookService.findBookByIsbn(isbn);
    }

    @DeleteMapping("/book/{isbn}")
    public BookDto removeBookByIsbn(@PathVariable String isbn){
        return bookService.removeBookByIsbn(isbn);
    }

    @PutMapping("/book/{isbn}/title/{title}")
    public BookDto updateTitleForBook(@PathVariable String isbn, @PathVariable String title){
        return bookService.updateTitleForBook(isbn, title);
    }

    @GetMapping("/books/author/{authorName}")
    public Iterable<BookDto> findBooksByAuthor(@PathVariable String authorName){
        return bookService.findBooksByAuthor(authorName);
    }

    @GetMapping("/books/publisher/{publisherName}")
    public Iterable<BookDto> findBooksByPublisher(@PathVariable String publisherName){
        return bookService.findBooksByPublisher(publisherName);
    }

    @GetMapping("/authors/book/{isbn}")
    public Iterable<AuthorDto> findBookAuthors(@PathVariable String isbn){
        return bookService.findBookAuthors(isbn);
    }

    @GetMapping("/publishers/author/{authorName}")
    public Iterable<String> findPublishersByAuthor(@PathVariable String authorName){
        return bookService.findPublishersByAuthor(authorName);
    }

    @DeleteMapping("/author/{authorName}")
    public AuthorDto removeAuthor(@PathVariable String authorName){
        return bookService.removeAuthor(authorName);
    }
}
