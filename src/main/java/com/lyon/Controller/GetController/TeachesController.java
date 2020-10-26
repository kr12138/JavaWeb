package com.lyon.Controller.GetController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Course;
import com.lyon.Entity.Teacher;
import com.lyon.Entity.Teaches;
import com.lyon.Repository.CourseRepository;
import com.lyon.Repository.TeacherRepository;
import com.lyon.Repository.TeachesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.lyon.Security.logTime.now;

@RestController
@RequestMapping("/api/teaches")
public class TeachesController {
    @Autowired
    private TeachesRepository teachesRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private TeacherRepository teacherRepository;

    @RequestMapping(value = "/uid/{uid}", method = RequestMethod.GET)
    public HashMap<String, String> getByUid(
            @PathVariable long uid
    ) {
        HashMap<String, String> response = new HashMap<>();
        final List<Teaches> list = teachesRepository.findByUid(uid);
        final List<Course> clist = new ArrayList<>();
        int sz = list.size();
        for (int i=0; i<sz; ++i)
            clist.add(courseRepository.findById(list.get(i).getCid()));
        System.out.println("teaches/getByUid/"+ uid + JSON.toJSONString(clist) + now());
        response.put("flag", "true");
        response.put("clist", JSON.toJSONString(clist));
        return response;
    }

    @RequestMapping(value = "/course/{cid}", method = RequestMethod.GET)
    public HashMap<String, String> getByCid(
            @PathVariable long cid
    ) {
        HashMap<String, String> response = new HashMap<>();
        final List<Teaches> list = teachesRepository.findByCid(cid);
        final List<Teacher> tlist = new ArrayList<>();
        int sz = list.size();
        for (int i=0; i<sz; ++i)
            tlist.add(teacherRepository.findById(list.get(i).getUid()));
        System.out.println("teaches/getByCid/"+ cid + JSON.toJSONString(tlist) + now());
        response.put("flag", "true");
        response.put("tlist", JSON.toJSONString(tlist));
        return response;
    }

    @RequestMapping(value = "/courses/uid/{uid}", method = RequestMethod.GET)
    public HashMap<String, String> getCourseByUid(
            @PathVariable long uid
    ) {
        HashMap<String, String> response = new HashMap<>();
        final List<Teaches> list = teachesRepository.findByUid(uid);
        final List<Course> clist = new ArrayList<>();
        int sz = list.size();
        for (int i=0; i<sz; ++i)
            clist.add(courseRepository.findById(list.get(i).getCid()));
        System.out.println("teaches/getCourseByUid/"+ uid + JSON.toJSONString(clist) + now());
        response.put("flag", "true");
        response.put("clist", JSON.toJSONString(clist));
        return response;
    }

}
