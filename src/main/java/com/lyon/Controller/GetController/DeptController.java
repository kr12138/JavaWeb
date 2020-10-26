package com.lyon.Controller.GetController;


import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Dept;
import com.lyon.Repository.DeptRepository;
import static com.lyon.Security.logTime.now;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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

    @RequestMapping(value = "s", method = RequestMethod.GET)
    public HashMap<String, String> getAll() {
//        Sort sort = new Sort(Sort.Direction.ASC, "id");
        HashMap<String, String> response = new HashMap<>();
        final List<Dept> list = deptRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        System.out.println("dept/getAll " + JSON.toJSONString(list) + now());

        response.put("flag", "true");
        response.put("depts", JSON.toJSONString(list));
        return response;
    }
//    public Page<Dept> getAll() {
//        Pageable pageable = PageRequest.of(1, 10000, Sort.Direction.ASC, "id");
//        return deptRepository.findAll(pageable);


}
