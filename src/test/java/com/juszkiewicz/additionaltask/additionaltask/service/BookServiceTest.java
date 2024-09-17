package com.juszkiewicz.additionaltask.additionaltask.service;

import com.juszkiewicz.additionaltask.additionaltask.entity.Book;
import com.juszkiewicz.additionaltask.additionaltask.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    public BookServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRateBook() {
        Book book = new Book();
        book.setId(1L);
        book.setRating(4.0);
        book.setRatingCount(1);
        book.setRatingPoints(4);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);

        Book ratedBook = bookService.rateBook(1L, 5);

        assertEquals(4.5, ratedBook.getRating());
        assertEquals(2, ratedBook.getRatingCount());
    }
}
