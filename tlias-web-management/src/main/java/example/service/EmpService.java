package example.service;

import java.time.LocalDate;
import java.util.List;

import example.pojo.Emp;
import example.pojo.EmpQueryParam;
import example.pojo.LoginInfo;
import example.pojo.PageResult;

public interface EmpService {
    /*
     * 分页查询
     * 
     * @param page 页码
     * 
     * @param pageSize 每页记录数
     */
    // PageResult page(Integer page, Integer pageSize, String name, Integer gender,
    // LocalDate begin, LocalDate end);

    PageResult page(EmpQueryParam empQueryParam);

    /**
     * 查询所有员工（用于班主任下拉列表）
     */
    List<Emp> listAll();

    /**
     * 添加员工
     * 
     * @param emp
     */
    void save(Emp emp) throws Exception;

    /* 批量删除员工 */
    void deleteByIds(List<Integer> ids);

    /* 根据ID查询员工的详细信息 */
    Emp getInfo(Integer id);

    /**
     * 更新员工信息
     * 
     * @param emp
     */
    void update(Emp emp);

    // 登录
    LoginInfo login(Emp emp);

}