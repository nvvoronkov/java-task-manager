package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private int id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private UserResponse author;
    private List<UserResponse> executors = new ArrayList<>();
    private List<CommentResponse> comments = new ArrayList<>();
}
