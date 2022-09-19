package com.xyzbooks;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class XyzBooksApplicationTests {
    private static final String BOOK_API_ROOT = "http://localhost:8080/api/v1/books";

    @Test
    void contextLoads() {
    }
}
