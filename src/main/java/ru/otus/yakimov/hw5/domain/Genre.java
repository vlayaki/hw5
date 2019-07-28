package ru.otus.yakimov.hw5.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class Genre {
    private final long id;
    private final String name;
    private final String description;

    @Override
    public String toString() {
        return "genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return id == genre.id &&
                name.equals(genre.name) &&
                description.equals(genre.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
