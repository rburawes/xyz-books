package com.xyzbooks.dto;

import java.math.BigDecimal;

/**
 * Holds the book information that will be presented
 * to the client as a response.
 */
public class BookDto {

    private String title;

    private String isbn;

    private BigDecimal price;

    private String publicationYear;

    private String publisherName;

    private String edition;

    private String imageUrl;

    private String authors;

    public BookDto(String title, String isbn, BigDecimal price, String publicationYear, String publisherName, String edition, String imageUrl, String authors) {
        this.title = title;
        this.isbn = isbn;
        this.price = price;
        this.publicationYear = publicationYear;
        this.publisherName = publisherName;
        this.edition = edition;
        this.imageUrl = imageUrl;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public String getIsbn() {
        return isbn;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public String getEdition() {
        return edition;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAuthors() {
        return authors;
    }
}
