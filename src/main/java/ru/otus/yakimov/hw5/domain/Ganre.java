package ru.otus.yakimov.hw5.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class Ganre {
    private final long id;
    private final String name;
    private final String description;

    @Override
    public String toString() {
        return "Ganre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ganre ganre = (Ganre) o;
        return id == ganre.id &&
                name.equals(ganre.name) &&
                description.equals(ganre.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description);
    }
}
