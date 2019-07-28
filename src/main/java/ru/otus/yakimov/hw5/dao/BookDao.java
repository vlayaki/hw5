package ru.otus.yakimov.hw5.dao;

import ru.otus.yakimov.hw5.domain.Book;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookDao {
    Book findById(String id);

    List<Book> findBooks();

    Map<String, Set<Long>> getBookIdToAuthorIdsMap();

    Map<String, Set<Long>> getBookIdToGenreIdsMap();

    void add(Book book);

}
