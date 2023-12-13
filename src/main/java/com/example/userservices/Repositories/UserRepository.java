package com.example.userservices.Repositories;

import com.example.userservices.Entities.UserEnity;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Map;

@Repository
public interface UserRepository extends JpaRepository<UserEnity,Integer> {

    @Query(value = "Select user_id from users u where u.user_id = ?1", nativeQuery = true)
    Integer checkUser (Integer idUser);
    @Query(value = "SELECT gender, phone_number, dob, avatar, email, full_name FROM users u where u.user_id = ?1",nativeQuery = true)
    Map<String,Object> getUser(Integer idUser);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE public.users\n" +
            "\tSET gender= ?5, phone_number=?4, dob=?3, avatar=?6, full_name=?2" +
            "\tWHERE users.user_id=?1",nativeQuery = true)
    void updateUser(Integer iduser, String fullname, Date birthday, Integer phone, boolean gender, String avatar);
}
