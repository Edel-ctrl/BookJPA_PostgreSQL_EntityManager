package telran.java57.bookpostgresql.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import telran.java57.bookpostgresql.model.Book;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class BookRepositoryImpl implements BookRepository{

    @PersistenceContext
    EntityManager em; // EntityManager is used to interact with the persistence context

    @Override
    public Stream<Book> findBookByAuthorsName(String name) {
     return em.createQuery(
                "SELECT b FROM Book b JOIN b.authors a WHERE a.name = :name",
                     Book.class)
                .setParameter("name", name)
                .getResultStream();
    }

    @Override
    public Stream<Book> findBookByPublisherPublisherName(String publisher) {
        return em.createQuery(
                "SELECT b FROM Book b WHERE b.publisher.publisherName = :publisher",
                Book.class)
                .setParameter("publisher", publisher)
                .getResultStream();
    }

    @Override
    public void deleteByAuthorsName(String authorName) {

     findBookByAuthorsName(authorName)
//             .forEach(em::remove);
                .forEach(book -> em.remove(em.contains(book) ? book : em.merge(book)));

    }

    @Override
    public boolean existsById(String isbn) {
        return em.find(Book.class, isbn)!=null;
    }

    @Override
    public Book save(Book book) {
        em.persist(book);
        return book;
    }

    @Override
    public Optional<Book> findById(String isbn) {
 // Using EntityManager to find a book by its ISBN:
 // The find method returns null if the entity is not found, so we wrap it in an Optional.
       return Optional.ofNullable(em.find(Book.class, isbn));
        // Alternatively, can use a TypedQuery to find the book:
        // TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class);
        // query.setParameter("isbn", isbn);
        // return query.getResultList().stream().findFirst();
 // The above code uses a TypedQuery to find the book by its ISBN and returns an Optional containing the book if found.
        // Return query.getResultList().stream().findFirst();
    }

    @Override
    public void deleteById(String isbn) {

    }
}
