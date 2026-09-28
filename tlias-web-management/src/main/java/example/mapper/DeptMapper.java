package example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import example.pojo.Dept;

@Mapper
public interface DeptMapper {
  /**
   * 查询所有部门
   */
  /*
   * 1). 手动结果映射
   * 
   * @Results({@Result(column = "create_time", property = "createTime"),
   * 
   * @Result(column = "update_time", property = "updateTime")})
   * 
   * @Select("select id, name, create_time, update_time from dept")
   */

  // * 2). 起别名
  // @Select("select id, name, create_time createTime, update_time updateTime from
  // dept")

  // 3). 开启驼峰命名

  /*
   * 在application.yml中做如下配置，开启开关。
   * mybatis:
   * configuration:
   * map-underscore-to-camel-case: true
   */
  @Select("select id, name, create_time, update_time from dept")
  public List<Dept> findAll();

  /**
   * 根据id删除部门
   */
  @Delete("delete from dept where id = #{id}")
  void deleteById(Integer id);

  /**
   * 统计该部门下的员工人数（用于删除前的业务校验）
   */
  @Select("select count(*) from emp where dept_id = #{deptId}")
  Integer countEmpByDeptId(Integer deptId);

  /**
   * 添加部门
   */
  @Insert("insert into dept(name,create_time,update_time) value(#{name},#{createTime},#{updateTime})")
  void insert(Dept dept);

  /**
   * 根据id查询部门
   */
  @Select("select id, name, create_time, update_time from dept where id = #{id}")
  Dept getById(Integer id);

  /**
   * 更新部门
   */
  @Update("update dept set name = #{name},update_time = #{updateTime} where id = #{id}")
  void update(Dept dept);
}