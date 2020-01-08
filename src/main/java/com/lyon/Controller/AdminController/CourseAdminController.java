package com.lyon.Controller.AdminController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Course;
import com.lyon.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/course")
public class CourseAdminController {
    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HashMap<String, String> add(
            @RequestBody Course data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("course/add " + JSON.toJSONString(data) + now());
        if (courseRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        courseRepository.save(data);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public HashMap<String, String> update(
            @RequestBody Course data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("course/update " + JSON.toJSONString(data) + now());
        if (!courseRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        courseRepository.save(data);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public HashMap<String, String> delete(
            @RequestBody Course data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("course/delete " + JSON.toJSONString(data) + now());
        if (!courseRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        courseRepository.deleteById(data.getId());
        response.put("flag", "true");
        return response;
    }
}
