package com.lht;


import com.lht.bService.UserService;
import com.lht.dModel.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ProStartEnter implements CommandLineRunner {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Override
    public void run(String... args) throws Exception {
//        while (true) {

            logger.info("启动啦");
            Thread.sleep(3000);
            User user = userService.getUserByAccountAndPassword("lht", "77ed66e2fa056ebe82d54b84ae24c451");
            logger.info(user.toString());
//        }
    }
}
