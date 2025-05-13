package telran.java57.bookpostgresql.model;

import jakarta.persistence.*;
import lombok.*;
import telran.java57.bookpostgresql.dto.AuthorDto;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "isbn")
@Table(name = "book")
public class Book {
    @Id
    String isbn;
    String title;
    @ManyToMany
    @JoinTable(name = "book_authors",
            joinColumns = @JoinColumn(name = "book_isbn"),
            inverseJoinColumns = @JoinColumn(name = "authors_name"))
    Set<Author> authors;
    @ManyToOne
    Publisher publisher;
}
