package com.example.userservices.Controller;


import com.example.userservices.DTOs.*;
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

    @PutMapping("/updateuser")
    public ResponeUpdateDTO updateUserInformation(@RequestParam(value = "userid",required = true) Integer userid, @RequestBody @Valid RequestUpdateDTO request, HttpServletResponse response){
            var updateUser = userService.updateInformationUser(userid,request);
            if (!updateUser){
                response.setStatus(404);
                return new ResponeUpdateDTO(response.getStatus(),"NOT_FOUND");
            }
          return new ResponeUpdateDTO(response.getStatus(),"OK");

    }

    @PatchMapping("/updateimage")
    public ResponeUpdateDTO updateUserImages(@RequestParam(value = "userid",required = true) Integer userid, @RequestBody @Valid RequestUpdateImageDTO request, HttpServletResponse response){
        boolean isCheckingSuccess = userService.updateImage(userid,request.getImage());
        if(isCheckingSuccess){
            return new ResponeUpdateDTO(response.getStatus(),"OK");
        }
        response.setStatus(404);
        return new ResponeUpdateDTO(response.getStatus(),"NOT_FOUND");
    }
}
