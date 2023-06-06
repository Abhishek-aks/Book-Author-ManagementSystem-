package com.example.authorBookManagement;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Publish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int yearOfPublishing;

    @ManyToOne
    @JoinColumn(name = "book_")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "author_")
    private Author author;
}
