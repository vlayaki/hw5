package ru.otus.yakimov.hw5.service.impl;

import org.jline.reader.LineReader;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import ru.otus.yakimov.hw5.service.UserService;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final LineReader reader;

    public UserServiceImpl(@Lazy LineReader reader) {
        this.reader = reader;
    }

    @Override
    public Set<Long> getSetOfIdsFromUser(String question) {
        Set<Long> res = new HashSet<>();
        String id;
        while (!(id = askUser(question)).isEmpty()) {
            res.add(Long.valueOf(id));
        }
        return res;
    }

    private String askUser(String question) {
        return this.reader.readLine("\n" + question + " > ");
    }
}
