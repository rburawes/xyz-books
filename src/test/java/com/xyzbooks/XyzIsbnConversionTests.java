package com.xyzbooks;

import com.xyzbooks.util.BookUtils;
import org.apache.commons.validator.routines.ISBNValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class XyzIsbnConversionTests {
    private ISBNValidator isbnValidator;
    private static final String ISBN10 = "1591847818";
    private static final String ISBN13 = "9781591847816";

    @BeforeAll
    public void setup() {
        isbnValidator = new ISBNValidator();
    }

    @Test
    public void convertedIsbn10ToIsbn13IsValid() {
        assertTrue(isbnValidator.isValidISBN13(BookUtils.convertToISBN13V2(ISBN10)));
    }

    @Test
    public void convertedIsbn13ToIsbn10IsValid() {
        assertTrue(isbnValidator.isValidISBN10(BookUtils.convertToISBN10(ISBN13)));
    }

    @Test
    public void convertedIsbn13EqualToExpectedIsbn10() {
        assertEquals(BookUtils.convertToISBN10(ISBN13), ISBN10);
    }

    @Test
    public void convertedIsbn10EqualToExpectedIsbn13() {
        assertEquals(BookUtils.convertToISBN13(ISBN10), ISBN13);
    }

}
