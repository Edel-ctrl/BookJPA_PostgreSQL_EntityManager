package telran.java57.bookpostgresql.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import telran.java57.bookpostgresql.dao.AuthorRepository;
import telran.java57.bookpostgresql.dao.BookRepository;
import telran.java57.bookpostgresql.dao.PublisherRepository;
import telran.java57.bookpostgresql.dto.AuthorDto;
import telran.java57.bookpostgresql.dto.BookDto;
import telran.java57.bookpostgresql.model.Author;
import telran.java57.bookpostgresql.model.Book;
import telran.java57.bookpostgresql.model.Publisher;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    final BookRepository bookRepository;
    final AuthorRepository authorRepository;
    final PublisherRepository publisherRepository;
    final ModelMapper modelMapper;


    @Transactional
    @Override
    public Boolean addBook(BookDto bookDto) {
        if (bookRepository.existsById(bookDto.getIsbn())) {
            return false;
        }
        //Publisher
        Publisher publisher = publisherRepository
                .findById(bookDto.getPublisher())
                .orElse(publisherRepository
                        .save(new Publisher(bookDto.getPublisher())));
        //Author
        Set<Author> authors = bookDto.getAuthors().stream()
                .map(authorDto -> authorRepository.findById(authorDto.getName())
                        .orElse(authorRepository.save(modelMapper.map(authorDto, Author.class))))
                .collect(Collectors.toSet());
        //Book
        Book book = new Book(bookDto.getIsbn(), bookDto.getTitle(), authors, publisher);
        bookRepository.save(book);
        return true;
    }

    @Transactional
    @Override
    public BookDto findBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(book, BookDto.class);
    }

    @Transactional
    @Override
    public BookDto removeBookByIsbn(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        bookRepository.deleteById(book.getIsbn());
        return modelMapper.map(book, BookDto.class);
    }

    @Override
    public BookDto updateTitleForBook(String isbn, String title) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);
        book.setTitle(title);
        return modelMapper.map(bookRepository.save(book), BookDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<BookDto> findBooksByAuthor(String authorName) {

        return bookRepository.findBookByAuthorsName(authorName)
                .map(b -> modelMapper.map(b, BookDto.class))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<BookDto> findBooksByPublisher(String publisherName) {
        return bookRepository.findBookByPublisherPublisherName(publisherName)
                .map(b -> modelMapper.map(b, BookDto.class))
                .toList();
    }

    @Override
    public Iterable<AuthorDto> findBookAuthors(String isbn) {
        Book book = bookRepository.findById(isbn).orElseThrow(EntityNotFoundException::new);

        return book.getAuthors().stream()
                .map(a -> modelMapper
                        .map(a,AuthorDto.class))
                .toList();
    }

    @Transactional(readOnly = true)
    @Override
    public Iterable<String> findPublishersByAuthor(String authorName) {
        return publisherRepository
                .findPublisherByAuthor(authorName)
                .toList();
    }

    @Transactional
    @Override
    public AuthorDto removeAuthor(String authorName) {
        Author author = authorRepository.findById(authorName)
                .orElseThrow(EntityNotFoundException::new);
//        bookRepository.deleteByAuthorsName(authorName);
        authorRepository.deleteById(authorName);
        return modelMapper
                .map(author,AuthorDto.class);
    }
}
