package com.example.spring_swagger.controller;

import com.example.spring_swagger.pojo.Person;
import com.example.spring_swagger.pojo.ResultBean;
import com.example.spring_swagger.pojo.ValidateGroup;
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
@Tag(name="人群",description = "ababababababababb")
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
    public ResultBean<Person> add(@RequestBody @Validated({ValidateGroup.insert.class}) List<Person> person, @RequestHeader("token") String token){
        ResultBean<Person> resultBean = new ResultBean();
        resultBean.setDate(new Person());
        return resultBean;
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
    public Person add5(@Schema(name = "A") String a, Date b, @Max(100) @Min(1) Integer c, List<String> d, Map<String,Integer> e) {
        return new Person();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人群")
    public String delete(String name){
        return "success";
    }
}
