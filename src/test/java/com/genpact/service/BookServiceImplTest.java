package com.genpact.service;

import com.genpact.domain.Book;
import com.genpact.domain.BookData;
import com.genpact.domain.Library;
import com.genpact.repository.BookRepository;
import com.genpact.repository.LibraryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(classes= {BookServiceImpl.class})
@ActiveProfiles("test")
public class BookServiceImplTest {
    @Autowired
    private BookService bookService;

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private LibraryRepository libraryRepository;

    @Test
    void createBookSuccess() {
        Library library = new Library();
        library.setLibraryId(1L);
        library.setName("libName1");
        Optional<Library> lib = Optional.of(library);
        when(libraryRepository.findById(any())).thenReturn(lib);

        BookData bookData = new BookData();
        bookData.setLibraryId(1L);
        bookData.setBookId(1L);
        bookData.setName("bookName1");
        Map<String, String> result = bookService.createBook(bookData);
        assertThat(result).isNotNull();
        assertThat(result.get("Status")).isEqualTo("Success");
        assertThat(result.get("Message")).isEqualTo("Book stored successfully..");
    }

    @Test
    void createBookFailure() {
        Optional<Library> lib = Optional.empty();
        when(libraryRepository.findById(any())).thenReturn(lib);

        BookData bookData = new BookData();
        bookData.setLibraryId(1L);
        bookData.setBookId(1L);
        bookData.setName("bookName1");
        Map<String, String> result = bookService.createBook(bookData);
        assertThat(result).isNotNull();
        assertThat(result.get("Status")).isEqualTo("Failure");
        assertThat(result.get("Message")).isEqualTo("Library doesn't exist");
    }

    @Test
    void updateBookSuccess() {
        Book book = new Book();
        book.setBookId(1L);
        book.setName("bookName1");
        Optional<Book> bookObj = Optional.of(book);
        when(bookRepository.findById(any())).thenReturn(bookObj);

        Library library = new Library();
        library.setLibraryId(1L);
        library.setName("libName1");
        Optional<Library> lib = Optional.of(library);
        when(libraryRepository.findById(any())).thenReturn(lib);

        BookData bookData = new BookData();
        bookData.setLibraryId(1L);
        bookData.setBookId(1L);
        bookData.setName("bookName100");
        Map<String, String> result = bookService.updateBook(bookData);
        assertThat(result).isNotNull();
        assertThat(result.get("Status")).isEqualTo("Success");
        assertThat(result.get("Message")).isEqualTo("Book updated successfully..");
    }

    @Test
    void updateBookFailure1() {
        Book book = new Book();
        book.setBookId(1L);
        book.setName("bookName1");
        Optional<Book> bookObj = Optional.of(book);
        when(bookRepository.findById(any())).thenReturn(bookObj);

        Optional<Library> lib = Optional.empty();
        when(libraryRepository.findById(any())).thenReturn(lib);

        BookData bookData = new BookData();
        bookData.setLibraryId(1L);
        bookData.setBookId(1L);
        bookData.setName("bookName1");
        Map<String, String> result = bookService.updateBook(bookData);
        assertThat(result).isNotNull();
        assertThat(result.get("Status")).isEqualTo("Failure");
        assertThat(result.get("Message")).isEqualTo("Library doesn't exist");
    }

    @Test
    void updateBookFailure2() {
        Optional<Book> bookObj = Optional.empty();
        when(bookRepository.findById(any())).thenReturn(bookObj);

        BookData bookData = new BookData();
        bookData.setLibraryId(1L);
        bookData.setBookId(1L);
        bookData.setName("bookName1");
        Map<String, String> result = bookService.updateBook(bookData);
        assertThat(result).isNotNull();
        assertThat(result.get("Status")).isEqualTo("Failure");
        assertThat(result.get("Message")).isEqualTo("Book doesn't exist");
    }

    @Test
    void getBookDetailsSuccess() {
        Book book = new Book();
        book.setBookId(1L);
        book.setName("bookName1");
        Library library = new Library();
        library.setLibraryId(1L);
        library.setName("libName1");
        book.setLibrary(library);
        Optional<Book> bookObj = Optional.of(book);
        when(bookRepository.findById(any())).thenReturn(bookObj);

        Map<String, Object> result = bookService.getBookDetails(1L);
        assertThat(result).isNotNull();
        assertThat(result.get("Status")).isEqualTo("Success");
        BookData bookData = (BookData) result.get("Message");
        assertThat(bookData.getName()).isEqualTo("bookName1");
    }

    @Test
    void getBookDetailsFailure() {
        Optional<Book> bookObj = Optional.empty();
        when(bookRepository.findById(any())).thenReturn(bookObj);

        Map<String, Object> result = bookService.getBookDetails(1L);
        assertThat(result).isNotNull();
        assertThat(result.get("Status")).isEqualTo("Success");
        assertThat(result.get("Message")).isEqualTo("Book doesn't exist");
    }
}
