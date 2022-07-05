package com.leoguedex.libraryapi.controllers;

import com.leoguedex.libraryapi.dtos.BookDto;
import com.leoguedex.libraryapi.entities.Book;
import com.leoguedex.libraryapi.services.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    ModelMapper modelMapper;

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto create(@RequestBody BookDto bookDto) {
        Book toBook = modelMapper.map(bookDto, Book.class);
        Book bookSaved = bookService.save(toBook);
        BookDto toBookDto = modelMapper.map(bookSaved, BookDto.class);
        return toBookDto;
    }

}