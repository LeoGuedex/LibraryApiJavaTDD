package com.leoguedex.libraryapi.repositories;

import com.leoguedex.libraryapi.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

}
