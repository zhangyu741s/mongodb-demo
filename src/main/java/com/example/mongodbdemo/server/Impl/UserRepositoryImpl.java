package com.example.mongodbdemo.server.Impl;

import com.example.mongodbdemo.dto.UserDTO;
import com.example.mongodbdemo.server.UserRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
public class UserRepositoryImpl implements UserRepository {

    //注入MongoTemplate
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void saveUser(UserDTO user) {
        mongoTemplate.save(user);
    }

    @Override
    public UserDTO findUserByUserName(String userName) {
        Query query=new Query(Criteria.where("userName").is(userName));
        UserDTO user = mongoTemplate.findOne(query , UserDTO.class);
        return user;
    }

    @Override
    public long updateUser(UserDTO user) {
        Query query=new Query(Criteria.where("id").is(user.getId()));
        Update update= new Update().set("userName", user.getUserName()).set("passWord"
                , user.getPassWord());
        //更新查询返回结果集的第⼀条
        UpdateResult result =mongoTemplate.updateFirst(query,update,UserDTO.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,UserEntity.class);
        if(result!=null)
            return result.getMatchedCount();
        else
            return 0;
    }

    @Override
    public void deleteUserById(Long id) {
        Query query=new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query,UserDTO.class);
    }
}
