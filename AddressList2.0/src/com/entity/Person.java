package com.entity;

public class Person {
    private String name;
    private String gender;
    private int age;
    private String tel;
    private int id;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public String getTel() {
        return tel;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "pojo{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", phonenumber='" + tel + '\'' +
                '}';
    }
    public Person(String name, String gender, Integer age, String tel) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.tel = tel;
    }
    public Person() {
    }
    public Person(String name, String gender, int age, String tel,int id) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.tel = tel;
        this.id=id;
    }


}

