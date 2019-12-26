package com.lyon.Controller;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Question;
import com.lyon.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/question")
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public HashMap<String, String> getAll() {
        HashMap<String, String> response = new HashMap<>();
        final List<Question> list = questionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        System.out.println("question/getAll " + JSON.toJSONString(list) + now());
        response.put("flag", "true");
        response.put("questions", JSON.toJSONString(list));
        return response;
    }

    @RequestMapping(value = "/get/{qid}", method = RequestMethod.GET)
    public HashMap<String, String> getById(
            @PathVariable long qid
    ) {
        HashMap<String, String> response = new HashMap<>();
        Question question = questionRepository.findById(qid);
        System.out.println("question/get("+ qid + ") " + JSON.toJSONString(question) + now());
        if (question == null) {
            response.put("flag", "false");
            return response;
        }
        response.put("flag", "true");
        response.put("question", JSON.toJSONString(question));
        return response;
    }

    @RequestMapping(value = "/getByCid/{cid}", method = RequestMethod.GET)
    public HashMap<String, String> getByCid(
            @PathVariable long cid
    ) {
        HashMap<String, String> response = new HashMap<>();
        List<Question> clist = questionRepository.findByCid(cid);
        System.out.println("question/get("+ cid + ") " + JSON.toJSONString(clist) + now());
        if (clist == null) {
            response.put("flag", "false");
            return response;
        }
        response.put("flag", "true");
        response.put("clist", JSON.toJSONString(clist));
        return response;
    }

//    @RequestMapping(value = "/add", method = RequestMethod.POST)
//    public HashMap<String, String> add(
//            @RequestBody Question data
//    ) {
//        HashMap<String, String> response = new HashMap<>();
//        System.out.println("question/add " + JSON.toJSONString(data) + now());
//        if (questionRepository.existsById(data.getId())) {
//            response.put("flag", "false");
//            return response;
//        }
//        questionRepository.save(data);
//        response.put("flag", "true");
//        return response;
//    }
//
//    @RequestMapping(value = "/update", method = RequestMethod.PUT)
//    public HashMap<String, String> update(
//            @RequestBody Question data
//    ) {
//        HashMap<String, String> response = new HashMap<>();
//        System.out.println("question/update " + JSON.toJSONString(data) + now());
//        if (!questionRepository.existsById(data.getId())) {
//            response.put("flag", "false");
//            return response;
//        }
//        questionRepository.save(data);
//        response.put("flag", "true");
//        return response;
//    }
//
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    public HashMap<String, String> delete(
//            @RequestBody Question data
//    ) {
//        HashMap<String, String> response = new HashMap<>();
//        System.out.println("question/delete " + JSON.toJSONString(data) + now());
//        if (!questionRepository.existsById(data.getId())) {
//            response.put("flag", "false");
//            return response;
//        }
//        questionRepository.deleteById(data.getId());
//        response.put("flag", "true");
//        return response;
//    }
}
