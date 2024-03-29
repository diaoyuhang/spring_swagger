package com.example.spring_swagger.pojo;

import com.alibaba.fastjson2.JSONObject;
import com.google.gson.JsonObject;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.HashMap;
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

    private Date[] h;

    private double[] i;
    private Double[] k;
    private List<List<Integer>> l;
    private List<List<String>> m;

    private HashMap<String,Car> n;

    private JsonObject o;
    private JSONObject p;

    private Car[] cars1;
    private List<List<Car>> cars2;
    private com.example.spring_swagger.config.Person person;

    public JsonObject getO() {
        return o;
    }

    public void setO(JsonObject o) {
        this.o = o;
    }

    public JSONObject getP() {
        return p;
    }

    public void setP(JSONObject p) {
        this.p = p;
    }

    public HashMap<String, Car> getN() {
        return n;
    }

    public void setN(HashMap<String, Car> n) {
        this.n = n;
    }

    public List<List<Integer>> getL() {
        return l;
    }

    public void setL(List<List<Integer>> l) {
        this.l = l;
    }

    public List<List<String>> getM() {
        return m;
    }

    public void setM(List<List<String>> m) {
        this.m = m;
    }

    public Date[] getH() {
        return h;
    }

    public void setH(Date[] h) {
        this.h = h;
    }

    public double[] getI() {
        return i;
    }

    public void setI(double[] i) {
        this.i = i;
    }

    public Double[] getK() {
        return k;
    }

    public void setK(Double[] k) {
        this.k = k;
    }

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
