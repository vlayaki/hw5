package ru.otus.yakimov.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.yakimov.hw5.dao.GenreDao;
import ru.otus.yakimov.hw5.domain.Genre;
import ru.otus.yakimov.hw5.service.GenreService;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public void add(Genre genre) {
        genreDao.add(genre);
    }

    @Override
    public Genre findById(long id) {
        return genreDao.findById(id);
    }
}
