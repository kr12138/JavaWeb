package com.lyon.Controller.AdminController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.User;
import com.lyon.Repository.UserRepository;
import static com.lyon.Security.logTime.now;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserAdminController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public HashMap<String, String> add(
            @RequestBody User data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("User/add " + JSON.toJSONString(data) + now());
        if (userRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        userRepository.save(data);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public HashMap<String, String> update(
            @RequestBody User data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("User/update " + JSON.toJSONString(data) + now());
        if (!userRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
//        userRepository.deleteById(data.getId());
        userRepository.save(data);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HashMap<String, String> delete(
            @PathVariable long id
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("User/delete " + id + now());
        if (!userRepository.existsById(id)) {
            response.put("flag", "false");
            return response;
        }
        userRepository.deleteById(id);
        response.put("flag", "true");
        return response;
    }
}