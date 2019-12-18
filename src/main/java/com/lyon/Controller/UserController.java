package com.lyon.Controller;

import com.lyon.Entity.User;
import com.lyon.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
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
    public Map<String, String> VerifyLogin(
            @RequestBody User data
    ) {
        List<User> users = userRepository.findByIdAndPasswordAndIdentity(data.getId(), data.getPassword(), data.getIdentity());
        Map<String, String> response = new HashMap<>();
        if (users.isEmpty())
            response.put("flag", "false");
        else {
            System.out.println(users.get(0).getName() + " login " + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
            response.put("flag", "true");
            response.put("name", users.get(0).getName());
            response.put("identity", Short.toString(users.get(0).getIdentity()));
            if (users.get(0).getIdentity() == 0) {
                response.put("token", "adminTOKEN");
            }
        }
        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> addUser(
            @RequestBody User data
    ) {
        Map<String, String> response = new HashMap<>();
//        System.out.println(userRepository.findByIdAndIdentity(data.getId(), data.getIdentity()));
        User pre = userRepository.findByIdAndIdentity(data.getId(), data.getIdentity());
        if (pre != null) {
            response.put("flag", "false");
        }
        else {
            System.out.println(data.getId() + " registering " + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
            response.put("flag", "true");
            userRepository.save(data);
        }
        return response;
    }
    @RequestMapping(value = "/changePassword", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> changeUser(
            @RequestBody HashMap<String, String> body
    ) {
        long id = Long.parseLong(body.get("id"));
        String oldPassword = body.get("oldPassword");
        String newPassword = body.get("newPassword");
        Map<String, String> response = new HashMap<>();

        User now = userRepository.findById(id);
        if (now == null || !now.getPassword().equals(oldPassword) ) {
            response.put("flag", "false");
        } else {
            System.out.println(id + " changing password " + new SimpleDateFormat("yy-MM-dd HH:mm:ss").format(new Date()));
//            userRepository.deleteById(id);//似乎会在主键冲突时自动将save改成update
            now.setPassword(newPassword);
            userRepository.save(now);
            response.put("flag", "true");
        }
        return response;
    }
}