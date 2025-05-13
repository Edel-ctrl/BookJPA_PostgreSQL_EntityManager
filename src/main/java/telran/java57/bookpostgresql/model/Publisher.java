package telran.java57.bookpostgresql.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@EqualsAndHashCode(of = "publisherName")
public class Publisher {
    @Id
    String publisherName;
    @OneToMany(mappedBy = "publisher")
    Set<Book> books;

    public Publisher(String publisherName) {
        this.publisherName = publisherName;
    }

    @Override
    public String toString(){
        return publisherName;
    }
}
