package ru.otus.yakimov.hw5.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.yakimov.hw5.dao.BookDao;
import ru.otus.yakimov.hw5.domain.Author;
import ru.otus.yakimov.hw5.domain.Book;
import ru.otus.yakimov.hw5.domain.Genre;
import ru.otus.yakimov.hw5.service.AuthorService;
import ru.otus.yakimov.hw5.service.BookService;
import ru.otus.yakimov.hw5.service.GenreService;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorService authorService;
    private final GenreService genreService;

    @Override

    public void add(Book book) {
        bookDao.add(book);
    }

    @Override
    public Book findById(String isbn) {
        return bookDao.findById(isbn);
    }

    @Override
    public List<Book> findBooks() {
        List<Book> res = bookDao.findBooks();
        Map<String, Set<Long>> bookIdToAuthorIds = bookDao.getBookIdToAuthorIdsMap();
        Map<Long, Author> authorIdToAuthor = authorService.getIdToAuthorMap();
        fillBooksWithAuthors(res, bookIdToAuthorIds, authorIdToAuthor);
        Map<String, Set<Long>> bookIdToGenreIds = bookDao.getBookIdToGenreIdsMap();
        Map<Long, Genre> genreIdToGenre = genreService.getGenreIdToGenreMap();
        fillBooksWithGenres(res, bookIdToGenreIds, genreIdToGenre);
        return res;
    }

    private void fillBooksWithAuthors(List<Book> books, Map<String, Set<Long>> bookIdToAuthorIds, Map<Long, Author> authorIdToAuthor) {
        for (Book book : books) {
            Set<Long> authorIds = bookIdToAuthorIds.get(book.getIsbn());
            for (Long authorId : authorIds) {
                Author author = authorIdToAuthor.get(authorId);
                book.getAuthors().add(author);
            }
        }
    }

    private void fillBooksWithGenres(List<Book> books, Map<String, Set<Long>> bookIdToGenreIds, Map<Long, Genre> genreIdToGenre) {
        for (Book book : books) {
            Set<Long> genreIds = bookIdToGenreIds.get(book.getIsbn());
            for (Long genreId : genreIds) {
                Genre author = genreIdToGenre.get(genreId);
                book.getGenres().add(author);
            }
        }
    }


}
