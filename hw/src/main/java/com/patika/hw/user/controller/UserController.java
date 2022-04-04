package com.patika.hw.user.controller;

import com.patika.hw.user.model.User;
import com.patika.hw.user.service.UserService;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@Valid @NonNull @RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{id}")
    public User getUserById(@PathVariable("id") UUID id) {
        return userService.getUserById(id)
                .orElse(null);
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable("id") UUID id) {
        userService.deleteUser(id);
    }

    @PutMapping(path = "{id}")
    public void updateUser(@PathVariable("id") UUID id,
                           @Valid @NonNull @RequestBody User userToUpdate) {
        userService.updateUser(id, userToUpdate);
    }

    @PutMapping(path = "{id}/edit-balance/{balance}")
    public void updateUserBalance(@PathVariable("id") UUID id,
                                  @PathVariable("balance") Double newBalance) {
        userService.updateUserBalance(id, newBalance);
    }

    @PutMapping(path = "{sender-id}/send-money/{receiver-id}/{amount}")
    public void sendUserMoney(@NonNull @PathVariable("sender-id") UUID senderId,
                              @NonNull @PathVariable("receiver-id") UUID receiverId,
                              @NonNull @PathVariable("amount") Double amount) {
        userService.sendUserMoney(senderId, receiverId, amount);
    }

}
