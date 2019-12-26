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
public class QuestionUserController {
    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public HashMap<String, String> add(
            @RequestBody HashMap<String, String> data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("question/new " + JSON.toJSONString(data) + now());
        long cid = Long.parseLong(data.get("cid"));
        long sid = Long.parseLong(data.get("sid"));
        long tid = Long.parseLong(data.get("tid"));
        String title = data.get("title");
        String content = data.get("content");
        String img = data.get("img");
        Question question = new Question();
        question.setCid(cid);
        question.setSid(sid);
        question.setTid(tid);
        question.setTitle(title);
        question.setContent(content);
        question.setImg(img);
        question.setDate(now());
        questionRepository.save(question);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/myDelete", method = RequestMethod.POST)
    public HashMap<String, String> myDelete(
            @RequestBody Question data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("question/myDelete " + JSON.toJSONString(data) + now());
        if (!questionRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        questionRepository.deleteById(data.getId());
        response.put("flag", "true");
        return response;
    }
    
    @RequestMapping(value = "/myUpdate", method = RequestMethod.PUT)
    public HashMap<String, String> myUpdate(
            @RequestBody Question data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("question/myUpdate " + JSON.toJSONString(data) + now());
        if (!questionRepository.existsById(data.getId())) {
            response.put("flag", "false");
            return response;
        }
        Question question = questionRepository.findById(data.getId());
        question.setTitle(data.getTitle());
        question.setContent(data.getContent());
        questionRepository.save(data);
        response.put("flag", "true");
        return response;
    }


}
