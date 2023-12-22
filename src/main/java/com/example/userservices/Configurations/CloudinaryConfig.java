package com.example.userservices.Configurations;


import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class CloudinaryConfig {


    @Bean
    public Cloudinary getCloudinary(){
        Map<String, Object> config = new HashMap<>();
        config.put("cloud_name", "df3vn23o6");
        config.put("api_key", "597879919897266");
        config.put("api_secret", "huqIqpjLYRZ3Ja06IjpKLWHAo1c");
        config.put("secure", true);
        return new Cloudinary(config);
    }
}
