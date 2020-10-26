package com.lyon.Controller.AdminController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Teacher;
import com.lyon.Repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/teacher")
public class TeacherAdminController {
    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public HashMap<String, String> add(
            @RequestBody Teacher data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("teacher/add " + JSON.toJSONString(data) + now());
        if (teacherRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        teacherRepository.save(data);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public HashMap<String, String> update(
            @RequestBody Teacher data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("teacher/update " + JSON.toJSONString(data) + now());
        if (!teacherRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
//        teacherRepository.deleteById(data.getId());
        teacherRepository.save(data);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HashMap<String, String> delete(
            @PathVariable long id
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("teacher/delete " + id + now());
        if (!teacherRepository.existsById(id)) {
            response.put("flag", "false");
            return response;
        }
        teacherRepository.deleteById(id);
        response.put("flag", "true");
        return response;
    }
}
