package com.example.spring_swagger.pojo;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

public class Person {

    @Schema(description = "名字")
    @NotBlank(message = "名字不能为空",groups = {ValidateGroup.insert.class})
    private String name;
    @Schema(description = "年龄")
    @Min(value = 0,message = "最小0岁")
    @Max(value = 200,message="最大200岁")
    private int age;
    @NotBlank(message = "出生日期不能为空",groups = {ValidateGroup.update.class})
    @Schema(description = "出生日期")
    private Date birthday;
    @Email(regexp = "asdfasdf",message = "邮件校验")
    @Size(min = 10,max = 20)
    private String email;
    private Home home;

    private Long a;
    private Double b;
    private Float c;
    private Boolean d;

    private byte e;
    private int[] f;
    private String[] g;
    private Car[] cars1;
    private List<List<Car>> cars2;





    private com.example.spring_swagger.config.Person person;


    public String[] getG() {
        return g;
    }

    public void setG(String[] g) {
        this.g = g;
    }

    public Car[] getCars1() {
        return cars1;
    }

    public void setCars1(Car[] cars1) {
        this.cars1 = cars1;
    }

    public List<List<Car>> getCars2() {
        return cars2;
    }

    public void setCars2(List<List<Car>> cars2) {
        this.cars2 = cars2;
    }

    public Long getA() {
        return a;
    }

    public void setA(Long a) {
        this.a = a;
    }

    public Double getB() {
        return b;
    }

    public void setB(Double b) {
        this.b = b;
    }

    public Float getC() {
        return c;
    }

    public void setC(Float c) {
        this.c = c;
    }

    public Boolean getD() {
        return d;
    }

    public void setD(Boolean d) {
        this.d = d;
    }

    public byte getE() {
        return e;
    }

    public void setE(byte e) {
        this.e = e;
    }

    public int[] getF() {
        return f;
    }

    public void setF(int[] f) {
        this.f = f;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public com.example.spring_swagger.config.Person getPerson() {
        return person;
    }
//
    public void setPerson(com.example.spring_swagger.config.Person person) {
        this.person = person;
    }
//
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}
