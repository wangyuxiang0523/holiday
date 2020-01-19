package com.fh.controller;

import com.fh.model.*;
import com.fh.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("staff")
@CrossOrigin(maxAge = 3600)
public class StaffController {
    @Autowired
    private StaffService staffService;
    @GetMapping
    public PageBean<EmpVo> queryStuList(PageBean<EmpVo> pageBean){
        pageBean=  staffService.queryStaffList(pageBean);
        return pageBean;
    }
    @GetMapping("toAdd")
    public Map<String,Object> toAdd(){
        Map<String,Object>map=new HashMap<>();
        List<Job> job=staffService.queryJobList();
        List<Dept>brand=staffService.queryDeptList();
        List<Leader> leaders =staffService.queryLeaderList();
        map.put("job",job);
        map.put("dept",brand);
        map.put("leader",leaders);
        return map;
    }
    @PostMapping
    public Map<String,Object> addEmp(Emp emp){
        String name =staffService.queryEmpNameByDept(emp.getDept_id());
        if (name != null){
            Map<String,Object> map=new HashMap<>();
            map.put("code",300);
            map.put("message","员工名字重复");
            return map;
        }else {
            staffService.addEmp(emp);
            Map<String,Object> map=new HashMap<>();
            map.put("code",200);
            map.put("message","添加成功");
            return map;
        }

    }
}
