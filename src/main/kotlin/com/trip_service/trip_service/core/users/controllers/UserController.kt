package com.trip_service.trip_service.core.users.controllers


import com.trip_service.trip_service.core.users.models.Users
import com.trip_service.trip_service.core.users.dto.RequestBodyDTO
import com.trip_service.trip_service.core.users.dto.UsersResponseBody
import com.trip_service.trip_service.core.users.services.UserService
import com.trip_service.trip_service.helpers.dto.GenerateResponse
import com.trip_service.trip_service.helpers.errors.GenerateApiException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    private lateinit var userService: UserService;

    @GetMapping
    fun default(): String {
        return "User Service is Up and Running"
    }

    @PostMapping("/create")
    fun createUser(@RequestBody user: Users):  GenerateResponse<String> {

        try {
            return GenerateResponse( 200,userService.createUser(user), "")
        }
        catch(exception: GenerateApiException) {
            return GenerateResponse(
                exception.status,
                "",
                exception.errorMessage
            )
        }

    }

    @PostMapping("/login")
    fun login(@RequestBody loginUserReqBody: RequestBodyDTO.LoginUser): GenerateResponse<String>{

        try {

            return GenerateResponse(
                200,
                "Login Successful!",
                "",
                userService.login(loginUserReqBody.username, loginUserReqBody.password)
            )

        }
        catch (exception: GenerateApiException) {
            return GenerateResponse(
                exception.status,
                "",
                exception.errorMessage
            )
        }

    }

    @GetMapping("/user-info/{username}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    fun getUserInfo(@PathVariable username: String): GenerateResponse<UsersResponseBody.UserInfo> {

        try {

            val foundUser: Users = userService.getUserById(username);

            return GenerateResponse(
                200, "User Details Found!", "", foundUser.getFilteredData()
            )

        }
        catch (exception: GenerateApiException) {
            return GenerateResponse(
                exception.status,
                "",
                exception.errorMessage
            )
        }

    }

    @GetMapping("/all")
    fun getAllUser(): GenerateResponse<List<UsersResponseBody.UserInfo>> {
        return GenerateResponse(
            200,
            "Data Retrived Successfully!",
            "",
            userService.getAllUsers()
        )
    }

    @PostMapping("/manipulate-user-role")
    fun manipulateUserRole(@RequestBody manipulateUserRolesReqBody: RequestBodyDTO.ManipulateRole): GenerateResponse<Set<String>> {
        try {
            return GenerateResponse(200, "Role Manipulated Successfully!", "", userService.manipulateRole(
                manipulateUserRolesReqBody.username,
                manipulateUserRolesReqBody.role,
                manipulateUserRolesReqBody.changeType
            ))
        }
        catch (exception: GenerateApiException) {
            return GenerateResponse(
                exception.status,
                "",
                exception.errorMessage
            )
        }
    }

}