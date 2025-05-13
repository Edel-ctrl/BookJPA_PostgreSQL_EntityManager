package telran.java57.bookpostgresql.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "name")
public class Author {
    @Id
    String name;
    LocalDate birthDate;
    @ManyToMany(mappedBy = "authors", cascade = CascadeType.REMOVE) // to delete books when author is deleted
    Set<Book> books;

    public Author(String name, LocalDate birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }
}
