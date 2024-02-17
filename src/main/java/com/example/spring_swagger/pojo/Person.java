package com.example.spring_swagger.pojo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.util.Date;
public class Person {

    @Schema(description = "名字")
    @NotBlank(message = "名字不能为空",groups = {ValidateGroup.insert.class})
    private String name;
    @Schema(description = "年龄")
    @Min(value = 0,message = "最小0岁")
    @Max(value = 200,message="最大200岁")
    private Integer age;
    @NotBlank(message = "出生日期不能为空",groups = {ValidateGroup.update.class})
    @Schema(description = "出生日期")
    private Date birthday;

//    private Home home;

    @Email(regexp = "asdfasdf",message = "邮件校验")
    @Size(min = 10,max = 20)
    private String email;

//    private com.example.spring_swagger.config.Person person;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    public com.example.spring_swagger.config.Person getPerson() {
//        return person;
//    }

//    public void setPerson(com.example.spring_swagger.config.Person person) {
//        this.person = person;
//    }

//    public Home getHome() {
//        return home;
//    }

//    public void setHome(Home home) {
//        this.home = home;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
