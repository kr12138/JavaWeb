package com.lyon.Controller.GetController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Answer;
import com.lyon.Entity.Question;
import com.lyon.Repository.AnswerRepository;
import com.lyon.Repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/answer")
public class AnswerIntroController {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    class Row implements Serializable { // 每一条回答的简介
        long qid;
        long aid;
        String title;
        String content;
        String date;

        public long getQid() {
            return qid;
        }

        public void setQid(long qid) {
            this.qid = qid;
        }

        public long getAid() {
            return aid;
        }

        public void setAid(long aid) {
            this.aid = aid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }

    @RequestMapping(value = "/getIntroByTid/{tid}", method = RequestMethod.GET)
    public HashMap<String, String> getIntroByTid(
            @PathVariable long tid
    ) {
        HashMap<String, String> response = new HashMap<>();
        List<Answer> alist = answerRepository.findByTid(tid);
        System.out.println("answer/getIntroByTid("+ tid + ") " + JSON.toJSONString(alist) + now());
        int sz = alist.size();
        List<Row> rlist = new ArrayList<>();
        for (int i=0; i<sz; ++i) {
            Question question = questionRepository.findById(alist.get(i).getQid());
            if (question == null) {
                response.put("flag", "false");
                return response;
            }
            Answer answer = alist.get(i);
            Row row = new Row();
            row.setQid(question.getId());
            row.setAid(answer.getId());
//            if (question.getTitle().length() > 9)
//                row.setTitle(question.getTitle().substring(0,9) + "…");
//            else
                row.setTitle(question.getTitle());
//            if (answer.getContent().length() > 9)
//                row.setContent(answer.getContent().substring(0,9) + "…");
//            else
                row.setContent(answer.getContent());
            row.setDate(answer.getDate());
            rlist.add(row);
        }
        response.put("flag", "true");
        response.put("rlist", JSON.toJSONString(rlist));
        return response;
    }
}
