package com.example.spring_swagger.pojo;

import java.util.List;
import java.util.Map;

public class Home {

    private String address;
    private List<Car> familyMember;

    private Map<String,Car> familyComposition;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Car> getFamilyMember() {
        return familyMember;
    }

    public void setFamilyMember(List<Car> familyMember) {
        this.familyMember = familyMember;
    }

    public Map<String, Car> getFamilyComposition() {
        return familyComposition;
    }

    public void setFamilyComposition(Map<String, Car> familyComposition) {
        this.familyComposition = familyComposition;
    }
}
