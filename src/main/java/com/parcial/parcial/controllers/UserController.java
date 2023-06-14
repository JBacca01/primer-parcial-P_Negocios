package com.parcial.parcial.controllers;
import com.parcial.parcial.models.User;
import com.parcial.parcial.services.UserService;
import com.parcial.parcial.utils.ApiResponse;
import com.parcial.parcial.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;
    private ApiResponse apiResponse;

    Map data = new HashMap<>();

    @GetMapping(value = "/{id}")
    public ResponseEntity findUserById(@PathVariable Long id){



        try {
            apiResponse = new ApiResponse(Constants.REGISTER_FOUND,userService.getUser(id));
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        }catch(Exception e){
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND,"");
            return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
        }

    }
    @PostMapping (value = "")
    public ResponseEntity saveUser(@RequestBody User user){
        Boolean userResp = userService.createUser(user);

        if (userResp == true){
            apiResponse = new ApiResponse(Constants.REGISTER_CREATED,"");
            return new ResponseEntity<>(apiResponse,HttpStatus.CREATED);
        }
        apiResponse = new ApiResponse(Constants.REGISTER_BAD,user);
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }
    // TAREA
    @GetMapping(value = "")
    public ResponseEntity allUsers() {
        try {
            apiResponse = new ApiResponse(Constants.REGISTER_LIST, userService.allUsers());
            return new ResponseEntity(apiResponse, HttpStatus.OK);
        } catch (Exception e) {
            apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND, "");
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
    }
        @PutMapping(value = "/{id}")
        public ResponseEntity updateUser(@PathVariable Long id, @RequestBody User user) {
            Boolean userDB = userService.updateUser(id, user);
            try {
                if (userDB == null) {
                    apiResponse = new ApiResponse(Constants.REGISTER_NOT_FOUND, "");

                    return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
                }
                apiResponse = new ApiResponse(Constants.REGISTER_UPDATED, userService.getUser(id));
                return new ResponseEntity(apiResponse, HttpStatus.ACCEPTED);
            } catch (Exception e) {
                apiResponse = new ApiResponse(Constants.REGISTER_BAD, user);
                return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);
            }
        }
    }
