package com.wyzxw.vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class EmployeeVo {
    private Integer empid;
    private String empname;
    private Double bsaralry;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date hiredate;
    private String address;
    private Integer depid;
    private String depname;

    public EmployeeVo() {
    }

    public EmployeeVo(Integer empid, String empname, Double bsaralry, Date hiredate, String address, Integer depid, String depname) {
        this.empid = empid;
        this.empname = empname;
        this.bsaralry = bsaralry;
        this.hiredate = hiredate;
        this.address = address;
        this.depid = depid;
        this.depname = depname;
    }

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
        this.empname = empname;
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
        this.address = address;
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }
}
