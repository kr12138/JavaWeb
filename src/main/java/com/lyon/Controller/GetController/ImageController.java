package com.lyon.Controller.GetController;

import com.alibaba.fastjson.JSON;
import com.lyon.Entity.Image;
import com.lyon.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

import static com.lyon.Security.logTime.now;


@RestController
@RequestMapping("/api/image")
public class ImageController {

    @Autowired
    private ImageRepository imageRepository;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HashMap<String, String> getById(
            @PathVariable long id
    ) {
        HashMap<String, String> response = new HashMap<>();
        Image image = imageRepository.findById(id);
        System.out.println("image/get("+ id + ") " + JSON.toJSONString(image) + now());
        if (image == null) {
            response.put("flag", "false");
            return response;
        }
        response.put("flag", "true");
        response.put("image", JSON.toJSONString(image));
        return response;
    }

}
