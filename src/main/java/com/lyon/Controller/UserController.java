package com.lyon.Controller;

import com.lyon.Entity.User;
import com.lyon.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired  //spring依赖注入
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public Map<String, String> VerifyLogin(
            @RequestParam("id") long id,
            @RequestParam("password") String password,
            @RequestParam("identity") short identity
    ) {
        List<User> users = userRepository.findByIdAndPasswordAndIdentity(id, password, identity);
        Map<String, String> response = new HashMap<>();
        if (users.isEmpty())
            response.put("flag", "false");
        else {
            response.put("flag", "true");
            response.put("name", users.get(0).getName());
        }
        return response;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Map<String, String> VerifyLogin(
            @RequestBody User data
    ) {
        List<User> users = userRepository.findByIdAndPasswordAndIdentity(data.getId(), data.getPassword(), data.getIdentity());
        Map<String, String> response = new HashMap<>();
        if (users.isEmpty())
            response.put("flag", "false");
        else {
            response.put("flag", "true");
            response.put("name", users.get(0).getName());
            response.put("identity", Short.toString(users.get(0).getIdentity()));
        }
        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> addUser(@RequestBody User data) {
        Map<String, String> response = new HashMap<>();
        System.out.println(data.getId());
        System.out.println(userRepository.findByIdAndIdentity(data.getId(), data.getIdentity()));
        User pre = userRepository.findByIdAndIdentity(data.getId(), data.getIdentity());
        if (pre != null) {
            response.put("flag", "false");
        } else {
//        System.out.println(userRepository.save(data));
            response.put("flag", "true");
            userRepository.save(data);
        }
        return response;
    }
}