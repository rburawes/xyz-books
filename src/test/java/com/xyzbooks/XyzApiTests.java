package com.xyzbooks;

import com.xyzbooks.util.BookUtils;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class XyzApiTests {
    private static final String BOOK_API_ROOT = "http://localhost:8080/api/v1/books";

    @Test
    public void whenFindByIsbn10IsOK() {
        Response response = RestAssured.get(BOOK_API_ROOT + "/isbn/1591847818");
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenFindByIsbn13IsOK() {
        Response response = RestAssured.get(BOOK_API_ROOT + "/isbn/9781591847816");
        assertEquals(HttpStatus.OK.value(), response.getStatusCode());
    }

    @Test
    public void whenDBIsbnIsISBN13AndSearchByIsbn10() {
        Response response = RestAssured.get(BOOK_API_ROOT + "/isbn/1591847818");
        JsonPath bookData = response.jsonPath();
        String isbn = bookData.get("isbn");
        assertTrue(isbn != null);
        assertTrue(!isbn.isEmpty());
        assertEquals(BookUtils.convertToISBN10(isbn), "1591847818");
    }

    @Test
    public void whenDBIsbnIsISBN10AndSearchByIsbn13() {
        Response response = RestAssured.get(BOOK_API_ROOT + "/isbn/9780315782143");
        JsonPath bookData = response.jsonPath();
        String isbn = bookData.get("isbn");
        assertTrue(isbn != null);
        assertTrue(!isbn.isEmpty());
        assertEquals(BookUtils.convertToISBN13V2(isbn), "9780315782143");
    }

    @Test
    public void whenFindByIsbn10IsNotFound() {
        Response response = RestAssured.get(BOOK_API_ROOT + "/isbn/1788162064");
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCode());
    }

    @Test
    public void whenFindByIsbn10IsBadRequest() {
        Response response = RestAssured.get(BOOK_API_ROOT + "/isbn/1788162065");
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode());
    }

}
