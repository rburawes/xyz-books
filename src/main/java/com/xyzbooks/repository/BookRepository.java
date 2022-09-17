package com.xyzbooks.repository;

import com.xyzbooks.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("FROM Book b WHERE b.isbn IN :isbns")
    Book findByIsbnInList(@Param("isbns") List<String> isbns);

    @Query("FROM Book b WHERE b.title LIKE %:bookTitle%")
    List<Book> findByTitleLike(@Param("bookTitle") String title);
}
