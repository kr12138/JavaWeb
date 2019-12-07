package com.lyon.Controller;

import com.lyon.Entity.User;
import com.lyon.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired  //spring依赖注入
    private UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String VerifyLogin(
            @RequestParam("id") long id,
            @RequestParam("password") String password,
            @RequestParam("identity") short identity
    ) {
        List<User> users = userRepository.findByIdAndPasswordAndIdentity(id, password, identity);
        return users.isEmpty() ? "false" : "true";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String VerifyLogin(
            @RequestBody User data
    ) {
        List<User> users = userRepository.findByIdAndPasswordAndIdentity(data.getId(), data.getPassword(), data.getIdentity());
        return users.isEmpty() ? "false" : "true";
    }

    @RequestMapping(value = "/add", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public User addUser(@RequestBody User data) {
        return userRepository.save(data);
    }
}