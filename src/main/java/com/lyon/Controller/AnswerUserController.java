package com.lyon.Controller;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Answer;
import com.lyon.Repository.AnswerRepository;
import com.lyon.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/answer")
public class AnswerUserController {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

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

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public HashMap<String, String> getById(
            @PathVariable long id
    ) {
        HashMap<String, String> response = new HashMap<>();
        Answer answer = answerRepository.findById(id);
        answer.setImg("");
        System.out.println("answer/get/ "+ id + " " + JSON.toJSONString(answer) + now());
        if (answer == null) {
            response.put("flag", "false");
            return response;
        }
        response.put("flag", "true");
        response.put("answer", JSON.toJSONString(answer));
        return response;
    }

    @RequestMapping(value = "/getByTid/{tid}", method = RequestMethod.GET)
    public HashMap<String, String> getByTid(
            @PathVariable long tid
    ) {
        HashMap<String, String> response = new HashMap<>();
        List<Answer> alist = answerRepository.findByTid(tid);
        int sz = alist.size();
        for (int i=0; i<sz; ++i) alist.get(i).setImg("");
        System.out.println("answer/getByTid("+ tid + ") " + JSON.toJSONString(alist) + now());
        if (alist == null) {
            response.put("flag", "false");
            return response;
        }
        response.put("flag", "true");
        response.put("alist", JSON.toJSONString(alist));
        return response;
    }

}
