package com.juszkiewicz.additionaltask.additionaltask.repository;

import com.juszkiewicz.additionaltask.additionaltask.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByPublicationYear(int publicationYear);
    List<Book> findByRatingGreaterThanEqual(double rating);
}
