package com.lyon.Controller.AdminController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Question;
import com.lyon.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/question")
public class QuestionAdminController {
    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value = "/", method = RequestMethod.PUT)
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

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HashMap<String, String> delete(
            @PathVariable long id
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("question/delete " + id + now());
        if (!questionRepository.existsById(id)) {
            response.put("flag", "false");
            return response;
        }
        questionRepository.deleteById(id);
        response.put("flag", "true");
        return response;
    }
}
