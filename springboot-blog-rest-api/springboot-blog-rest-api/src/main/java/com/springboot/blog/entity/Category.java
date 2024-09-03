package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor //to create no argument constructor
@AllArgsConstructor //to create a parameterized constructor
@Entity //to make this class a jpa entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true) //mappedBy attribute tells hibernate that we are using one-to-many bidirectional mapping and dont create the additional join tables
    private List<Post> posts;
}
