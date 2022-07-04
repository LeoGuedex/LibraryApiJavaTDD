package com.leoguedex.libraryapi.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.leoguedex.libraryapi.dtos.BookDto;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BookDto create() {

        BookDto bookDto = BookDto.builder()
                .id(1L)
                .title("Titulo Livro")
                .author("Autor")
                .isbn("123456789")
                .build();

        return bookDto;
    }

}
