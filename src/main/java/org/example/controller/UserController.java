package org.example.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.example.model.User;
import org.example.model.dto.UserResponse;
import org.example.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@Api("Controller for managing user info")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping("{id}")
    @ApiOperation("Get user by id")
    public ResponseEntity<UserResponse> getUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUserResponse(id), HttpStatus.OK);
    }

    @PutMapping("{id}/update")
    @ApiOperation("Create new user")
    public ResponseEntity<UserResponse> updateUser(@RequestBody User user, @PathVariable("id") int id) {
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.OK);
    }

}
