package com.genpact.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "library")
@Getter
@Setter
public class Library {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libraryId;
    private String name;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Book> books = new HashSet<>();
}
