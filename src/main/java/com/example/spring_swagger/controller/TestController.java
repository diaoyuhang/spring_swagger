package com.example.spring_swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Tag(name="测试接口")
public class TestController {


    @GetMapping("/test1")
    @Operation(summary = "测试接口1")
    public String test1(){
        return "success";
    }
}
