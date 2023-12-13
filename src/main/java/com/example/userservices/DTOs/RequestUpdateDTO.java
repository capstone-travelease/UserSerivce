package com.example.userservices.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
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
    private Integer iduser;

    @NotNull
    @NotBlank
   private String fullname;

    @NotBlank
    @NotNull
    private String birthday;

    @NotNull
    private Integer phone;
    private String avatar;

    @NotNull
    private boolean gender;
}
