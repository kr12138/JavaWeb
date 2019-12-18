package com.lyon.Controller;



import com.lyon.Repository.DeptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dept")
public class DeptController {
    @Autowired
    private DeptRepository deptRepository;


}
