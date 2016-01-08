package com.gaohan.biz.module1.vo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gaohan on 2015/12/29.
 */
public class PersonalInfoVo implements Serializable{
    private static final long serialVersionUID = 2014844669608669907L;

    private Long id;
    private String name;
    private Integer age;
    private Integer sex;
    private String hobbies;

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
        this.name = name;
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
        this.hobbies = hobbies;
    }

    @Override
    public String toString() {
        return "PersonalInfoVo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", hobbies='" + hobbies + '\'' +
                '}';
    }
}
