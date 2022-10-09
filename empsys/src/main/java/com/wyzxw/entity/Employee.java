package com.wyzxw.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Employee {
    private Integer empid;

    private String empname;

    private Double bsaralry;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hiredate;

    private String address;

    private Integer depid;

    public Integer getEmpid() {
        return empid;
    }

    public void setEmpid(Integer empid) {
        this.empid = empid;
    }

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname == null ? null : empname.trim();
    }

    public Double getBsaralry() {
        return bsaralry;
    }

    public void setBsaralry(Double bsaralry) {
        this.bsaralry = bsaralry;
    }

    public Date getHiredate() {
        return hiredate;
    }

    public void setHiredate(Date hiredate) {
        this.hiredate = hiredate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }
}