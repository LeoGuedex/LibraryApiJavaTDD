package com.leoguedex.libraryapi.service;

import com.leoguedex.libraryapi.entities.Book;
import com.leoguedex.libraryapi.repositories.BookRepository;
import com.leoguedex.libraryapi.services.BookService;
import com.leoguedex.libraryapi.services.impl.BookServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class BookServiceTest {

    BookService bookService;

    @MockBean
    BookRepository bookRepository;

    @BeforeEach
    public void setup() {
        this.bookService = new BookServiceImpl(bookRepository);
    }

    @Test
    @DisplayName("Deve salvar um livro")
    public void saveBookTest() {
        //Cenario
        Book book = Book.builder()
                .title("Titulo")
                .author("Autor")
                .isbn("123456")
                .build();

        Mockito.when(bookRepository.save(book)).thenReturn(Book.builder()
                .id(1L)
                .title("Titulo")
                .author("Autor")
                .isbn("123456")
                .build());

        //Execucao
        Book savedBook = bookService.save(book);

        //Validacoes
        Assertions.assertThat(savedBook.getId()).isNotNull();
        Assertions.assertThat(savedBook.getTitle()).isEqualTo(book.getTitle());
        Assertions.assertThat(savedBook.getAuthor()).isEqualTo(book.getAuthor());
        Assertions.assertThat(savedBook.getIsbn()).isEqualTo(book.getIsbn());

    }
}
