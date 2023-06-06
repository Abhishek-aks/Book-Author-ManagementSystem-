package com.example.authorBookManagement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@org.springframework.stereotype.Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
    int countByAuthorRatingGreaterThan(double rating);
    List<Book> findLatestBookByAuhtor(Author author);

}
