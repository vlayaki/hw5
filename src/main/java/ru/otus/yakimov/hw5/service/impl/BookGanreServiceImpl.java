package ru.otus.yakimov.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.yakimov.hw5.dao.BookGanreDao;
import ru.otus.yakimov.hw5.domain.BookGanre;
import ru.otus.yakimov.hw5.service.BookGanreService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookGanreServiceImpl implements BookGanreService {

    private final BookGanreDao bookGanreDao;

    @Override
    public void add(List<BookGanre> bookGanreList) {
        bookGanreDao.add(bookGanreList);
    }

    @Override
    public List<BookGanre> findByBookId(String bookId) {
        return bookGanreDao.findByBookId(bookId);
    }

    @Override
    public void delete(String bookId) {
        bookGanreDao.delete(bookId);
    }
}
