package com.example.libraryservice;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    public void addBook() {

        BookRequest bookRequest = new BookRequest();

        bookRequest.setTitle("Margarita");
        bookRequest.setAuthor("Salam Aleikumov");
        bookRequest.setYear(Date.from(Instant.now()));

        Book book = new Book();

        book.setId(1L);
        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setYear(book.getYear());
        book.setFile("34567890.pdf");

        when(bookRepository.save(any())).thenReturn(book);

        String booki = "Add Book";

        String bookResponse = bookService.addBook(bookRequest);

        assertEquals(booki, bookResponse);
    }

    @Test
    public void deleteByIdBook() {

        String books = "Delete Book";

        String bookResponse = bookService.deleteBook(1);

        assertEquals(books, bookResponse);
    }
}