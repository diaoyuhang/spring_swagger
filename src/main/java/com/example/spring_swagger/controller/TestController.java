package com.example.spring_swagger.controller;

import com.alibaba.fastjson2.JSONObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/test")
@Tag(name="test",description = "abababab")
public class TestController {


    @GetMapping("/test1")
    @Operation(summary = "测试接口1")
    public String test1(){
        return "success";
    }

    @PostMapping("/createApi")
    public String createApi(@RequestBody JSONObject jsonObject){
        System.out.println(jsonObject.toString());
        return "success";
    }

    @GetMapping("/download")
    public void downloadFile(@NotBlank(message = "fileId is empty")String fileId, HttpServletResponse response) throws IOException {
    }

    @GetMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        return "";
    }
}
