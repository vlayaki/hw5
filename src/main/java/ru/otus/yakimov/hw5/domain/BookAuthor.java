package ru.otus.yakimov.hw5.domain;

import lombok.Data;

@Data
public class BookAuthor {
    public final String bookId;
    public final Long authorId;
}
