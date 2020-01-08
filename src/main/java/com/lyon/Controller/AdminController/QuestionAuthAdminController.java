package com.lyon.Controller.AdminController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.QuestionAuth;
import com.lyon.Repository.QuestionAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.lyon.Security.logTime.now;

@RestController
@RequestMapping("/api/questionAuth")
public class QuestionAuthAdminController {
    @Autowired
    private QuestionAuthRepository questionAuthRepository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public HashMap<String, String> add(
            @RequestBody HashMap<String, String> data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("QuestionAuth/add " + JSON.toJSONString(data) + now());
        long sid = Long.parseLong(data.get("sid"));
        long tid = Long.parseLong(data.get("tid"));
        QuestionAuth temp = questionAuthRepository.findBySidAndTid(sid, tid);
        if (temp != null) {
            response.put("flag", "false");
            return response;
        }
        temp = new QuestionAuth();
        temp.setSid(sid);
        temp.setTid(tid);
        questionAuthRepository.save(temp);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public HashMap<String, String> delete(
            @RequestBody HashMap<String, String> data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("QuestionAuth/delete " + JSON.toJSONString(data) + now());
        long sid = Long.parseLong(data.get("sid"));
        long tid = Long.parseLong(data.get("tid"));
        QuestionAuth temp = questionAuthRepository.findBySidAndTid(sid, tid);
        if (temp == null) {
            response.put("flag", "false");
            return response;
        }
        questionAuthRepository.deleteBySidAndTid(sid, tid);
        response.put("flag", "true");
        return response;
    }

}
