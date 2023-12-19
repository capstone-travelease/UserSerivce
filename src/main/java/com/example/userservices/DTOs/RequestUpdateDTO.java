package com.example.userservices.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@AllArgsConstructor
@Data
public class RequestUpdateDTO {

    @NotNull
    @NotBlank
   private String fullname;


    private String birthday;

    @NotNull
    @NotBlank
    private String phone;


    private boolean gender;
}
