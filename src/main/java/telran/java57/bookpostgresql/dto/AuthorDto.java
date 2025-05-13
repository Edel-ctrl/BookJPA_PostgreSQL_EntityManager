package telran.java57.bookpostgresql.dto;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class AuthorDto {
    String name;
    LocalDate birthDate;
}
