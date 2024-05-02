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
        return "{\"openapi\":\"3.0.1\",\"info\":{\"title\":\"OpenAPI definition\",\"version\":\"v0\"},\"tags\":[{\"name\":\"人群\",\"description\":\"ababababababababb\"}],\"paths\":{\"/person/add3\":{\"get\":{\"tags\":[\"人群\"],\"parameters\":[{\"name\":\"com.alibaba.fastjson2.JSONObject\",\"in\":\"query\",\"required\":false,\"schema\":{\"type\":\"object\",\"additionalProperties\":{\"type\":\"object\"}}},{\"name\":\"c2\",\"in\":\"query\",\"required\":false,\"schema\":{\"maxLength\":100,\"minLength\":10,\"type\":\"array\",\"items\":{\"type\":\"string\"}}},{\"name\":\"c3\",\"in\":\"query\",\"required\":true,\"schema\":{\"type\":\"string\"}},{\"name\":\"c4\",\"in\":\"query\",\"required\":false,\"schema\":{\"maximum\":100,\"type\":\"integer\",\"format\":\"int32\"}},{\"name\":\"c5\",\"in\":\"query\",\"required\":false,\"schema\":{\"type\":\"string\",\"format\":\"date-time\"}},{\"name\":\"c6\",\"in\":\"query\",\"required\":false,\"schema\":{\"type\":\"number\",\"format\":\"float\"}},{\"name\":\"c7\",\"in\":\"query\",\"required\":false,\"schema\":{\"type\":\"number\",\"format\":\"double\"}}],\"requestBody\":{\"content\":{\"application/json\":{}}},\"responses\":{\"200\":{\"content\":{\"*/*\":{\"schema\":{\"type\":\"string\"}}}}}}}},\"components\":{}}\n";
    }
}
