package telran.java57.bookpostgresql.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import telran.java57.bookpostgresql.model.Publisher;

import java.util.Optional;
import java.util.stream.Stream;

@Repository
public class PublisherRepositoryImpl implements PublisherRepository{
    @PersistenceContext
    EntityManager em;

    @Override
    public Stream<String> findPublisherByAuthor(String author) {
        return em.createQuery(
                "SELECT DISTINCT p.publisherName"+
                        " FROM Publisher p JOIN p.books b JOIN b.authors a"+
                        " WHERE a.name = :name",
                String.class)
                .setParameter("name", author)
                .getResultStream();
    }

    @Override
    public Optional<Publisher> findById(String publisher) {
        return Optional.ofNullable(em.find(Publisher.class,publisher));
    }

    @Override
    public Publisher save(Publisher publisher) {
        em.persist(publisher);
        return publisher;
    }
}
