package com.lyon.Controller.GetController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.User;
import com.lyon.Repository.UserRepository;
import static com.lyon.Security.logTime.now;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

//    @RequestMapping(value = "/getALL", method = RequestMethod.GET)
//    public HashMap<String, String> getALL() {
//        HashMap<String, String> response = new HashMap<>();
//        final List<User> list = userRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
//        System.out.println("user/getALL " + JSON.toJSONString(list) + now());
//        response.put("flag", "true");
//        response.put("users", JSON.toJSONString(list));
//        return response;
//    }
    @RequestMapping(value = "/students", method = RequestMethod.GET)
    public HashMap<String, String> getAllStudents() {
        HashMap<String, String> response = new HashMap<>();
        final List<User> list = userRepository.findAllByIdentity((short)1);
        System.out.println("user/getAllStudents " + JSON.toJSONString(list) + now());
        response.put("flag", "true");
        response.put("slist", JSON.toJSONString(list));
        return response;
    }

    @RequestMapping(value = "/{page}", method = RequestMethod.GET)
    public HashMap<String, String> getAll(
            @PathVariable int page
    ) {
        HashMap<String, String> response = new HashMap<>();
        final Page<User> users = userRepository.findAll(
                PageRequest.of(page,5,
                        Sort.by(Sort.Direction.ASC, "id")
                )
        );
        System.out.println("user/getAll("+ page +") " + JSON.toJSONString(users) + now());
        response.put("flag", "true");
        response.put("totalPages", Integer.toString(users.getTotalPages()));
        response.put("users", JSON.toJSONString(users.getContent()));
        return response;
    }


}