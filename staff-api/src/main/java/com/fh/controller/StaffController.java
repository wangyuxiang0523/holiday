package com.fh.controller;

import com.fh.model.*;
import com.fh.service.StaffService;
import com.fh.util.RedisPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("staff")
@CrossOrigin(origins = "http://localhost:8071")
public class StaffController {
    @Autowired
    private StaffService staffService;
    @PostMapping("query")
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
        List<Emp> emps = staffService.queryEmpNameByDept(emp.getDeptId());
        for (int i=0;i<emps.size();i++){
            if (emps.get(i).getName() .contains(emp.getName()) ){
                Map<String,Object> map=new HashMap<>();
                map.put("code",300);
                map.put("message","员工名字重复");
                return map;
            }
        }
        Jedis jedis = RedisPool.getJedis();
        Long del = jedis.del("uuid");
        if (del == 0 ){
            Map<String,Object> map=new HashMap<>();
            map.put("code",203);
            map.put("message","勿重复下单");
            return map;
        }else {
            staffService.addEmp(emp);
            Map<String,Object> map=new HashMap<>();
            map.put("code",200);
            map.put("message","添加成功");
            return map;
        }

    }
    @GetMapping("getStaffById")
    public Map<String,Object> getStaffById(Integer id){
        Map<String,Object> map = new HashMap<>();
        Emp emp = staffService.queryEmpById(id);
        map.put("emp",emp);
        List<Dept> depts = staffService.queryDeptList();
        List<Job> jobs = staffService.queryJobList();
        List<Leader> leaders = staffService.queryLeaderList();
        map.put("depts",depts);
        map.put("jobs",jobs);
        map.put("leaders",leaders);
        return map;
    }
    @PostMapping("updateLeader")
    public Leader updateLeader(Integer id){
        Dept dept =staffService.queryDeptById(id);
        Leader leader=staffService.updateLeader(dept.getLeaderId());
        return leader;
    }
    @PostMapping("saveStaff")
    public Map saveStaff(Emp emp){
        Map<String,Object> map = new HashMap<>();
        staffService.saveEmp(emp);
        map.put("code",200);
        map.put("message","修改成功");
        return map;
    }
    @GetMapping("getUUID")
    public Map getUUID(){
        Map<String,Object> map = new HashMap<>();
        UUID uuid = UUID.randomUUID();
        String s = uuid.toString();
        Jedis jedis = RedisPool.getJedis();
        jedis.set("uuid",s);
        RedisPool.returnJedis(jedis);
        map.put("code",200);
        map.put("message","添加成功");
        map.put("data",s);
        return map;
    }
}
