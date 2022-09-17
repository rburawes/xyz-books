package com.xyzbooks.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * 1. Should always have a name.
 * 2. Can have many books.
 */
@Entity
@Table(name = "publishers")
public class Publisher extends Persistable {
    @Column(nullable = false, unique = true)
    private String name;

    @JsonBackReference
    @OneToMany(mappedBy = "publisher")
    private Set<Book> books;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
