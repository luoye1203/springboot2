package com.lht.cDao;

import com.lht.dModel.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    public User getUserByAccountAndPassword(@Param("account") String account, @Param("password") String password);
}
