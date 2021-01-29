package com.genpact.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LibraryData {
    @NotNull
    private Long libraryId;
    private List<BookData> bookData;
    private String name;
}
