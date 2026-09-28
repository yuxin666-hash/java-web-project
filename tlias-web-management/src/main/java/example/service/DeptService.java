package example.service;

import java.util.List;

import example.pojo.Dept;

public interface  DeptService {

  public List<Dept> findAll();

  /**
 * 根据id删除部门
 */
void deleteById(Integer id);


/**
 * 新增部门
 */
 void save(Dept dept);


 /**
 * 查询部门
 */
 Dept getById(Integer id);

 /**
 * 修改部门
 */
  void update(Dept dept);
  
}
