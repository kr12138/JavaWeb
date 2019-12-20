package com.lyon.Controller;


import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Dept;
import com.lyon.Repository.DeptRepository;
import static com.lyon.Security.logTime.now;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;


@RestController
@RequestMapping("/api/dept")
public class DeptController {
    @Autowired
    private DeptRepository deptRepository;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public HashMap<String, String> getAll() {
//        Sort sort = new Sort(Sort.Direction.ASC, "id");
        final List<Dept> list = deptRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        System.out.println("dept/getAll：" + JSON.toJSONString(list) + now());

        HashMap<String, String> response = new HashMap<>();
        response.put("flag", "true");
        response.put("depts", JSON.toJSONString(list));
        return response;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HashMap<String, String> add(
            @RequestBody Dept data
    ) {
        System.out.println("dept/add " + JSON.toJSONString(data) + now());
        HashMap<String, String> response = new HashMap<>();
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
        System.out.println("dept/update " + JSON.toJSONString(data) + now());
        HashMap<String, String> response = new HashMap<>();
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
//    public Page<Dept> getAll() {
//        Pageable pageable = PageRequest.of(1, 10000, Sort.Direction.ASC, "id");
//        return deptRepository.findAll(pageable);


}
