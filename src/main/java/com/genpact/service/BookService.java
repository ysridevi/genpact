package com.genpact.service;

import com.genpact.domain.BookData;

import java.util.Map;

public interface BookService {
    Map<String, String> createBook(BookData bookData);
    Map<String, String> updateBook(BookData bookData);
    Map<String, Object> getBookDetails(Long id);
}
