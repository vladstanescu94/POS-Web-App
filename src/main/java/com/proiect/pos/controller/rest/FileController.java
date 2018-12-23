package com.proiect.pos.controller.rest;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(value="/file")
public class FileController {

    @RequestMapping(value="/upload",method = RequestMethod.POST)
    public String uploadFile(@RequestParam("image") MultipartFile file){
//        String path = new File("src/main/resources/public/media/").getAbsolutePath();
        String path="/media";
        String userDir = System.getProperty("user.dir");
        path=userDir+path;

        File directory = new File(path);
        if (! directory.exists()){
            directory.mkdir();
        }

        final String uuid= UUID.randomUUID().toString().replace("-","");
        path=path.concat("/"+uuid+".png");
        if(!file.isEmpty()){
            System.out.println(path);
            try {
                BufferedImage src = null;
                src = ImageIO.read(new ByteArrayInputStream(file.getBytes()));
                File destination = new File(path); // something like C:/Users/tom/Documents/nameBasedOnSomeId.png
                ImageIO.write(src, "png", destination);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return uuid;
    }


}
