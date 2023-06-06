package com.example.authorBookManagement;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
public class Service {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PublishRepository publishRepository;
    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }


    public int countBookspublished(Author author, int year) {
        return (int) author.getPublishes().stream().filter(publish -> publish.getYearOfPublishing() == year).count();
    }

    public Author findById(int authorId) {
        return authorRepository.findById(authorId);
    }

    public int getTotalBookByAuthorbyX(double rating) {
        return bookRepository.countByAuthorRatingGreaterThan(rating);
    }

    public List<Book> getLatestBookbyAuthors() {
        List<Book> latestBooks =new ArrayList<>();
        List<Author> authors = authorRepository.findAll(); // gett all author

        for (Author author : authors) {
            List<Book> authorsBook = bookRepository.findLatestBookByAuhtor(author);
            if (!authorsBook.isEmpty()) { // if Empty - no book
                latestBooks.add(authorsBook.get(0));
            }
        }
        return latestBooks;
    }

    public List<Author> findAuthorsWithMaxPages() {
        List<Author> authors = authorRepository.findAll();
        int maxPages = authors.stream()
                .flatMap(author -> author.getPublishes().stream())
                .mapToInt(publish -> publish.getBook().getPages())
                .max()
                .orElse(0);
        return authors.stream()
                .filter(author -> author.getPublishes().stream()
                        .mapToInt(publish -> publish.getBook().getPages())
                        .sum() == maxPages)
                .collect(Collectors.toList());
    }

}
