package com.genpact.service;

import com.genpact.domain.Book;
import com.genpact.domain.Library;
import com.genpact.domain.LibraryData;
import com.genpact.repository.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes= {LibraryServiceImpl.class})
@ActiveProfiles("test")
public class LibraryServiceImplTest {
    @Autowired
    private LibraryService libraryService;

    @MockBean
    private LibraryRepository libraryRepository;

    @Test
    void getAllBooksSuccess1() {
        Library library = new Library();
        library.setLibraryId(1L);
        library.setName("libName1");
        Book book1 = new Book();
        book1.setLibrary(library);
        book1.setName("bookName1");
        book1.setBookId(1L);

        Book book2 = new Book();
        book2.setLibrary(library);
        book2.setName("bookName2");
        book2.setBookId(2L);

        Set<Book> bookList = new HashSet<>();
        bookList.add(book1);
        bookList.add(book2);

        library.setBooks(bookList);
        Optional<Library> lib = Optional.of(library);
        when(libraryRepository.findById(any())).thenReturn(lib);

        Map<String, Object> result = libraryService.getAllBooks(1L);
        assertThat(result).isNotNull();
        assertThat(result.get("Status")).isEqualTo("Success");
        LibraryData libData = (LibraryData) result.get("Message");
        assertThat(libData.getLibraryId()).isEqualTo(1L);
    }

    @Test
    void getAllBooksSuccess2() {
        Optional<Library> lib = Optional.empty();
        when(libraryRepository.findById(any())).thenReturn(lib);

        Map<String, Object> result = libraryService.getAllBooks(2L);
        assertThat(result).isNotNull();
        assertThat(result.get("Status")).isEqualTo("Success");
        assertThat(result.get("Message")).isEqualTo("Library doesn't exist");
    }
}
