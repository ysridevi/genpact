package com.genpact.controller;

import com.genpact.domain.BookData;
import com.genpact.service.BookService;
import com.genpact.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class LibraryController {
    private static final Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Autowired
    private LibraryService libraryService;

    @GetMapping("/getBooks/{libraryId}")
    public ResponseEntity<Map<String, Object>> getAllBooks(@PathVariable("libraryId") Long libraryId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result = libraryService.getAllBooks(libraryId);
        } catch(Exception e) {
            result.put("Status", "Failure");
            result.put("Message", e.getMessage());
            if(e.getCause() != null) {
                result.put("Detailed Message", e.getCause().getMessage());
            }
        }
        return ResponseEntity.ok(result);
    }
}
