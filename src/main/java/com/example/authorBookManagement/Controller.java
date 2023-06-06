package com.example.authorBookManagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ABP")
public class Controller {

    @Autowired
    Service service;


    // 1. POST API - Add Book name, author name, no of pages
    @PostMapping("/addBook")
    public Book addBook(@RequestBody Book book) {
        return service.addBook(book);
    }



    // 2. POST API - Add Author author name, age, gender, rating
    @PostMapping("/addAuthor")
    public Author addAuthor(@RequestBody Author author) {
        return service.addAuthor(author);
    }



    // 3. Find the total number of books published by author A in year Y.
    @GetMapping("/{authorId}/year/{year}")
    public int countBookspublished( @PathVariable int authorId, @PathVariable int year){
        Author author  = service.findById(authorId);
        if (author != null) {
            return service.countBookspublished(author, year);
        }
        return 0;   // Handle author not found error
    }



    // 4. GET API– Find the total number of books written by authors who have a rating > X
    @GetMapping
    public ResponseEntity<Integer> getTotalBookByAuthorbyX(@RequestParam double rating){
        int totalBook = service.getTotalBookByAuthorbyX(rating);
        return ResponseEntity.ok(totalBook);
    }



    // 5. GET API - Find the list of the most recent book written by every author.
    public List<Book> getLatestBookbyAuthors(){
        return service.getLatestBookbyAuthors();
    }


    // 6. GET API -Find the list of name of authors and their age who have written maximum no. of pages during lifetime
    @GetMapping("/max-pages")
    public List<Author> findAuthorsWithMaxPages() {
        return service.findAuthorsWithMaxPages();
    }


}
