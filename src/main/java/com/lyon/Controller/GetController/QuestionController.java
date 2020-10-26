package com.lyon.Controller.GetController;

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
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    private int size = 5;   // 每页的数据量

    @RequestMapping(value = "s", method = RequestMethod.GET)
    public HashMap<String, String> getAll() {
        HashMap<String, String> response = new HashMap<>();
        final List<Question> list = questionRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        System.out.println("question/getAll " + JSON.toJSONString(list) + now());
        response.put("flag", "true");
        response.put("questions", JSON.toJSONString(list));
        return response;
    }

    @RequestMapping(value = "/{qid}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/cid/{cid}", method = RequestMethod.GET)
    public HashMap<String, String> getByCid(
            @PathVariable long cid
    ) {
        HashMap<String, String> response = new HashMap<>();
        List<Question> qlist = questionRepository.findByCid(cid);
        System.out.println("question/getByCid("+ cid + ") " + JSON.toJSONString(qlist) + now());
        if (qlist == null) {
            response.put("flag", "false");
            return response;
        }
        response.put("flag", "true");
        response.put("qlist", JSON.toJSONString(qlist));
        return response;
    }

    @RequestMapping(value = "/page/{page}/id/{ID}", method = RequestMethod.GET)
    public HashMap<String, String> getPageByID(
            @PathVariable int page,
            @PathVariable long ID
    ) {
        HashMap<String, String> response = new HashMap<>();
        final Page<Question> qlist = questionRepository.findById(
                ID,
                PageRequest.of(page, size,
                        Sort.by(Sort.Direction.ASC, "id")
                )
        );
        System.out.println("question/getPageByID("+ ID +") page("+ page +") "+ JSON.toJSONString(qlist) + now());
        response.put("flag", "true");
        response.put("totalPages", Integer.toString(qlist.getTotalPages()));
        response.put("qlist", JSON.toJSONString(qlist.getContent()));
        return response;
    }

    @RequestMapping(value = "/page/{page}/titleContaining/{title}", method = RequestMethod.GET)
    public HashMap<String, String> getPageByTitleContaining(
            @PathVariable int page,
            @PathVariable String title
    ) {
        HashMap<String, String> response = new HashMap<>();
//        title = "%" + title + "%";
        final Page<Question> qlist = questionRepository.findByTitleContaining(
                title,
                PageRequest.of(page, size,
                        Sort.by(Sort.Direction.ASC, "id")
                )
        );
        System.out.println("question/getPageByTitleContaining("+ title +") page("+ page +") "+ JSON.toJSONString(qlist) + now());
        response.put("flag", "true");
        response.put("totalPages", Integer.toString(qlist.getTotalPages()));
        response.put("qlist", JSON.toJSONString(qlist.getContent()));
        return response;
    }

    @RequestMapping(value = "/page/{page}/contentContaining/{content}", method = RequestMethod.GET)
    public HashMap<String, String> getPageByContentContaining(
            @PathVariable int page,
            @PathVariable String content
    ) {
        HashMap<String, String> response = new HashMap<>();
//        content = "%" + content + "%";
        final Page<Question> qlist = questionRepository.findByContentContaining(
                content,
                PageRequest.of(page, size,
                        Sort.by(Sort.Direction.ASC, "id")
                )
        );
        System.out.println("question/getPageByContentContaining("+ content +") page("+ page +") "+ JSON.toJSONString(qlist) + now());
        response.put("flag", "true");
        response.put("totalPages", Integer.toString(qlist.getTotalPages()));
        response.put("qlist", JSON.toJSONString(qlist.getContent()));
        return response;
    }


}
