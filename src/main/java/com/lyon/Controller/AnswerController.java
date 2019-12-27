package com.lyon.Controller;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Answer;
import com.lyon.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/answer")
public class AnswerController {
    @Autowired
    private AnswerRepository answerRepository;

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
