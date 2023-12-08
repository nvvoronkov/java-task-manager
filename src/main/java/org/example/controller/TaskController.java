package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.model.dto.*;
import org.example.service.TaskServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Api("Controller of tasks")
public class TaskController {

    private final TaskServiceImpl taskService;

    public TaskController(TaskServiceImpl taskService) {
        this.taskService = taskService;
    }

    @GetMapping("task/all")
    @ApiOperation("Get all tasks")
    public ResponseEntity<List<TaskResponse>> getAllTasks(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(taskService.getAllTasks(PageRequest.of(page, size)), HttpStatus.OK);
    }

    @GetMapping("task/{id}")
    @ApiOperation("Get task by id")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable int id) {
        return new ResponseEntity<>(taskService.getTaskResponse(id), HttpStatus.OK);
    }

    @GetMapping("task/{id}/comments")
    @ApiOperation("Get list of comments of task")
    public ResponseEntity<List<CommentResponse>> getCommentsFromTaskById(@PathVariable int id) {
        return new ResponseEntity<>(taskService.getTaskResponse(id).getComments(), HttpStatus.OK);
    }

    @GetMapping("task/{id}/executors")
    @ApiOperation("Get list of executors of task")
    public ResponseEntity<List<UserResponse>> getListOfExecutors(@PathVariable int id) {
        return new ResponseEntity<>(taskService.getTaskResponse(id).getExecutors(), HttpStatus.OK);
    }

    @GetMapping("task/{id}/author")
    @ApiOperation("Get author of task")
    public ResponseEntity<UserResponse> getAuthor(@PathVariable int id) {
        return new ResponseEntity<>(taskService.getTaskResponse(id).getAuthor(), HttpStatus.OK);
    }

    @GetMapping("user/{id}/backlog")
    @ApiOperation("Get list of tasks which user is authored")
    public ResponseEntity<List<TaskResponse>> getTasksByAuthor(@PathVariable int id) {
        return new ResponseEntity<>(taskService.getTasksByAuthor(id), HttpStatus.OK);
    }

    @GetMapping("user/{id}/tasks")
    @ApiOperation("Get list of tasks which user is assigned to")
    public ResponseEntity<List<TaskResponse>> getTasksByExecutor(@PathVariable int id) {
        return new ResponseEntity<>(taskService.getTasksByExecutor(id), HttpStatus.OK);
    }

    @PostMapping("task/create")
    @ApiOperation("Create new task")
    public ResponseEntity<TaskResponse> createTask(@RequestBody TaskRequest request) {
        return new ResponseEntity<>(taskService.createTask(request), HttpStatus.OK);
    }

    @PutMapping("task/{id}/updateStatus")
    @ApiOperation("Update task")
    public ResponseEntity<TaskResponse> updateStatus(@PathVariable int id, @RequestBody UpdateStatusRequest request) {
        return new ResponseEntity<>(taskService.updateStatus(id, request.getStatus()), HttpStatus.OK);
    }

    @PutMapping("task/{id}/addComment")
    @ApiOperation("Add comment to task")
    public ResponseEntity<TaskResponse> addComment(@PathVariable int id, @RequestBody AddCommentRequest request) {
        return new ResponseEntity<>(taskService.addComment(id, request), HttpStatus.OK);
    }

    @DeleteMapping("task/{id}/delete")
    @ApiOperation("Delete task by id")
    public ResponseEntity<TaskResponse> removeTask(@PathVariable int id) {
        return new ResponseEntity<>(taskService.removeTask(id), HttpStatus.OK);
    }

}
