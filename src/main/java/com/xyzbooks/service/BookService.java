package com.xyzbooks.service;

import com.xyzbooks.dto.BookDto;
import com.xyzbooks.exception.InvalidRequestException;
import com.xyzbooks.exception.ResourceNotFoundException;
import com.xyzbooks.model.Book;
import com.xyzbooks.repository.BookRepository;
import com.xyzbooks.util.BookUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Used to service business logic related to the boo.
 * e.g. modifying the collection to be presented as plain text.
 */
@Service
public class BookService {

    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDto findBookByIsbn(String isbn) {
        Book book = bookRepository.findByIsbnInList(BookUtils.getIsbnVersions(isbn));
        if (null != book) {
            return BookUtils.convertToBookDto(book);
        }
        throw new ResourceNotFoundException("Unable to find books");
    }

    public List<BookDto> getByBookTitle(String title) {
        if (null == title || title.isEmpty()) {
            throw new InvalidRequestException("Invalid title");
        }
        List<Book> books = bookRepository.findByTitleLike(title);
        if (null != books && !books.isEmpty()) {
            return BookUtils.convertToBookDtos(books);
        }
        throw new ResourceNotFoundException("Unable to find books");
    }

    public List<BookDto> findAll() {
        return BookUtils.convertToBookDtos(bookRepository.findAll());
    }
}
