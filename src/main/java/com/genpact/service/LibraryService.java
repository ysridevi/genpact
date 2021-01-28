package com.genpact.service;

import com.genpact.domain.BookData;

import java.util.Map;

public interface LibraryService {
    Map<String, Object> getAllBooks(Long libraryId);
}
