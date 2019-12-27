package com.lyon.Controller;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.User;
import com.lyon.Repository.UserRepository;
import static com.lyon.Security.logTime.now;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
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
    @RequestMapping(value = "/getAllStudents", method = RequestMethod.GET)
    public HashMap<String, String> getAllStudents() {
        HashMap<String, String> response = new HashMap<>();
        final List<User> list = userRepository.findAllByIdentity((short)1);
        System.out.println("user/getAllStudents " + JSON.toJSONString(list) + now());
        response.put("flag", "true");
        response.put("slist", JSON.toJSONString(list));
        return response;
    }

    @RequestMapping(value = "/getAll/{page}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/add", method = RequestMethod.POST)
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

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public HashMap<String, String> delete(
            @RequestBody User data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("User/delete " + JSON.toJSONString(data) + now());
        if (!userRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        userRepository.deleteById(data.getId());
        response.put("flag", "true");
        return response;
    }
}