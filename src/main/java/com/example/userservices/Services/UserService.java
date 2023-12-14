package com.example.userservices.Services;

import com.example.userservices.DTOs.RequestUpdateDTO;
import com.example.userservices.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.rmi.server.ExportException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;



@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    public Map<String,Object> getUser(Integer idUser){
         return userRepository.getUser(idUser);
    }

    public boolean updateInformationUser(Integer userid,RequestUpdateDTO request) {
        var isExistingUser = userRepository.checkUser(userid);
        Date date = parseDate(request.getBirthday());
        if(isExistingUser == null){
            return false;
        }
        userRepository.updateUser(userid,request.getFullname(), date,request.getPhone(),request.isGender(),request.getAvatar());
        return true;
    }
    private Date parseDate(String date) {
        String expectedFormate = "MM/dd/yyyy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(expectedFormate);
        try {
            return dateFormat.parse(date);
        } catch (ParseException err) {
            return null;
        }
    }
}
