package com.example.spring_swagger.controller;

import com.example.spring_swagger.pojo.Person;
import com.example.spring_swagger.pojo.ResultBean;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

@RestController
@RequestMapping("/person")
@Tag(name="人群")
public class PersonController {

    /**
     *
     * @param person 参数
     * @param token 请求头参数
     * @return 返回resultBean
     */
    @PostMapping("/add1")
    @Operation(summary = "新增人群")
    @ApiResponses(value={
            @ApiResponse(responseCode="400",description = "未找到对应用户"),
            @ApiResponse(responseCode="200",description = "成功响应")
    })
    public ResultBean<Person> add(@RequestBody Person person, @RequestHeader("token") String token){
        ResultBean<Person> resultBean = new ResultBean();
        resultBean.setDate(new Person());
        return resultBean;
    }

    @GetMapping("/add/{age}")
    @Operation(summary = "新增人群")
    @ApiResponses(value={
            @ApiResponse(responseCode="400",description = "未找到对应用户"),
            @ApiResponse(responseCode="200",description = "成功响应")
    })
    public Person add2(@NotBlank(message = "name不能为空") String name, @PathVariable("age") String age) {
        return new Person();
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人群")
    @Deprecated
    public String delete(@Parameter(name="姓名") String name){
        return "success";
    }
}
