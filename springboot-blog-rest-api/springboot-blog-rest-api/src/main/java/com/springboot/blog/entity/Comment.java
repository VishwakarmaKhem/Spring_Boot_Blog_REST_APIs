package com.springboot.blog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")

public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //here we will be ignoring @Column annotation, as if we don't use it
    // to specify the columns names then JPA will consider the column names same
    // as the field name

    private String name;
    private String email;
    private String body;

    //creating one-to-many relationship between post and comment
    @ManyToOne(fetch = FetchType.LAZY) // FetchType.LAZY tells the hibernate to fetch only the related entities from the DB when we use relations
    @JoinColumn(name = "post_id", nullable = false)  //to specify foreign key, we use @JoinColumn

    private Post post;
}
