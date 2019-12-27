package com.lyon.Controller;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Question;
import com.lyon.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/question")
public class QuestionAdminController {
    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public HashMap<String, String> update(
            @RequestBody Question data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("question/update " + JSON.toJSONString(data) + now());
        if (!questionRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        questionRepository.save(data);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public HashMap<String, String> delete(
            @RequestBody Question data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("question/delete " + JSON.toJSONString(data) + now());
        if (!questionRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        questionRepository.deleteById(data.getId());
        response.put("flag", "true");
        return response;
    }
}
