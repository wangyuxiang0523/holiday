package com.fh.service.impl;

import com.fh.dao.DeptDao;
import com.fh.dao.JobDao;
import com.fh.dao.LeaderDao;
import com.fh.dao.StaffDao;
import com.fh.model.*;
import com.fh.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    @Autowired
    private DeptDao deptDao;
    @Autowired
    private JobDao jobDao;
    @Autowired
    private LeaderDao leaderDao;
    @Override
    public PageBean<EmpVo> queryStaffList(PageBean<EmpVo> pageBean) {
        Long total=staffDao.queryStaffCount();
        pageBean.setRecordsFiltered(total);
        pageBean.setRecordsTotal(total);
        List<EmpVo> list=staffDao.queryStuList(pageBean);
        pageBean.setData(list);
        return pageBean;
    }

    @Override
    public List<Job> queryJobList() {
        return jobDao.selectList(null);
    }

    @Override
    public List<Dept> queryDeptList() {
        return deptDao.selectList(null);
    }

    @Override
    public List<Leader> queryLeaderList() {
        return leaderDao.selectList(null);
    }

    @Override
    public List<Emp> queryEmpNameByDept(Integer dept_id) {

        return staffDao.queryEmpNameByDept(dept_id);
    }

    @Override
    public void addEmp(Emp emp) {
        staffDao.insert(emp);
    }

    @Override
    public Emp queryEmpById(Integer id) {
        return staffDao.selectEmpById(id);
    }

    @Override
    public Dept queryDeptById(Integer id) {
        return deptDao.selectById(id);
    }

    @Override
    public Leader updateLeader(Integer leaderId) {
        return leaderDao.selectById(leaderId);
    }

    @Override
    public void saveEmp(Emp emp) {
        staffDao.updateById(emp);
    }
}
