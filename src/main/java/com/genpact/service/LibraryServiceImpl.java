package com.genpact.service;

import com.genpact.domain.BookData;
import com.genpact.domain.Library;
import com.genpact.domain.LibraryData;
import com.genpact.repository.LibraryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LibraryServiceImpl implements LibraryService {

    private static final Logger logger = LoggerFactory.getLogger(LibraryServiceImpl.class);

    @Autowired
    LibraryRepository libraryRepository;

    @Override
    public Map<String, Object> getAllBooks(Long id) {
        Map<String, Object> result = new HashMap<>();

        try {
            Optional<Library> libraryObj = libraryRepository.findById(id);
            if(libraryObj.isPresent()) {
                Library library = libraryObj.get();
                LibraryData libData = new LibraryData();
                libData.setLibraryId(library.getLibraryId());
                libData.setName(library.getName());
                List<BookData> bookList = new ArrayList<>();
                if(library.getBooks().size() > 0) {
                    library.getBooks().forEach(book -> {
                        BookData bookData = new BookData();
                        bookData.setBookId(book.getBookId());
                        bookData.setLibraryId(library.getLibraryId());
                        bookData.setName(book.getName());
                        bookList.add(bookData);
                    });
                    libData.setBookData(bookList);
                }

                result.put("Status", "Success");
                result.put("Message", libData);
            } else {
                result.put("Status", "Success");
                result.put("Message", "Library doesn't exist");
                return result;
            }
        } catch(Exception e) {
            throw e;
        }
        return result;
    }
}
