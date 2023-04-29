package com.example.libraryservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;



@ExtendWith(MockitoExtension.class)
class BookControllerTest {


    @Mock
    private BookServiceImpl bookService;

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookRequest bookRequest;

    @BeforeEach
    public void setUp() {

        bookRequest = new BookRequest();
    }

    @Test
    public void testAddBook() {

        Mockito.when(bookService.addBook(bookRequest)).thenReturn("success");

        ResponseEntity<String> response = bookController.addBook(bookRequest);

        assertEquals("success", response.getBody());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
}