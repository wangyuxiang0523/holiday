package com.fh.service;

import com.fh.model.*;

import java.util.List;

public interface StaffService {
    PageBean<EmpVo> queryStaffList(PageBean<EmpVo> pageBean);

    List<Job> queryJobList();

    List<Dept> queryDeptList();

    List<Leader> queryLeaderList();

    List<Emp> queryEmpNameByDept(Integer dept_id);

    void addEmp(Emp emp);

    Emp queryEmpById(Integer id);

    Dept queryDeptById(Integer id);

    Leader updateLeader(Integer leaderId);

    void saveEmp(Emp emp);

    void deleteStaff(Integer id);
}
