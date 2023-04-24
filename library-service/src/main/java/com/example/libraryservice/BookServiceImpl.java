package com.example.libraryservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookResponse getAllBook() {
        List<Book> book = (List<Book>) bookRepository.findAll();

        BookResponse bookResponse = new BookResponse();

        bookResponse.setId(book.get(0).getId());
        bookResponse.setTitle(book.get(0).getTitle());
        bookResponse.setAuthor(book.get(0).getAuthor());
        bookResponse.setYear(book.get(0).getYear());

        return bookResponse;

    }

    @Override
    public BookResponseById getByIdBook(long id) {

        Book book = bookRepository.getById(id);

        BookResponseById bookResponse = new BookResponseById();

        bookResponse.setId(book.getId());
        bookResponse.setTitle(book.getTitle());
        bookResponse.setAuthor(book.getAuthor());
        bookResponse.setFile(book.getFile());


        return bookResponse;
    }

    @Override
    public String addBook(BookRequest bookRequest) {

        Book book = new Book();

        book.setTitle(bookRequest.getTitle());
        book.setAuthor(bookRequest.getAuthor());
        book.setYear(bookRequest.getYear());

        bookRepository.save(book);

        return "Add Book";
    }

    @Override
    public String deleteBook(long id) {

        bookRepository.deleteById(id);

        return "Delete Book";
    }
}
