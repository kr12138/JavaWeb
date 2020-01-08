package com.lyon.Controller.UserController;

import com.lyon.Repository.QuestionAuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/questionAuth")
public class QuestionAuthUserController {
    @Autowired
    private QuestionAuthRepository questionAuthRepository;
//    @Autowired
//    private CourseRepository courseRepository;
//    @Autowired
//    private TeacherRepository teacherRepository;

//    @RequestMapping(value = "/getBySid/{sid}", method = RequestMethod.GET)
//    public HashMap<String, String> getBySid(
//            @PathVariable long sid
//    ) {
//        HashMap<String, String> response = new HashMap<>();
//        final List<QuestionAuth> list = questionAuthRepository.findBySid(sid);
//        final List<Course> clist = new ArrayList<>();
//        int sz = list.size();
//        for (int i=0; i<sz; ++i)
//            clist.add(courseRepository.findById(list.get(i).getSid()));
//        System.out.println("questionAuth/getBySid/"+ sid + JSON.toJSONString(clist) + now());
//        response.put("flag", "true");
//        response.put("clist", JSON.toJSONString(clist));
//        return response;
//    }

//    @RequestMapping(value = "/getByTid/{tid}", method = RequestMethod.GET)
//    public HashMap<String, String> getByTid(
//            @PathVariable long tid
//    ) {
//        HashMap<String, String> response = new HashMap<>();
//        final List<QuestionAuth> list = questionAuthRepository.findByTid(tid);
//        final List<Teacher> tlist = new ArrayList<>();
//        int sz = list.size();
//        for (int i=0; i<sz; ++i)
//            tlist.add(teacherRepository.findById(list.get(i).getSid()));
//        System.out.println("questionAuth/getByTid/"+ tid + JSON.toJSONString(tlist) + now());
//        response.put("flag", "true");
//        response.put("tlist", JSON.toJSONString(tlist));
//        return response;
//    }
    @RequestMapping(value = "/hasTid/{tid}", method = RequestMethod.GET)
    public HashMap<String, String> hasTid(
            @PathVariable long tid
    ) {
        HashMap<String, String> response = new HashMap<>();
        if (questionAuthRepository.existsByTid(tid))
            response.put("flag", "true");
        else
            response.put("flag", "false");
        return response;
    }

    @RequestMapping(value = "/hasTidAndSid/{tid}/{sid}", method = RequestMethod.GET)
    public HashMap<String, String> hasTid(
            @PathVariable long tid,
            @PathVariable long sid
    ) {
        HashMap<String, String> response = new HashMap<>();
        if (questionAuthRepository.existsByTidAndSid(tid, sid))
            response.put("flag", "true");
        else
            response.put("flag", "false");
        return response;
    }

//    @RequestMapping(value = "/hasSid/{sid}", method = RequestMethod.GET)
//    public HashMap<String, String> hasSid(
//            @PathVariable long sid
//    ) {
//        HashMap<String, String> response = new HashMap<>();
//        if (questionAuthRepository.existsBySid(sid))
//            response.put("flag", "true");
//        else
//            response.put("flag", "false");
//        return response;
//    }

}
