package com.proiect.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
@SpringBootApplication
public class PosApplication {

    public static void main(String[] args) {
        Initialize();
        SpringApplication.run(PosApplication.class, args);
    }

    private static void Initialize(){
        checkForDirectory("/media");
        addDefaultMediaImage("http://www.moxmultisport.com/wp-content/uploads/no-image.jpg");
    }

    private static void addDefaultMediaImage(String url) {
        if(fileOrDirectoryExists("/media/-1.png"))
            return;
        try(InputStream in = new URL(url).openStream()){
            String path = System.getProperty("user.dir");
            path = path+"/media/-1.png";

            Files.copy(in, Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean fileOrDirectoryExists(String s) {
        String path = System.getProperty("user.dir");
        path = path+s;

        File file = new File(path);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    private static void checkForDirectory(String folder) {
        String path = System.getProperty("user.dir");
        path = path+folder;

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }


}
