package com.example.userservices.Services;

import com.cloudinary.Cloudinary;
import com.example.userservices.DTOs.RequestUpdateDTO;
import com.example.userservices.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.management.RuntimeErrorException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;



@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final Cloudinary cloudinary;
    private static final int MAX_LENGTH_URL = 48;
    public Map<String,Object> getUser(Integer idUser){
         return userRepository.getUser(idUser);
    }

    public boolean updateInformationUser(Integer userid,RequestUpdateDTO request) {
        var isExistingUser = userRepository.checkUser(userid);
//        Date date = parseDate(request.getBirthday());
        if(isExistingUser == null){
            return false;
        }
        userRepository.updateUser(userid,request.getFullname(), request.getBirthday(),request.getPhone(),request.isGender());
        return true;
    }

    public boolean updateImage(Integer userid, MultipartFile image){
        var isExistingUser = userRepository.checkUser(userid);
        if (isExistingUser == null){
           return false;
        }
        try {
            Map<String,String> params = new HashMap<>();
            params.put("folder","CAPSTONES/Users");
            var data = cloudinary.uploader().upload(image.getBytes(),params);
            String url = (String) data.get("url");
            System.err.println(url);
            String urlUpdate = url.substring(MAX_LENGTH_URL);
            userRepository.updateImage(userid,urlUpdate);
            return true;
        }catch (Exception err){
            return false;
        }
    }

}
