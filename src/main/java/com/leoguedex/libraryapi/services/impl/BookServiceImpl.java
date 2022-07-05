package com.leoguedex.libraryapi.services.impl;

import com.leoguedex.libraryapi.entities.Book;
import com.leoguedex.libraryapi.repositories.BookRepository;
import com.leoguedex.libraryapi.services.BookService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
