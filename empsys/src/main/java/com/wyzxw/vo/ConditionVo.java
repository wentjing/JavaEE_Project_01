package com.wyzxw.vo;

/**
 * 查询条件的工具类
 */
public class ConditionVo {

    private Integer depid;
    private String address;
    private Double min_bsaralry;
    private Double max_bsaralry;

    public ConditionVo() {
    }

    public Integer getDepid() {
        return depid;
    }

    public void setDepid(Integer depid) {
        this.depid = depid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getMin_bsaralry() {
        return min_bsaralry;
    }

    public void setMin_bsaralry(Double min_bsaralry) {
        this.min_bsaralry = min_bsaralry;
    }

    public Double getMax_bsaralry() {
        return max_bsaralry;
    }

    public void setMax_bsaralry(Double max_bsaralry) {
        this.max_bsaralry = max_bsaralry;
    }
}
