package ru.otus.yakimov.hw5.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.yakimov.hw5.domain.Author;
import ru.otus.yakimov.hw5.service.AuthorService;

@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class AuthorCommands {

    private final AuthorService authorService;

    @ShellMethod(key = "show_author", value = "Prints author in JSON format")
    public void showAuthor(long id){
        Author author = authorService.findById(id);
        log.info(author.toString());
    }

    @ShellMethod(key = "add_author", value="Adds new author to dataBase")
    public void addAuthor(long id, String firstName, String lastName){
        Author author = new Author(id, lastName, firstName);
        authorService.add(author);
    }
}
