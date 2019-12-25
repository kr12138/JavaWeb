package com.lyon.Controller;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Course;
import com.lyon.Repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/course")
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public HashMap<String, String> getAll() {
        HashMap<String, String> response = new HashMap<>();
        final List<Course> list = courseRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        System.out.println("course/getAll " + JSON.toJSONString(list) + now());
        response.put("flag", "true");
        response.put("courses", JSON.toJSONString(list));
        return response;
    }

    @RequestMapping(value = "/get/{cid}", method = RequestMethod.GET)
    public HashMap<String, String> getById(
            @PathVariable long cid
    ) {
        HashMap<String, String> response = new HashMap<>();
        Course course = courseRepository.findById(cid);
        System.out.println("course/get("+ cid + ") " + JSON.toJSONString(course) + now());
        if (course == null) {
            response.put("flag", "false");
            return response;
        }
        response.put("flag", "true");
        response.put("course", JSON.toJSONString(course));
        return response;
    }

    @RequestMapping(value = "/getByDept/{dept}", method = RequestMethod.GET)
    public HashMap<String, String> getByDept(
            @PathVariable String dept
    ) {
        HashMap<String, String> response = new HashMap<>();
        List<Course> clist = courseRepository.findByDept(dept);
        System.out.println("course/get("+ dept + ") " + JSON.toJSONString(clist) + now());
        if (clist == null) {
            response.put("flag", "false");
            return response;
        }
        response.put("flag", "true");
        response.put("clist", JSON.toJSONString(clist));
        return response;
    }

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
