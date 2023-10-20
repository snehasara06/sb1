package com.kgisl.sb1.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import com.kgisl.sb1.entity.Users;
// import com.kgisl.sb1.repository.UsersRepository;
import com.kgisl.sb1.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController{

    @Autowired
    private UsersService usersService;
    // private UsersRepository usersRepository;

    @GetMapping
    public ResponseEntity<List<Users>> all() {
        return new ResponseEntity<>(usersService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> post(@RequestBody Users users, UriComponentsBuilder ucBuilder) {
        // usersService.save(users);
        // HttpHeaders headers = new HttpHeaders();
        // headers.setLocation(ucBuilder.path("/get/{userId}").buildAndExpand(users.getUserId()).toUri());
        return new ResponseEntity<>(usersService.save(users), HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getById(@PathVariable Long userId) {
        Optional<Users> optionalUser = usersService.find(userId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // @PutMapping("/put/{userId}")
    // public ResponseEntity<?> put(@PathVariable Long userId, @RequestBody Users
    // users) {
    // Optional<Users> optionalUser = usersRepository.findById(userId);
    // if (optionalUser.isPresent()) {
    // Users user = optionalUser.get();
    // return new ResponseEntity<>(usersRepository.save(user), HttpStatus.CREATED);
    // } else {
    // return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
    // }
    // users.setUserId(userId);
    // usersService.save(users);
    // return new ResponseEntity<>(users, HttpStatus.OK);

    // return new ResponseEntity<>(usersService.save(users), HttpStatus.OK);
    // }

    @PutMapping("/put/{userId}")
    public ResponseEntity<?> put(@PathVariable Long userId, @RequestBody Users users) {
        Optional<Users> optionalUser = usersService.find(userId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();

            // Update the entire user object with the new representation
            user.setUserName(users.getUserName());
            user.setUserEmail(users.getUserEmail());
            // Update other fields as needed

            // Save the updated user
            usersService.save(user);

            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
    }

    // keeping userName and userEmail field can be changed
    @PatchMapping("/patch/{userId}")
    public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody Users updatedUser) {
        Optional<Users> optionalUser = usersService.find(userId);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();

            // Perform partial update only if specific fields are present in the updatedUser
            // object
            if (updatedUser.getUserName() != null) {
                user.setUserName(updatedUser.getUserName());
            }
            if (updatedUser.getUserEmail() != null) {
                user.setUserEmail(updatedUser.getUserEmail());
            }
            // Update other fields as needed

            // Save the updated user
            usersService.save(user);

            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // keeping userEmail field unchanged
    // @PatchMapping("/patch/{userId}")
    // public ResponseEntity<Users> updateUser(@PathVariable Long userId, @RequestBody Users updatedUser) {
    //     Optional<Users> optionalUser = usersService.find(userId);
    //     if (optionalUser.isPresent()) {
    //         Users user = optionalUser.get();

            // Perform partial update only if specific fields are present in the updatedUser
            // object
            // if (updatedUser.getUserName() != null) {
            //     user.setUserName(updatedUser.getUserName());
            // }
            // Keep the userEmail field unchanged
            // user.setUserEmail(updatedUser.getUserEmail());

            // Update other fields as needed

            // Save the updated user
        //     usersService.save(user);

        //     return ResponseEntity.ok(user);
        // } else {
        //     return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // }
   // }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable Long userId) {
        // usersService.delete(userId);
        usersService.delete(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
