package com.gaohan.dal.module1.dataobject;

import java.io.Serializable;

public class PersonalInfoDo implements Serializable {
    private Long id;

    private String name;

    private Integer age;

    private Integer sex;

    private String hobbies;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies == null ? null : hobbies.trim();
    }

    @Override
    public String toString() {
        return "PersonalInfoDo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }
}