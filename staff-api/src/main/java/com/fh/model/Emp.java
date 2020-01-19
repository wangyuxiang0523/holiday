package com.fh.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@TableName("w_emp")
public class Emp {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "job_id")
    private Integer job_id;
    @TableField(value = "salary")
    private BigDecimal salary;
    @TableField(value = "entry_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date entry_time;
    @TableField(value = "leader_id")
    private Integer leader_id;
    @TableField(value = "dept_id")
    private Integer dept_id;

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJob_id() {
        return job_id;
    }

    public void setJob_id(Integer job_id) {
        this.job_id = job_id;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Date getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Date entry_time) {
        this.entry_time = entry_time;
    }

    public Integer getLeader_id() {
        return leader_id;
    }

    public void setLeader_id(Integer leader_id) {
        this.leader_id = leader_id;
    }

    public Integer getDept_id() {
        return dept_id;
    }

    public void setDept_id(Integer dept_id) {
        this.dept_id = dept_id;
    }
}
