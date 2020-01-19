package com.fh.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fh.model.Dept;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface DeptDao extends BaseMapper<Dept> {
}
