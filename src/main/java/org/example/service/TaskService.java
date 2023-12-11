package org.example.service;

import org.example.model.dto.AddCommentRequest;
import org.example.model.dto.TaskRequest;
import org.example.model.dto.TaskResponse;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAllTasks(PageRequest pageRequest);

    TaskResponse createTask(TaskRequest request);

    TaskResponse updateStatus(int id, String status);

    TaskResponse addComment(int taskId, AddCommentRequest request);

    TaskResponse getTaskResponse(int id);

    List<TaskResponse> getTasksByAuthor(int id);

    List<TaskResponse> getTasksByExecutor(int id);

    TaskResponse removeTask(int id);
}
