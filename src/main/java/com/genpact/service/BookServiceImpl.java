package com.genpact.service;

import com.genpact.domain.Book;
import com.genpact.domain.BookData;
import com.genpact.domain.Library;
import com.genpact.repository.BookRepository;
import com.genpact.repository.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private static final Logger logger = LoggerFactory.getLogger(BookServiceImpl.class);

    @Autowired
    BookRepository bookRepository;

    @Autowired
    LibraryRepository libraryRepository;

    @Override
    public Map<String, String> createBook(BookData bookData) {
        Map<String, String> result = new HashMap<>();

        try {
            Optional<Library> libraryObj = libraryRepository.findById(bookData.getLibraryId());
            if(libraryObj.isPresent()) {
                Book book = new Book();
                book.setName(bookData.getName());
                book.setLibrary(libraryObj.get());
                bookRepository.save(book);
                result.put("Status", "Success");
                result.put("Message", "Book stored successfully..");
            } else {
                result.put("Status", "Failure");
                result.put("Message", "Library doesn't exist");
                return result;
            }
        } catch(Exception e) {
            throw e;
        }
        return result;
    }

    @Override
    public Map<String, String> updateBook(BookData bookData) {
        Map<String, String> result = new HashMap<>();

        try {
            Optional<Book> book = bookRepository.findById(bookData.getBookId());
            if(book.isPresent()) {
                Optional<Library> libraryObj = libraryRepository.findById(bookData.getLibraryId());
                if(libraryObj.isPresent()) {
                    Book updatedBook = book.get();
                    updatedBook.setName(bookData.getName());
                    updatedBook.setLibrary(libraryObj.get());
                    bookRepository.save(updatedBook);
                } else {
                    result.put("Status", "Failure");
                    result.put("Message", "Library doesn't exist");
                    return result;
                }
            } else {
                result.put("Status", "Failure");
                result.put("Message", "Book doesn't exist");
                return result;
            }

            result.put("Status", "Success");
            result.put("Message", "Book updated successfully..");
        } catch(Exception e) {
            throw e;
        }
        return result;
    }

    @Override
    public Map<String, Object> getBookDetails(Long id) {
        Map<String, Object> result = new HashMap<>();

        try {
            Optional<Book> bookObj = bookRepository.findById(id);
            if(bookObj.isPresent()) {
                Book book = bookObj.get();
                BookData bookData = new BookData();
                bookData.setBookId(book.getBookId());
                bookData.setName(book.getName());
                bookData.setLibraryId(book.getLibrary().getLibraryId());
                result.put("Status", "Success");
                result.put("Message", bookData);
            } else {
                result.put("Status", "Success");
                result.put("Message", "Book doesn't exist");
                return result;
            }
        } catch(Exception e) {
            throw e;
        }
        return result;
    }
}
