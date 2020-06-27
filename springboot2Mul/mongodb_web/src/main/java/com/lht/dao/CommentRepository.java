package com.lht.dao;

import com.lht.dModel.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
//评论的持久层接口
public interface CommentRepository extends MongoRepository<Comment,String> {
}