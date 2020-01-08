package com.lyon.Controller.GetController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Image;
import com.lyon.Entity.Question;
import com.lyon.Entity.User;
import com.lyon.Repository.ImageRepository;
import com.lyon.Repository.QuestionRepository;
import com.lyon.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/question")
public class QuestionDetailsController {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = "/getDetails/{qid}", method = RequestMethod.GET)
    public HashMap<String, String> getDetailsById(
            @PathVariable long qid
    ) {
        HashMap<String, String> response = new HashMap<>();
        Question question = questionRepository.findById(qid);
        System.out.println("question/getDetails("+ qid + ") " + JSON.toJSONString(question) + now());
        if (question == null) {
            response.put("flag", "false");
            return response;
        }
        Image image = imageRepository.findById(question.getSid());
        User user = userRepository.findById(question.getSid());
        if (user == null) {
            response.put("flag", "false");
        }
        response.put("flag", "true");
        response.put("title", question.getTitle());
        response.put("content", question.getContent());
        if (image != null)
            response.put("avatar", image.getImg());
        else
            response.put("avatar", null);
        response.put("name", user.getName());
        response.put("date", question.getDate());
        response.put("img", question.getImg());
        return response;
    }

}
