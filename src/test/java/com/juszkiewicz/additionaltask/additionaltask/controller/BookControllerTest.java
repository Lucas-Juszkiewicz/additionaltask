package com.juszkiewicz.additionaltask.additionaltask.controller;

import com.juszkiewicz.additionaltask.additionaltask.entity.Book;
import com.juszkiewicz.additionaltask.additionaltask.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        Book book = new Book();
        book.setTitle("Spring Boot Guide");
        book.setAuthor("John Doe");
        book.setPublicationYear(2020);
        bookRepository.deleteAll();
        bookRepository.save(book);
    }

    @Test
    void testGetAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testRateBook() throws Exception {
        Book book = bookRepository.findAll().get(0);

        mockMvc.perform(put("/api/books/" + book.getId() + "/rate")
                        .param("ratingPointsToAdd", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(4.0));

        mockMvc.perform(put("/api/books/" + book.getId() + "/rate")
                        .param("ratingPointsToAdd", "5"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rating").value(4.5));
    }
}