package com.example.libraryservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/books")
public class BookController {

    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<BookResponse> getAllBook() {

        return ResponseEntity.status(200).body(bookService.getAllBook());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseById> getById(@PathVariable long id) {

        return ResponseEntity.status(200).body(bookService.getByIdBook(id));
    }

    @PostMapping
    public ResponseEntity<String> addBook(BookRequest bookRequest) {

        return ResponseEntity.status(201).body(bookService.addBook(bookRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(long id) {

        return ResponseEntity.status(202).body(bookService.deleteBook(id));
    }
}
