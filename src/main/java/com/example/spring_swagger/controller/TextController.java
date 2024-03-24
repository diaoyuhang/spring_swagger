package com.example.spring_swagger.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/text")
public class TextController {

    @GetMapping("/content1")
    public String test1(){
        return "{\"openapi\":\"3.0.1\",\"info\":{\"title\":\"OpenAPI definition\",\"version\":\"v0\"},\"tags\":[{\"name\":\"人群\",\"description\":\"ababababababababb\"}],\"paths\":{\"/person/add2\":{\"post\":{\"tags\":[\"人群\"],\"summary\":\"新增人群\",\"description\":\"ababb\",\"parameters\":[{\"name\":\"token\",\"in\":\"header\",\"required\":true,\"schema\":{\"type\":\"string\"}},{\"name\":\"token2\",\"in\":\"header\",\"required\":true,\"schema\":{\"type\":\"string\"}}],\"requestBody\":{\"content\":{\"application/json\":{\"schema\":{\"type\":\"object\",\"additionalProperties\":{\"type\":\"object\"}}}}},\"responses\":{\"400\":{\"description\":\"未找到对应用户\",\"content\":{\"*/*\":{\"schema\":{\"type\":\"string\"}}}},\"0\":{\"description\":\"成功响应\",\"content\":{\"*/*\":{\"schema\":{\"type\":\"string\"}}}}}}}},\"components\":{}}\n";
    }
}
