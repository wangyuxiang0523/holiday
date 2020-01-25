package com.fh.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fh.annotation.ExcelAnnotation;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@TableName("w_emp")
@ExcelAnnotation(title = "员工信息",sheetName = "员工信息",mkdir = "emp")
public class Emp {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "job_id")
    private Integer jobId;
    @TableField(value = "salary")
    @ExcelAnnotation(columnName = "工资")
    private BigDecimal salary;
    @TableField(value = "entry_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date entryTime;
    @TableField(value = "leader_id")
    private Integer leaderId;
    @TableField(value = "dept_id")
    private Integer deptId;
    @ExcelAnnotation(columnName = "名字")
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



    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }
}
