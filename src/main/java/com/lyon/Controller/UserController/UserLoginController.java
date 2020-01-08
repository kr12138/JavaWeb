package com.lyon.Controller.UserController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.User;
import com.lyon.Repository.UserRepository;
import static com.lyon.Security.logTime.now;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/user")
public class UserLoginController {
    @Autowired
    private UserRepository userRepository;

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public Map<String, String> VerifyLogin(
//            @RequestParam("id") long id,
//            @RequestParam("password") String password,
//            @RequestParam("identity") short identity
//    ) {
//        List<User> users = userRepository.findByIdAndPasswordAndIdentity(id, password, identity);
//        Map<String, String> response = new HashMap<>();
//        if (users.isEmpty())
//            response.put("flag", "false");
//        else {
//            response.put("flag", "true");
//            response.put("name", users.get(0).getName());
//        }
//        return response;
//    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> VerifyLogin(
            @RequestBody User data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("user/login " + JSON.toJSONString(data) + now());
        User user = userRepository.findByIdAndPasswordAndIdentity(data.getId(), data.getPassword(), data.getIdentity());
        if (user == null)
            response.put("flag", "false");
        else {
            System.out.println(user.getName() + " login " + now());
            response.put("flag", "true");
            response.put("name", user.getName());
            response.put("identity", Short.toString(user.getIdentity()));
            if (user.getIdentity() == 0) {
                response.put("token", "admin" + data.getId());
            } else if (user.getIdentity() == 1) {
                response.put("token", "stude" + data.getId());
            } else if (user.getIdentity() == 2) {
                response.put("token", "teach" + data.getId());
            }
        }
        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> addUser(
            @RequestBody User data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("user/register " + JSON.toJSONString(data) + now());
        User pre = userRepository.findByIdAndIdentity(data.getId(), data.getIdentity());
        if (pre != null) {
            response.put("flag", "false");
        }
        else {
            System.out.println(data.getId() + " registering " + now());
            response.put("flag", "true");
            userRepository.save(data);
        }
        return response;
    }

    @RequestMapping(value = "/changePassword", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HashMap<String, String> changeUser(
            @RequestBody HashMap<String, String> data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("user/changePassword " + JSON.toJSONString(data) + now());
        long id = Long.parseLong(data.get("id"));
        String oldPassword = data.get("oldPassword");
        String newPassword = data.get("newPassword");

        User newData = userRepository.findById(id);
        if (newData == null || !newData.getPassword().equals(oldPassword) ) {
            response.put("flag", "false");
        } else {
            System.out.println(id + " changing password " + now());
            newData.setPassword(newPassword);
//            userRepository.deleteById(id);//似乎会在主键冲突时自动将save改成update
            userRepository.save(newData);
            response.put("flag", "true");
        }
        return response;
    }

}