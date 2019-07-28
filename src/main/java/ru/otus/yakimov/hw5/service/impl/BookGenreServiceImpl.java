package ru.otus.yakimov.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.yakimov.hw5.dao.BookGenreDao;
import ru.otus.yakimov.hw5.domain.BookGenre;
import ru.otus.yakimov.hw5.service.BookGenreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookGenreServiceImpl implements BookGenreService {

    private final BookGenreDao bookGenreDao;

    @Override
    public void add(List<BookGenre> bookGenreList) {
        bookGenreDao.add(bookGenreList);
    }

    @Override
    public List<BookGenre> findByBookId(String bookId) {
        return bookGenreDao.findByBookId(bookId);
    }

    @Override
    public void delete(String bookId) {
        bookGenreDao.delete(bookId);
    }
}
