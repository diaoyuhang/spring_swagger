package com.example.spring_swagger.pojo;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.Date;
@Schema(description = "人类")
public class Person {

    @Schema(description = "名字")
    @NotBlank(message = "名字不能为空")
    private String name;
    @Schema(description = "年龄")
    @Min(value = 0,message = "最小0岁")
    private Integer age;
    @NotBlank(message = "出生日期不能为空")
    @Schema(description = "出生日期")
    private Date birthday;

    private Home home;

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

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
