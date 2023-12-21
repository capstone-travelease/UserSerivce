package com.example.userservices.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;


@AllArgsConstructor
@Data
public class RequestUpdateDTO {

    @NotNull
    private boolean gender;

    @NotNull
    @NotBlank
   private String fullname;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull
    @NotBlank
    private String phone;

}
