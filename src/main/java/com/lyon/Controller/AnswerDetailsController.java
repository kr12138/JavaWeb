package com.lyon.Controller;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Answer;
import com.lyon.Entity.Image;
import com.lyon.Entity.Teacher;
import com.lyon.Repository.AnswerRepository;
import com.lyon.Repository.ImageRepository;
import com.lyon.Repository.QuestionAuthRepository;
import com.lyon.Repository.TeacherRepository;
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
public class AnswerDetailsController {
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private QuestionAuthRepository questionAuthRepository;

    class Row implements Serializable { // 每一行回答的数据
        String prof;
        String name;
        String avatar;
        String content;
        String date;
        String img;

        public String getProf() {
            return prof;
        }

        public void setProf(String prof) {
            this.prof = prof;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }
    }

    @RequestMapping(value = "/getDetailsByQid/{qid}/{uid}", method = RequestMethod.GET)
    public HashMap<String, String> getDetailsByQid(
            @PathVariable long qid,
            @PathVariable long uid
    ) {
        HashMap<String, String> response = new HashMap<>();
        List<Answer> alist = answerRepository.findByQid(qid);
        System.out.println("answer/getDetailsByQid("+ qid + ") uid " + uid + JSON.toJSONString(alist) + now());
        int sz = alist.size();
        List<Row> rlist = new ArrayList<>();
        for (int i=0; i<sz; ++i) {
            Teacher teacher = teacherRepository.findById(alist.get(i).getTid());
            Image img = imageRepository.findById(alist.get(i).getTid());
            if (teacher == null) {
                response.put("flag", "false");
                return response;
            }
            if (questionAuthRepository.existsByTid(teacher.getId()) &&
                    !questionAuthRepository.existsByTidAndSid(teacher.getId(), uid)) {
                response.put("flag", "false");
                return response;
            }

            Answer answer = alist.get(i);
            Row row = new Row();
            row.setName(teacher.getName());
            row.setProf(teacher.getProf());
            if (img != null)
                row.setAvatar(img.getImg());
            else
                row.setAvatar(null);
            row.setContent(answer.getContent());
            row.setDate(answer.getDate());
            row.setImg(answer.getImg());
            rlist.add(row);
        }
        response.put("flag", "true");
        response.put("rlist", JSON.toJSONString(rlist));
        return response;
    }
}
