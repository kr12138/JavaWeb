package com.lyon.Controller.UserController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Answer;
import com.lyon.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/answer")
public class AnswerUserController {
    @Autowired
    private AnswerRepository answerRepository;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public HashMap<String, String> add(
            @RequestBody HashMap<String, String> data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("answer/new " + JSON.toJSONString(data) + now());
        long qid = Long.parseLong(data.get("qid"));
        long tid = Long.parseLong(data.get("tid"));
        String content = data.get("content");
        String img = data.get("img");
        Answer answer = new Answer();
        answer.setQid(qid);
        answer.setTid(tid);
        answer.setContent(content);
        answer.setImg(img);
        answer.setDate(now());
        answerRepository.save(answer);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/myDelete", method = RequestMethod.POST)
    public HashMap<String, String> myDelete(
            @RequestBody Answer data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("answer/myDelete " + JSON.toJSONString(data) + now());
        if (!answerRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        answerRepository.deleteById(data.getId());
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/myUpdate", method = RequestMethod.PUT)
    public HashMap<String, String> myUpdate(
            @RequestBody Answer data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("answer/myUpdate " + JSON.toJSONString(data) + now());
        if (!answerRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        Answer answer = answerRepository.findById(data.getId());
        answer.setContent(data.getContent());
        answerRepository.save(data);
        response.put("flag", "true");
        return response;
    }

}
