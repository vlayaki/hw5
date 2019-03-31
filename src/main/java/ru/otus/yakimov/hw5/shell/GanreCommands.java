package ru.otus.yakimov.hw5.shell;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.yakimov.hw5.domain.Ganre;
import ru.otus.yakimov.hw5.service.GanreService;

@ShellComponent
@RequiredArgsConstructor
@Slf4j
public class GanreCommands {

    private final GanreService ganreService;

    @ShellMethod(key="show_ganre", value = "Prints ganre in JSON format")
    public void showGanre(long id){
        Ganre ganre = ganreService.findById(id);
        log.info(ganre.toString());
    }

    @ShellMethod(key="add_ganre", value = "Adds new ganre to database")
    public void addGanre(long id, String name, String description){
        Ganre ganre = new Ganre(id, name, description);
        ganreService.add(ganre);
    }
}
