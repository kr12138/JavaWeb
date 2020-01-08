package com.lyon.Controller.GetController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Teacher;
import com.lyon.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public HashMap<String, String> getAll() {
        HashMap<String, String> response = new HashMap<>();
        final List<Teacher> list = teacherRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        System.out.println("teacher/getAll " + JSON.toJSONString(list) + now());
        response.put("flag", "true");
        response.put("teachers", JSON.toJSONString(list));
        return response;
    }

    @RequestMapping(value = "/get/{uid}", method = RequestMethod.GET)
    public HashMap<String, String> getById(
            @PathVariable long uid
    ) {
        HashMap<String, String> response = new HashMap<>();
        Teacher teacher = teacherRepository.findById(uid);
        System.out.println("teacher/get("+ uid + ") " + JSON.toJSONString(teacher) + now());
        if (teacher == null) {
            response.put("flag", "false");
            return response;
        }
        response.put("flag", "true");
        response.put("teacher", JSON.toJSONString(teacher));
        return response;
    }

    @RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET)
    public HashMap<String, String> getByName(
            @PathVariable String name
    ) {
        HashMap<String, String> response = new HashMap<>();
        Teacher teacher = teacherRepository.findByName(name);
        System.out.println("teacher/getByName("+ name + ") " + JSON.toJSONString(teacher) + now());
        if (teacher == null) {
            response.put("flag", "false");
            return response;
        }
        response.put("flag", "true");
        response.put("teacher", JSON.toJSONString(teacher));
        return response;
    }
}
