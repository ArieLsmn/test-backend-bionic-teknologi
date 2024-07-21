package com.example.demo.service;

import com.example.demo.entity.Borrow;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Return;
import com.example.demo.entity.User;
import com.example.demo.repository.BorrowRepo;
import com.example.demo.repository.ReturnRepo;
import com.example.demo.repository.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepo userRepo;
    ReturnRepo retRepo;

    @Transactional
    public boolean addUser(User usr){

        userRepo.save(usr);
        return true;
    }

    public boolean deleteUser(int id){

        retRepo.deleteById(id);
        return true;
    }

    public boolean editUser(int id, User usr){
        Optional<User> optional = userRepo.findById(id);

        if (optional.isEmpty()) {
            return false;
        }
        else {
            try {
                userRepo.save(usr);
            }catch (Exception e){
                return false;
            }

            return true;
        }

    }

}
