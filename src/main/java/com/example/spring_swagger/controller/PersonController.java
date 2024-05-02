package com.example.spring_swagger.controller;

import com.alibaba.fastjson2.JSONObject;
import com.example.spring_swagger.pojo.Car;
import com.example.spring_swagger.pojo.Person;
import com.example.spring_swagger.pojo.ResultBean;
import com.example.spring_swagger.pojo.ValidateGroup;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/person")
@Tag(name="人群",description = "qwerty")
public class PersonController {

    /**
     *
     * @param person 参数
     * @param token 请求头参数
     * @return 返回resultBean
     */
    @PostMapping("/add1")
    @Operation(summary = "新增人群",description = "ababb")
    @ApiResponses(value={
            @ApiResponse(responseCode="400",description = "未找到对应用户"),
            @ApiResponse(responseCode="0",description = "成功响应")
    })
    public ResultBean<Person> add(@RequestBody @Validated({ValidateGroup.insert.class}) ResultBean<List<Person<Car>>> person, @RequestHeader("token") String token,@RequestHeader("token2") Integer token2){
        ResultBean<Person> resultBean = new ResultBean();
        resultBean.setDate(new Person());
        return resultBean;
    }

    @PostMapping("/add2")
    @Operation(summary = "新增人群",description = "ababb")
    @ApiResponses(value={
            @ApiResponse(responseCode="400",description = "未找到对应用户"),
            @ApiResponse(responseCode="0",description = "成功响应")
    })
    public Date add2(Person person, @RequestHeader("token") String token, @RequestHeader("token2") Integer token2){
        ResultBean<Person> resultBean = new ResultBean();
        resultBean.setDate(new Person());
        return null;
    }

    @GetMapping("/add3")
    public String add3(JSONObject jsonObject,@Size(min=10,max = 100) List<String> c2,@NotBlank(message="不为空") String c3,@Max(100) Integer c4,Date c5,Float c6,Double c7){
        ResultBean<Person> resultBean = new ResultBean();
        resultBean.setDate(new Person());
        return null;
    }

    @GetMapping("/add/{age}")
    @Operation(summary = "新增人群",operationId = "123456")
    @ApiResponses(value={
            @ApiResponse(responseCode="400",description = "未找到对应用户"),
            @ApiResponse(responseCode="200",description = "成功响应")
    })
    public Person add2(@NotBlank(message = "name不能为空") @Size(min = 0,max = 100) @RequestParam("userName") String name,
                       @PathVariable("age") String age) {
        return new Person();
    }

    @PostMapping(value = "/add/add3")
    @Operation(summary = "新增人群")
    @ApiResponses(value={
            @ApiResponse(responseCode="400",description = "未找到对应用户"),
            @ApiResponse(responseCode="200",description = "成功响应")
    })
    public Person add3(Person person) {
        return new Person();
    }

    @PostMapping(value = "/add/add4")
    @Operation(summary = "新增人群")
    public Person add4(Person person) {
        return new Person();
    }

    @PostMapping(value = "/add/add5")
    @Operation(summary = "新增人群")
    public Person add5(@Schema(name = "A") String a, Date b, @Max(100) @Min(1) Integer c, List<String> d, Map<String,Map<String,Integer>> e) {
        return new Person();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人群")
    public String delete(String name){
        return "success";
    }
}
