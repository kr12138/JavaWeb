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
public class QuestionController {
    @Autowired
    private QuestionRepository questionRepository;

    private int size = 5;   // 每页的数据量

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

    @RequestMapping(value = "/getPageByID/{page}/{ID}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/getPageByTitleContaining/{page}/{title}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/getPageByContentContaining/{page}/{content}", method = RequestMethod.GET)
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

//    @RequestMapping(value = "/getByTitleContaining", method = RequestMethod.POST)
//    public HashMap<String, String> getPageByTitleContaining(
//            @RequestBody HashMap<String, String> data
//    ) {
//        HashMap<String, String> response = new HashMap<>();
//        String title = data.get("title");
//        final List<Question> qlist = questionRepository.findAllByTitleContaining(title);
//        System.out.println("question/getByTitleContaining("+ title +") "+ JSON.toJSONString(qlist) + now());
//        response.put("flag", "true");
//        response.put("qlist", JSON.toJSONString(qlist));
//        return response;
//    }


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
