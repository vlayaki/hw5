package ru.otus.yakimov.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.yakimov.hw5.dao.AuthorDao;
import ru.otus.yakimov.hw5.domain.Author;
import ru.otus.yakimov.hw5.service.AuthorService;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public void add(Author author) {
        authorDao.add(author);
    }

    @Override
    public Author findById(long id) {
        return authorDao.findById(id);
    }
}
