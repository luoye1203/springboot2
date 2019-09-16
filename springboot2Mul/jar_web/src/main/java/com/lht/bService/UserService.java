package com.lht.bService;


import com.lht.cDao.UserDao;
import com.lht.dModel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public User getUserByAccountAndPassword(String account,String password){

        return this.userDao.getUserByAccountAndPassword(account, password);
    }

}
