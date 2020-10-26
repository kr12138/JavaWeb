package com.lyon.Controller.AdminController;


import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Dept;
import com.lyon.Repository.DeptRepository;
import static com.lyon.Security.logTime.now;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;


@RestController
@RequestMapping("/api/dept")
public class DeptAdminController {
    @Autowired
    private DeptRepository deptRepository;


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public HashMap<String, String> add(
            @RequestBody Dept data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("dept/add " + JSON.toJSONString(data) + now());
        if (deptRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        deptRepository.save(data);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public HashMap<String, String> update(
            @RequestBody Dept data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("dept/update " + JSON.toJSONString(data) + now());
        if (!deptRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        deptRepository.save(data);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HashMap<String, String> delete(
            @PathVariable long id
    ) {
        System.out.println("dept/delete " + id + now());
        HashMap<String, String> response = new HashMap<>();
        if (!deptRepository.existsById(id)) {
            response.put("flag", "false");
            return response;
        }
        deptRepository.deleteById(id);
        response.put("flag", "true");
        return response;
    }

}
