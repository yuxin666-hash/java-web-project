package example.service.impl;

import example.exception.BusinessException;
import example.mapper.DeptMapper;
import example.pojo.Dept;
import example.service.DeptService;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    // 删
    public void deleteById(Integer id) {
        // 业务校验：该部门下如果有关联的员工，则不允许删除
        Integer count = deptMapper.countEmpByDeptId(id);
        if (count != null && count > 0) {
            throw new BusinessException("对不起，当前部门下有员工，不能直接删除！");
        }
        deptMapper.deleteById(id);
    }

    // 增
    public void save(Dept dept) {
        // 补全基础信息
        dept.setCreateTime(LocalDateTime.now());
        dept.setUpdateTime(LocalDateTime.now());
        // 保存部门
        deptMapper.insert(dept);
    }

    // 查
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    //改
    public void update(Dept dept) {
        // 补全基础信息
        dept.setUpdateTime(LocalDateTime.now());
        // 保存部门
        deptMapper.update(dept);
         }
}
