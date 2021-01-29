package com.genpact.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BookData {
    @NotNull
    private Long bookId;
    @NotNull
    private Long libraryId;
    private String name;
}
