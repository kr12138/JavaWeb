package com.lyon.Controller.AdminController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Answer;
import com.lyon.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/answer")
public class AnswerAdminController {
    @Autowired
    private AnswerRepository answerRepository;

    @RequestMapping(value = "s", method = RequestMethod.GET)
    public HashMap<String, String> getAll() {
        HashMap<String, String> response = new HashMap<>();
        final List<Answer> list = answerRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        System.out.println("answer/getAll " + JSON.toJSONString(list) + now());
        response.put("flag", "true");
        response.put("alist", JSON.toJSONString(list));
        return response;
    }

    @RequestMapping(value = "/", method = RequestMethod.PUT)
    public HashMap<String, String> update(
            @RequestBody Answer data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("answer/update " + JSON.toJSONString(data) + now());
        if (!answerRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        answerRepository.save(data);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HashMap<String, String> delete(
            @PathVariable long id
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("answer/delete " + id + now());
        if (!answerRepository.existsById(id)) {
            response.put("flag", "false");
            return response;
        }
        answerRepository.deleteById(id);
        response.put("flag", "true");
        return response;
    }
}
