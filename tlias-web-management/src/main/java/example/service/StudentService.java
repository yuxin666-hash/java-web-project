package example.service;

import java.util.List;

import example.pojo.PageResult;
import example.pojo.Student;
import example.pojo.StudentQueryParam;

public interface StudentService {

    /**
     * 条件分页查询学员列表
     */
    PageResult<Student> page(StudentQueryParam studentQueryParam);

    /**
     * 新增学员
     */
    void save(Student student);

    /**
     * 根据ID查询学员
     */
    Student getById(Integer id);

    /**
     * 修改学员
     */
    void update(Student student);

    /**
     * 根据ID批量删除学员
     */
    void deleteByIds(List<Integer> ids);

    /**
     * 违纪处理
     */
    void violationHandle(Integer id, Integer score);
}
