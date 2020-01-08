package com.lyon.Controller.GetController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Answer;
import com.lyon.Repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

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
        System.out.println("answer/get/ "+ id + " " + JSON.toJSONString(answer) + now());
        if (answer == null) {
            response.put("flag", "false");
            return response;
        }
        answer.setImg("");
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
        System.out.println("answer/getByTid("+ tid + ") " + JSON.toJSONString(alist) + now());
        if (alist == null) {
            response.put("flag", "false");
            return response;
        }
        int sz = alist.size();
        for (int i=0; i<sz; ++i) alist.get(i).setImg("");
        response.put("flag", "true");
        response.put("alist", JSON.toJSONString(alist));
        return response;
    }

}
