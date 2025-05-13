package telran.java57.bookpostgresql.dao;

import telran.java57.bookpostgresql.model.Publisher;

import java.util.Optional;
import java.util.stream.Stream;

public interface PublisherRepository{

//    @Query("select distinct p.publisherName from Book b join b.publisher p" +
//            " join b.authors a where a.name=?1")
    Stream<String> findPublisherByAuthor(String author);

    Optional<Publisher> findById(String publisher);

    Publisher save(Publisher publisher);
}
