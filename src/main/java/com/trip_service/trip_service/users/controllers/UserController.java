package com.trip_service.trip_service.users.controllers;

import com.trip_service.trip_service.utils.DTO.SendResponse;
import com.trip_service.trip_service.users.DTO.RequestBodyDTO;
import com.trip_service.trip_service.users.models.Users;
import com.trip_service.trip_service.users.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserServices userServices;

    @PostMapping("/create")
    public SendResponse<Users> createUser(@RequestBody Users user) {

        try {
            Users createdUser = userServices.createUser(user);

            return new SendResponse<>(200, "User Created Successfully!", createdUser);

        }
        catch (Exception e) {
            return new SendResponse<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/login")
    public SendResponse<String> loginUser(@RequestBody RequestBodyDTO.GetLoginUserRequestBody requestBody ) {

        try {

            String response = userServices.loginUser(requestBody.getUsername(), requestBody.getPassword());

            if(response != null )  {
                return new SendResponse<>(200, "Login Successful!", response);
            }

            return  new SendResponse<>(204, "Login Failed!!", null);

        }
        catch (Exception e) {
            return new SendResponse<>(500, e.getMessage(), null);
        }
    }

    @PostMapping("/list")
    public SendResponse<List<Users>> getAllUsers() {
        return new SendResponse<>(200, "Data Retrived Successfully!", userServices.getAllUser());
    }

    @PostMapping("/get-user-info")
    public SendResponse<Optional<Users>> getUserById(@RequestBody RequestBodyDTO.GetUserByIdRequestBody requestBody) {

        try {
            Optional<Users> foundUser = userServices.getUserById(requestBody.getUsername());

            if(foundUser.isPresent())
                return new SendResponse<>(200, "User Details Found!!", foundUser);

            return new SendResponse<>(204, "User Not Found!!", foundUser);

        }
        catch (Exception e) {
            return new SendResponse<>(500, e.getMessage(), null);
        }

    }

    @PostMapping("/manipulate-user-role")
    public SendResponse<List<String>> manipulateRole( @RequestBody RequestBodyDTO.RoleRequestBody requestBody ) {

        try {

            // TODO: Sanities changeType from requestBody


            return new SendResponse<>(
                    200,
                    "Role Manipulated Successfully!!",
                    userServices.manipulateRole( requestBody.getChangeType(), requestBody.getUsername(), requestBody.getRole())
            );

        }
        catch (Exception e) {
            return new SendResponse<>(500, e.getMessage(), null);
        }

    }

}
