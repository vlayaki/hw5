package ru.otus.yakimov.hw5.domain;

import lombok.Data;

import java.util.Objects;

@Data
public class Author {
    private final long id;
    private final String lastName;
    private final String firstName;

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Author author = (Author) o;
        return id == author.id &&
                lastName.equals(author.lastName) &&
                firstName.equals(author.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastName, firstName);
    }
}
