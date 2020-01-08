package com.lyon.Controller.GetController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Answer;
import com.lyon.Entity.Question;
import com.lyon.Entity.User;
import com.lyon.Repository.AnswerRepository;
import com.lyon.Repository.QuestionRepository;
import com.lyon.Repository.UserRepository;
import static com.lyon.Security.logTime.now;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserMessageController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @RequestMapping(value = "/getNewMessageCountByUid/{id}", method = RequestMethod.GET)
    public HashMap<String, String> getNewMessageCountByUid(
            @PathVariable long id
    ) {
        HashMap<String, String> response = new HashMap<>();
        response.put("flag", "true");
        final User user = userRepository.findById(id);
        short identity = user.getIdentity();
        int count = 0;
        if (identity == 1) {
            List<Question> qlist = questionRepository.findBySid(id);
            int sz = qlist.size();
            for (int i=0; i<sz; ++i) {
                List<Answer> alist = answerRepository.findByQidAndRead(qlist.get(i).getId(), false);
                count += alist.size();
            }
        } else if (identity == 2) {
            List<Question> qlist = questionRepository.findByTidAndRead(id, false);
            count += qlist.size();
        }
        response.put("count", Integer.toString(count));
        System.out.println("user/getNewMessageCountByUid("+ id +") " + count + now());
        return response;
    }

    @RequestMapping(value = "/getNewMessageByUid/{id}", method = RequestMethod.GET)
    public HashMap<String, String> getNewMessageByUid(
            @PathVariable long id
    ) {
        HashMap<String, String> response = new HashMap<>();
        response.put("flag", "true");
        final User user = userRepository.findById(id);
        short identity = user.getIdentity();
        List<Question> qlist = new ArrayList<>();
        if (identity == 1) {
            List<Question> qlist2 = questionRepository.findBySid(id);
            int sz = qlist2.size();
            for (int i=0; i<sz; ++i) {
                qlist2.get(i).setImg("");
                List<Answer> alist = answerRepository.findByQidAndRead(qlist2.get(i).getId(), false);
                if (!alist.isEmpty())
                    qlist.add(qlist2.get(i));
            }
        } else if (identity == 2)
            qlist = questionRepository.findByTidAndRead(id, false);
        response.put("qlist", JSON.toJSONString(qlist));
        System.out.println("user/getNewMessageByUid("+ id +") " + JSON.toJSONString(qlist) + now());
        return response;
    }

}