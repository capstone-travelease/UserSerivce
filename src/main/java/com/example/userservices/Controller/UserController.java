package com.example.userservices.Controller;


import com.example.userservices.DTOs.RequestUpdateDTO;
import com.example.userservices.DTOs.ResponeDTO;
import com.example.userservices.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/getuser")
    public ResponeDTO getUserInformation(@RequestParam(value = "userid",required = true) Integer userId, HttpServletResponse response){
        var userDetail = userService.getUser(userId);
        if(userDetail.isEmpty()){
            response.setStatus(404);
            return  new ResponeDTO(
                    response.getStatus(),userDetail,"NOT_FOUND"
            );

        }
        return  new ResponeDTO(response.getStatus(),userDetail,"OK");
    }

    @PostMapping("/updateuser")
    public ResponeDTO updateUserInformation(@RequestBody @Valid RequestUpdateDTO request,HttpServletResponse response){
            var updateUser = userService.updateInformationUser(request);
            if (updateUser){
                return  new ResponeDTO(response.getStatus(), new HashMap<>(), "OK");
            }
            response.setStatus(404);
            return new ResponeDTO(response.getStatus(),new HashMap<>(),"NOT_FOUND");
    }
}
