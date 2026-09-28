package example.mapper;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import example.pojo.Emp;
import example.pojo.EmpQueryParam;

@Mapper
public interface EmpMapper {
  /**
   * 查询总记录数
   */
  // @Select("select count(*) from emp e left join dept d on e.dept_id = d.id ")
  // public Long count();

  /**
   * 查询所有的员工及其对应的部门名称
   */
  /*
   * @Select("select e.*, d.name deptName from emp as e left join dept as d on e.dept_id = d.id limit #{start}, #{pageSize}"
   * )
   * public List<Emp> list(Integer start , Integer pageSize);
   */

  // 3.2.4 PageHelper分页插件
  // @Select("select e.*, d.name deptName from emp as e left join dept as d on
  // e.dept_id = d.id")
  // public List<Emp> list();
  /**
   * 查询所有的员工及其对应的部门名称
   */
  // public List<Emp> list(String name, Integer gender, LocalDate begin, LocalDate
  // end);

  public List<Emp> list(EmpQueryParam empQueryParam);

  /**
   * 查询所有员工（用于班主任下拉列表）
   */
  public List<Emp> listAll();

  /**
   * 新增员工数据
   */
  @Options(useGeneratedKeys = true, keyProperty = "id")
  @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) "
      +
      "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
  public void insert(Emp emp);

  /* 批量删除员工信息 */
  public void deleteByIds(List<Integer> ids);

  /* * 根据ID查询员工详细信息 */
  public Emp getById(Integer id);

  /* 更新员工基本信息 */
  public void updateById(Emp emp);

  /**
   * 统计各个职位的员工人数
   */
  @MapKey("pos")
  public List<Map<String, Object>> countEmpJobData();

  /**
   * 统计员工性别信息
   */
  @MapKey("name")
  public List<Map> countEmpGenderData();

  /**
   * 根据用户名和密码查询员工信息
   */
  @Select("select * from emp where username = #{username} and password = #{password}")
  Emp getUsernameAndPassword(Emp emp);
}
