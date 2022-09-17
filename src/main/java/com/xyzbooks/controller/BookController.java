package com.xyzbooks.controller;

import com.xyzbooks.dto.BookDto;
import com.xyzbooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/title/{title}")
    public List<BookDto> getByTitle(@PathVariable String title) {
        return bookService.getByBookTitle(title);
    }

    @GetMapping("/isbn/{isbn}")
    public BookDto getByIsbn(@PathVariable String isbn) {
        return bookService.findBookByIsbn(isbn);
    }

    @GetMapping()
    public List<BookDto> getAllBooks() {
        return bookService.findAll();
    }
}
