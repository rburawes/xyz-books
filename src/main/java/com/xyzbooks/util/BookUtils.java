package com.xyzbooks.util;

import com.xyzbooks.dto.BookDto;
import com.xyzbooks.exception.InvalidRequestException;
import com.xyzbooks.model.Author;
import com.xyzbooks.model.Book;
import org.apache.commons.validator.routines.ISBNValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Utilities for {@link Book}.
 * For validation, conversion and mapping.
 */
public class BookUtils {

    private static final ISBNValidator isbnValidator = new ISBNValidator();
    private static final String ISBN_13_PREFIX = "978";

    public static String convertAuthorsToString(Set<Author> authors) {
        String authorNames = "";
        if (authors != null && authors.size() > 0) {
            String[] names = new String[authors.size()];
            int ctr = 0;
            for (Author author : authors) {
                if (author.getMiddleName() != null && author.getMiddleName().length() > 0) {
                    names[ctr] = String.format("%s %s %s", author.getFirstName(), author.getMiddleName(), author.getLastName());
                } else {
                    names[ctr] = String.format("%s %s", author.getFirstName(), author.getLastName());
                }
                ctr++;
            }
            authorNames = String.join(", ", names);
        }
        return authorNames;
    }

    public static List<BookDto> convertToBookDtos(List<Book> books) {
        List<BookDto> bookResources = new ArrayList<>(books.size());
        if (null != books && !books.isEmpty()) {
            for (Book book : books) {
                bookResources.add(convertToBookDto(book));
            }
        }
        return bookResources;
    }

    public static BookDto convertToBookDto(Book book) {
        return new BookDto(
                book.getTitle(),
                book.getIsbn(),
                book.getPrice(),
                book.getPublicationYear(),
                book.getPublisher().getName(),
                convertToOrdinal(book.getEdition()),
                book.getImageUrl(),
                BookUtils.convertAuthorsToString(book.getAuthors()));
    }

    public static String convertToOrdinal(String value) {
        char last = value.charAt(value.length() - 1);
        switch (last) {
            case '1':
                return value + "st";
            case '2':
                return value + "nd";
            case '3':
                return value + "rd";
            default:
                return value + "th";
        }
    }

    /**
     * Collect the ISBN versions to use as query parameter when searching against the DB.
     * The ISBN in the DB might be in ISBN10 or ISBN13 formats.
     * <p>
     * from apache commons-validator.
     *
     * @param isbn
     * @return
     */
    public static String[] getIsbnVersions(String isbn) {
        isbn = isbn.replace("-", "").trim();
        String[] isbnFormats = new String[2];
        if (isbnValidator.isValidISBN10(isbn)) {
            isbnFormats[0] = convertToISBN13V2(isbn);
        } else if (isbnValidator.isValidISBN13(isbn)) {
            isbnFormats[0] = convertToISBN10(isbn);
        } else {
            throw new InvalidRequestException("Invalid ISBN");
        }
        isbnFormats[1] = isbn;
        return isbnFormats;
    }

    /**
     * Used the apache 'commons-validator' for converting ISBN10 to ISBN13 since the implementation is existing.
     * But we can opt to write our own just like {@link #convertToISBN10(String)} based from the formula here
     * https://isbn-information.com/convert-isbn-10-to-isbn-13.html
     *
     * @param isbn10
     * @return
     */
    public static String convertToISBN13(String isbn10) {
        return isbnValidator.convertToISBN13(isbn10);
    }

    /**
     * apache 'commons-validator' has no function to convert ISBN13 to ISBN10.
     * The formula used for the conversion of ISBN-13 to ISBN-10 is from here:
     * https://isbn-information.com/the-10-digit-isbn.html
     *
     * @param isbn13
     * @return the ISBN10 equivalent value of the given ISBN13
     */
    public static String convertToISBN10(String isbn13) {
        int count = 10;
        int sum = 0;
        String trimmedISBN13 = isbn13.trim().substring(3, isbn13.length() - 1);
        for (int a = 0; a < trimmedISBN13.length(); a++) {
            int digit = Integer.parseInt(trimmedISBN13.charAt(a) + "");
            sum = sum + (digit * count);
            count--;
        }
        return String.format("%s%s", trimmedISBN13, 11 - (sum % 11));
    }

    /**
     * ISBN10 to ISBN13
     * Implementation based on the info here: https://isbn-information.com/convert-isbn-10-to-isbn-13.html
     *
     * @param isbn10
     * @return
     */
    public static String convertToISBN13V2(String isbn10) {
        String isbn = ISBN_13_PREFIX + isbn10.substring(0, isbn10.length() - 1);
        int sum = 0;
        for (int i = 0; i < isbn.length(); i++) {
            int digit = Integer.parseInt(isbn.charAt(i) + "");
            if ((i + 1) % 2 == 0) {
                sum = sum + (3 * digit);
            } else {
                sum = sum + (1 * digit);
            }
        }
        return String.format("%s%s", isbn, (10 - (sum % 10)));
    }

}
