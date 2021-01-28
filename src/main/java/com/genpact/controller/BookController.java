package com.genpact.controller;

import com.genpact.domain.BookData;
import com.genpact.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
public class BookController {
    private static final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    private BookService bookService;

    @PostMapping(value="/createBook")
    public ResponseEntity<Map<String, String>> createBook(@RequestBody @Valid BookData bookData) {
        Map<String, String> result = new HashMap<>();
        try {
            result = bookService.createBook(bookData);
        } catch(Exception e) {
            result.put("Status", "Failure");
            result.put("Message", e.getMessage());
            if(e.getCause() != null) {
                result.put("Detailed Message", e.getCause().getMessage());
            }
        }
        return ResponseEntity.ok(result);
    }

    @PostMapping(value="/updateBook")
    public ResponseEntity<Map<String, String>> updateBook(@RequestBody @Valid BookData bookData) {
        Map<String, String> result = new HashMap<>();
        try {
            result = bookService.updateBook(bookData);
        } catch(Exception e) {
            result.put("Status", "Failure");
            result.put("Message", e.getMessage());
            if(e.getCause() != null) {
                result.put("Detailed Message", e.getCause().getMessage());
            }
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getBookDetails/{bookId}")
    public ResponseEntity<Map<String, Object>> getBookDetails(@PathVariable("bookId") Long bookId) {
        Map<String, Object> result = new HashMap<>();
        try {
            result = bookService.getBookDetails(bookId);
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
