package com.example.userservices.Controller;


import com.example.userservices.DTOs.*;
import com.example.userservices.Services.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;


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

    @PostMapping("/updateimage")
    public ResponeUpdateDTO updateUserImages(@RequestParam(value = "userid",required = true) Integer userid, @RequestParam(value = "image",required = true)  MultipartFile image, HttpServletResponse response){
        if( image.isEmpty() || !validateFileImg(image.getContentType()) ){
            response.setStatus(400);
            return new ResponeUpdateDTO(response.getStatus(),"BAD_REQUEST");
        }
        boolean isCheckingSuccess = userService.updateImage(userid,image);
        if(!isCheckingSuccess){
            response.setStatus(404);
            return new ResponeUpdateDTO(response.getStatus(),"NOT_FOUND");
        }
        return new ResponeUpdateDTO(response.getStatus(),"OK");

    }

    private boolean validateFileImg(String contentType){
        return contentType.equals("image/png")
                || contentType.equals("image/jpg")
                || contentType.equals("image/jpeg");
    }
}
