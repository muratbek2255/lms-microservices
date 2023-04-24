package com.example.libraryservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "SELECT id, title, author, year, file FROM books WHERE id=?1", nativeQuery = true)
    Book getById(@Param("id") long id);

}
