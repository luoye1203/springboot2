package com.lht.test;

import com.lht.bService.CommentService;
import com.lht.dModel.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    private Logger logger= LoggerFactory.getLogger(this.getClass());


    @Autowired
    private CommentService commentService;

    @Test
    public void testSaveComment(){
        Comment comment=new Comment();
        comment.setArticleid("100000");
        comment.setContent("测试添加的数据");
        comment.setCreatedatetime(LocalDateTime.now());
        comment.setUserid("1003");
        comment.setNickname("凯撒大帝");
        comment.setState("1");
        comment.setLikenum(0);
        comment.setReplynum(0);
        commentService.saveComment(comment);
    }

    @Test
    public void testFindAll(){
        List<Comment> list = commentService.findCommentList();
        logger.info(list.toString());
    }
}
