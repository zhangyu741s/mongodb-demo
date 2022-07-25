package com.example.mongodbdemo.controller;

import com.example.mongodbdemo.dto.UserDTO;
import com.example.mongodbdemo.server.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/getUser",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String getUser(String name){
        UserDTO user=userRepository.findUserByUserName(name);

        return "userName="+user.getUserName()+",pwd="+user.getPassWord();
    }

}
