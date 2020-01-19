package com.fh.service;

import com.fh.model.*;

import java.util.List;

public interface StaffService {
    PageBean<EmpVo> queryStaffList(PageBean<EmpVo> pageBean);

    List<Job> queryJobList();

    List<Dept> queryDeptList();

    List<Leader> queryLeaderList();

    String queryEmpNameByDept(Integer dept_id);

    void addEmp(Emp emp);
}
