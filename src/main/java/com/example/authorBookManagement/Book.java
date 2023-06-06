package com.example.authorBookManagement;


import jakarta.persistence.*;
import lombok.*;

import javax.annotation.processing.Generated;
import java.util.List;




@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private  String name;
    private  int pages;
    private String author_name;
    private double rating;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Publish> publishes;


}


