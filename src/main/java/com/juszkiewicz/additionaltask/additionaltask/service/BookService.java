package com.juszkiewicz.additionaltask.additionaltask.service;
import com.juszkiewicz.additionaltask.additionaltask.entity.Book;
import com.juszkiewicz.additionaltask.additionaltask.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public List<Book> filterBooks(String title, String author, Integer publicationYear, Double rating) {
        if (title != null) {
            return bookRepository.findByTitleContainingIgnoreCase(title);
        } else if (author != null) {
            return bookRepository.findByAuthorContainingIgnoreCase(author);
        } else if (publicationYear != null) {
            return bookRepository.findByPublicationYear(publicationYear);
        } else if (rating != null) {
            return bookRepository.findByRatingGreaterThanEqual(rating);
        } else {
            return bookRepository.findAll();
        }
    }

    public Book rateBook(Long id, int ratingPointsToAdd) {
        Optional<Book> bookOpt = bookRepository.findById(id);
        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            int ratingCount = book.getRatingCount() + 1;
            int ratingPoints = book.getRatingPoints() + ratingPointsToAdd;
            double rating = (double) ratingPoints / ratingCount;
            book.setRatingCount(ratingCount);
            book.setRatingPoints(ratingPoints);
            book.setRating(rating);
            return bookRepository.save(book);
        }
        return null;
    }
}