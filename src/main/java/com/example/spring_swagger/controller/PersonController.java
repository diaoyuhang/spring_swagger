package com.example.spring_swagger.controller;

import com.example.spring_swagger.pojo.Person;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/person")
@Tag(name="人群")
public class PersonController {

    @PostMapping("/add")
    @Operation(summary = "新增人群")
    @ApiResponses(value={
            @ApiResponse(responseCode="400",description = "未找到对应用户"),
            @ApiResponse(responseCode="200",description = "成功响应")
    })
    public Person add(@RequestBody Person person){
        return person;
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人群")
    @Deprecated
    public String delete(@Parameter(name="姓名") String name){
        return "success";
    }
}
