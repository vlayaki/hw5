package ru.otus.yakimov.hw5.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.yakimov.hw5.domain.Genre;
import ru.otus.yakimov.hw5.service.GenreService;

@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class GenreCommands {

    private final GenreService genreService;

    @ShellMethod(key="show_genre", value = "Prints genre in JSON format")
    public void showGenre(long id){
        Genre genre = genreService.findById(id);
        log.info(genre.toString());
    }

    @ShellMethod(key="add_genre", value = "Adds new genre to database")
    public void addGenre(long id, String name, String description){
        Genre genre = new Genre(id, name, description);
        genreService.add(genre);
    }
}
