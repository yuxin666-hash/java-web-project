package example.service;

import java.util.List;

import example.pojo.Clazz;
import example.pojo.ClazzQueryParam;
import example.pojo.PageResult;

public interface ClazzService {

    /**
     * 条件分页查询班级列表
     */
    PageResult<Clazz> page(ClazzQueryParam clazzQueryParam);

    /**
     * 查询所有班级
     */
    List<Clazz> listAll();

    /**
     * 新增班级
     */
    void save(Clazz clazz);

    /**
     * 根据ID查询班级
     */
    Clazz getById(Integer id);

    /**
     * 修改班级
     */
    void update(Clazz clazz);

    /**
     * 根据ID删除班级
     */
    void deleteById(Integer id);
}
