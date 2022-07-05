package com.leoguedex.libraryapi.controllers;

import com.leoguedex.libraryapi.entities.Book;
import com.leoguedex.libraryapi.services.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.leoguedex.libraryapi.dtos.BookDto;

@WebMvcTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
public class BookControllerTest {

    static String BOOK_API = "/api/books";

    @Autowired
    MockMvc mockMvc;

    @MockBean
    BookService bookService;

    @Test
    @DisplayName("Deve criar um livro com sucesso")
    public void createBookTest() throws Exception {
        // Cenário
        BookDto bookDto = BookDto.builder()
                .title("Titulo")
                .author("Autor")
                .isbn("123456")
                .build();

        String json = new ObjectMapper().writeValueAsString(bookDto);

        Book saveBook = Book.builder()
                .id(1L)
                .title("Titulo")
                .author("Autor")
                .isbn("123456")
                .build();

        BDDMockito.given(bookService.save(Mockito.any(Book.class))).willReturn(saveBook);



        // Execução
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(BOOK_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        // Validação
        mockMvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("title").value(bookDto.getTitle()))
                .andExpect(MockMvcResultMatchers.jsonPath("author").value(bookDto.getAuthor()))
                .andExpect(MockMvcResultMatchers.jsonPath("isbn").value(bookDto.getIsbn()));

    }

    @Test
    @DisplayName("Deve lançar um erro de validação quando não houver dados suficiente para a criação do livro")
    public void createInvalidBookTest() {

    }
}
