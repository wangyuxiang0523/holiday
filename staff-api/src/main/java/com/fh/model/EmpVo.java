package com.fh.model;

import java.math.BigDecimal;
import java.util.Date;

public class EmpVo {
    private Integer id;

    private String name;

    private Integer gobId;

    private String gobname;

    private BigDecimal salary;

    private Date entryTime;

    private Integer deptId;

    private String deptName;

    private Integer leaderId;

    private String leaderName;


    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGobId() {
        return gobId;
    }

    public void setGobId(Integer gobId) {
        this.gobId = gobId;
    }


    public String getGobname() {
        return gobname;
    }

    public void setGobname(String gobname) {
        this.gobname = gobname;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
