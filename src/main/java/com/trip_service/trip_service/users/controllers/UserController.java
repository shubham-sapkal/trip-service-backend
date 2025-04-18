package com.trip_service.trip_service.users.controllers;

import com.trip_service.trip_service.utils.DTO.SendResponse;
import com.trip_service.trip_service.users.DTO.RequestBodyDTO;
import com.trip_service.trip_service.users.models.Users;
import com.trip_service.trip_service.users.services.UserServices;
import com.trip_service.trip_service.utils.exceptions.GeneratedApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

            return new SendResponse<>(200, "User Created Successfully!", "", createdUser);

        }
        catch (GeneratedApiException apiException){
            return new SendResponse<>(apiException.getStatusCode(), apiException.getMessage(), apiException.getErrorMsg(), null);
        }
        catch (Exception e) {
            return new SendResponse<>(500, e.getMessage(),"", null);
        }
    }

    @PostMapping("/login")
    public SendResponse<String> loginUser(@RequestBody RequestBodyDTO.GetLoginUserRequestBody requestBody ) {

        try {

            String response = userServices.loginUser(requestBody.getUsername(), requestBody.getPassword());

            if(response != null )  {
                return new SendResponse<>(200, "Login Successful!", "", response);
            }

            return  new SendResponse<>(204, "Login Failed!!", "", null);

        }
        catch (GeneratedApiException apiException){
            return new SendResponse<>(apiException.getStatusCode(), apiException.getMessage(), apiException.getErrorMsg(), null);
        }
        catch (Exception e) {
            return new SendResponse<>(500, e.getMessage(), "", null);
        }
    }

    @PostMapping("/list")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SendResponse<List<Users>> getAllUsers() {
        return new SendResponse<>(200, "Data Retrived Successfully!", "", userServices.getAllUser());
    }

    @PostMapping("/get-user-info")
    public SendResponse<Optional<Users>> getUserById(@RequestBody RequestBodyDTO.GetUserByIdRequestBody requestBody) {

        try {
            Optional<Users> foundUser = userServices.getUserById(requestBody.getUsername());

            return new SendResponse<>(200, "User Details Found!!", "", foundUser);

        }
        catch (GeneratedApiException apiException){
            return new SendResponse<>(apiException.getStatusCode(), apiException.getMessage(), apiException.getErrorMsg(), null);
        }
        catch (Exception e) {
            return new SendResponse<>(500, e.getMessage(), "", null);
        }

    }

    @PostMapping("/manipulate-user-role")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SendResponse<List<String>> manipulateRole( @RequestBody RequestBodyDTO.RoleRequestBody requestBody ) {

        try {

            // TODO: Sanities changeType from requestBody


            return new SendResponse<>(
                    200,
                    "Role Manipulated Successfully!!",
                    "",
                    userServices.manipulateRole( requestBody.getChangeType(), requestBody.getUsername(), requestBody.getRole())
            );

        }
        catch (GeneratedApiException apiException){
            return new SendResponse<>(apiException.getStatusCode(), apiException.getMessage(), apiException.getErrorMsg(), null);
        }
        catch (Exception e) {
            return new SendResponse<>(500, "Internal Server Error!!", e.getMessage(), null);
        }

    }

}
