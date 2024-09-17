package com.juszkiewicz.additionaltask.additionaltask.controller;

import com.juszkiewicz.additionaltask.additionaltask.entity.Book;
import com.juszkiewicz.additionaltask.additionaltask.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) Integer publicationYear,
            @RequestParam(required = false) Double rating) {
        return bookService.filterBooks(title, author, publicationYear, rating);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{id}/rate")
    public ResponseEntity<Book> rateBook(@PathVariable Long id, @RequestParam int ratingPointsToAdd) {
        if (ratingPointsToAdd < 1 || ratingPointsToAdd > 5) {
            return ResponseEntity.badRequest().build();
        }
        Book ratedBook = bookService.rateBook(id, ratingPointsToAdd);
        return ratedBook != null ? ResponseEntity.ok(ratedBook) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
