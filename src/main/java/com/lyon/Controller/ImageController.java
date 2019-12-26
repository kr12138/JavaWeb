package com.lyon.Controller;

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

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
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

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    public HashMap<String, String> addUser(
            @RequestBody String data
    ) {
        HashMap<String, String> response = new HashMap<>();
//        data.replaceAll("")
        System.out.println("image/new " + data + now());
        System.out.println("image/new " + JSON.toJSONString(data) + now());
        Image image = new Image();
        long id = image.getIid();
        System.out.println("image/new id now: " + id);
        image.setImg(data);
        imageRepository.save(image);
        id = image.getIid();
        System.out.println("image/new id now: " + id);
        image = imageRepository.findById(id);
        if (image.getImg().equals(""))
            response.put("flag", "false");
        else
            response.put("flag", "true");
        System.out.println("image/new image " + JSON.toJSONString(image) + now());
        id = image.getIid();
        System.out.println("image/new id now: " + id);
        return response;
    }

}
