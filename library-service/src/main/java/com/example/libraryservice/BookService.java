package com.example.libraryservice;


public interface BookService {

    public BookResponse getAllBook();

    public BookResponseById getByIdBook(long id);

    public String addBook(BookRequest bookRequest);

    public String deleteBook(long id);
}
