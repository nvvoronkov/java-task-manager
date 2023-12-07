package org.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private String status;

    private String priority;

    @ManyToOne
    private User author;

    @ManyToMany
    private List<User> executor;

    @OneToMany
    private List<Comment> comment;
}
