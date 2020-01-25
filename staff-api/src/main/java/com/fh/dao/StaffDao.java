package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Emp;
import com.fh.model.EmpQuery;
import com.fh.model.EmpVo;
import com.fh.model.PageBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaffDao extends BaseMapper<Emp> {
    Long queryStaffCount(@Param("emp") EmpQuery empQuery);

    List<EmpVo> queryStuList(@Param("pageBean") PageBean<EmpVo> pageBean,@Param("emp") EmpQuery empQuery);

    List<Emp> queryEmpNameByDept( Integer dept_id);

    Emp selectEmpById(Integer id);

    List<EmpVo> queryExcelList(@Param("emp") EmpQuery empQuery);
}
