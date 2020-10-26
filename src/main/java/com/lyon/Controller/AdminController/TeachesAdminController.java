package com.lyon.Controller.AdminController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Teaches;
import com.lyon.Repository.TeachesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.lyon.Security.logTime.now;

@RestController
@RequestMapping("/api/teaches")
public class TeachesAdminController {
    @Autowired
    private TeachesRepository teachesRepository;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public HashMap<String, String> add(
            @RequestBody HashMap<String, String> data
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("Teaches/add " + JSON.toJSONString(data) + now());
        long uid = Long.parseLong(data.get("uid"));
        long cid = Long.parseLong(data.get("cid"));
        Teaches temp = teachesRepository.findByUidAndCid(uid, cid);
        if (temp != null) {
            response.put("flag", "false");
            return response;
        }
        temp = new Teaches();
        temp.setUid(uid);
        temp.setCid(cid);
        teachesRepository.save(temp);
        response.put("flag", "true");
        return response;
    }

    @RequestMapping(value = "/{uid}/{cid}", method = RequestMethod.DELETE)
    public HashMap<String, String> delete(
            @PathVariable long uid,
            @PathVariable long cid
    ) {
        HashMap<String, String> response = new HashMap<>();
        System.out.println("Teaches/delete " + uid + "/" + cid + now());
        Teaches temp = teachesRepository.findByUidAndCid(uid, cid);
        if (temp == null) {
            response.put("flag", "false");
            return response;
        }
        teachesRepository.deleteByUidAndCid(uid, cid);
        response.put("flag", "true");
        return response;
    }

}
