package com.lyon.Controller.AdminController;


import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Dept;
import com.lyon.Repository.DeptRepository;
import static com.lyon.Security.logTime.now;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
@RequestMapping("/api/dept")
public class DeptAdminController {
    @Autowired
    private DeptRepository deptRepository;


    @RequestMapping(value = "/add", method = RequestMethod.POST)
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

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public HashMap<String, String> delete(
            @RequestBody Dept data
    ) {
        System.out.println("dept/delete " + JSON.toJSONString(data) + now());
        HashMap<String, String> response = new HashMap<>();
        if (!deptRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        deptRepository.deleteById(data.getId());
        response.put("flag", "true");
        return response;
    }

}
