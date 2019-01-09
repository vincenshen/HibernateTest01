package com.vmware;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Blob;
import java.util.Date;
import java.util.Objects;

@Entity
public class Students {
    private int sid;    // 学号
    private String name;    // 姓名
    private String gender;  // 性别
    private Date birthday;  // 出生日期
    private String address; // 家庭住址
    private Blob picture; // 照片

    public Students() {
    }

    public Students(int sid, String name, String gender, Date birthday, String address) {
        this.sid = sid;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.address = address;
    }

    @Id
    @Column(name = "sid")
    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "birthday")
    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(java.sql.Date birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "picture")
    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        return "Students{" +
                "sid=" + sid +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Students students = (Students) o;
        return sid == students.sid &&
                Objects.equals(name, students.name) &&
                Objects.equals(gender, students.gender) &&
                Objects.equals(birthday, students.birthday) &&
                Objects.equals(address, students.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, name, gender, birthday, address);
    }
}
