package com.example.userservices.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Map;



@AllArgsConstructor
@Data
public class ResponeDTO {
    private int code;
    private Map<String,Object> data;
    private String message;
}
