package com.example.spring_swagger.pojo;

import java.util.List;
import java.util.Map;

public class Home {

    private String address;
    private String a;

    private List<Person> familyMember;

    private Map<String,Person> familyComposition;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public List<Person> getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(List<Person> familyMember) {
        this.familyMember = familyMember;
    }

    public Map<String, Person> getFamilyComposition() {
        return familyComposition;
    }

    public void setFamilyComposition(Map<String, Person> familyComposition) {
        this.familyComposition = familyComposition;
    }
}
