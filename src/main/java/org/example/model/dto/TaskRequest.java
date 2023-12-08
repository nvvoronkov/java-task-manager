package org.example.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {
    private int id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private int author;
    private List<Integer> executors;
}
