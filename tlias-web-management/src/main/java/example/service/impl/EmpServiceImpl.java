package example.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import example.mapper.EmpExprMapper;
import example.mapper.EmpMapper;
import example.pojo.Emp;
import example.pojo.EmpExpr;
import example.pojo.EmpQueryParam;
import example.pojo.LoginInfo;
import example.pojo.PageResult;
import example.service.EmpService;
import example.util.JwtUtils;

//员工管理
@Service
public class EmpServiceImpl implements EmpService {
  @Autowired
  private EmpMapper empMapper;
    @Autowired
  private EmpExprMapper empExprMapper;

  /*
   * @Override
   * public PageResult page(Integer page, Integer pageSize, String name, Integer
   * gender, LocalDate begin, LocalDate end) {
   * 
   * //1. 获取总记录数
   * Long total = empMapper.count();
   * 
   * //2. 获取结果列表
   * Integer start = (page - 1) * pageSize;
   * List<Emp> empList = empMapper.list(start, pageSize);
   * 
   * //3. 封装结果
   * return new PageResult(total, empList);
   * 
   * 
   * // 3.2.4 PageHelper分页插件
   * // 1. 设置分页参数
   * PageHelper.startPage(page, pageSize);
   * // 2. 执行查询
   * List<Emp> empList = empMapper.list(name, gender, begin, end);
   * // 3. 封装结果
   * Page<Emp> p = (Page<Emp>) empList;
   * return new PageResult(p.getTotal(), p.getResult());
   * 
   * }
   */

  @Override
  public PageResult page(EmpQueryParam empQueryParam) {
    // 1. 设置PageHelper分页参数
    PageHelper.startPage(empQueryParam.getPage(), empQueryParam.getPageSize());
    // 2. 执行查询
    List<Emp> empList = empMapper.list(empQueryParam);
    // 3. 封装分页结果
    Page<Emp> p = (Page<Emp>) empList;
    return new PageResult(p.getTotal(), p.getResult());
  }

  /**
   * 查询所有员工（用于班主任下拉列表）
   */
  @Override
  public List<Emp> listAll() {
    return empMapper.listAll();
  }

  // 添加员工
  @Transactional(rollbackFor = Exception.class) //添加事务管理
  @Override
  public void save(Emp emp) throws Exception {
    // 1.补全基础属性
    emp.setCreateTime(LocalDateTime.now());
    emp.setUpdateTime(LocalDateTime.now());

    // 2.保存员工基本信息
    empMapper.insert(emp);
    // 3. 保存员工的工作经历信息 - 批量
    Integer empId = emp.getId();
    List<EmpExpr> exprList = emp.getExprList();
    if (!CollectionUtils.isEmpty(exprList)) {
      exprList.forEach(empExpr -> empExpr.setEmpId(empId));
      empExprMapper.insertBatch(exprList);
    }
  }
  //批量删除员工
  @Transactional 
  @Override
  public void deleteByIds(List<Integer> ids) {
    //1.根据id批量删除员工
     empMapper.deleteByIds(ids);
    //2.根据员工id批量删除工作经历
    empExprMapper.deleteByEmpIds(ids);
  }
//根据id查询回显
  @Override
  public Emp getInfo(Integer id) {
    return empMapper.getById(id);
  }
//修改员工信息
@Transactional 
  @Override
  public void update(Emp emp) {
    //1. 根据ID更新员工基本信息
    emp.setUpdateTime(LocalDateTime.now());
    empMapper.updateById(emp);

    //2. 根据员工ID删除员工的工作经历信息 【删除老的】
    empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));

    //3. 新增员工的工作经历数据 【新增新的】
    Integer empId = emp.getId();
    List<EmpExpr> exprList = emp.getExprList();
    if(!CollectionUtils.isEmpty(exprList)){
        exprList.forEach(empExpr -> empExpr.setEmpId(empId));
        empExprMapper.insertBatch(exprList);
    }
  }
/* //登录
@Override
public LoginInfo login(Emp emp) {
   Emp empLogin = empMapper.getUsernameAndPassword(emp);
    if(empLogin != null){
        LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), null);
        return loginInfo;
    }
    return null;
}
 */
// 登录成功，生成JWT令牌并返回
@Override
public LoginInfo login(Emp emp) {
    Emp empLogin = empMapper.getUsernameAndPassword(emp);
    if(empLogin != null){
        //1. 生成JWT令牌
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("id", empLogin.getId());
        dataMap.put("username", empLogin.getUsername());
        
        String jwt = JwtUtils.generateJwt(dataMap);
        LoginInfo loginInfo = new LoginInfo(empLogin.getId(), empLogin.getUsername(), empLogin.getName(), jwt);
        return loginInfo;
    }
    return null;
}
}
