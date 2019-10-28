package com.lht.bService;


import com.lht.dModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginMangeService {


    @Autowired
    private UserService userService;

    public User login(String account,String password){
        User user=userService.getUserByAccountAndPassword(account, password);


        return  user;
    }
}


