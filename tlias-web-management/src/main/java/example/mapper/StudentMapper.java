package example.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import example.pojo.Student;
import example.pojo.StudentQueryParam;

@Mapper
public interface StudentMapper {

    /**
     * 条件分页查询学员列表（关联查询班级名称）
     */
    public List<Student> list(StudentQueryParam studentQueryParam);

    /**
     * 新增学员
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into student(name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time) "
            + "values (#{name},#{no},#{gender},#{phone},#{idCard},#{isCollege},#{address},#{degree},#{graduationDate},#{clazzId},#{violationCount},#{violationScore},#{createTime},#{updateTime})")
    public void insert(Student student);

    /**
     * 根据ID查询学员
     */
    @Select("select id, name, no, gender, phone, id_card, is_college, address, degree, graduation_date, clazz_id, violation_count, violation_score, create_time, update_time from student where id = #{id}")
    public Student getById(Integer id);

    /**
     * 根据ID修改学员
     * 说明：违纪次数与违纪扣分不在此修改，只能通过「违纪处理」接口变更
     */
    public void updateById(Student student);

    /**
     * 根据ID批量删除学员
     */
    public void deleteByIds(List<Integer> ids);

    /**
     * 违纪处理：违纪次数+1，违纪扣分累加
     */
    @Update("update student set violation_count = violation_count + 1, violation_score = violation_score + #{score}, "
            + "update_time = now() where id = #{id}")
    public void violationHandle(@Param("id") Integer id, @Param("score") Integer score);

    /**
     * 统计学员学历信息
     */
    @MapKey("name")
    public List<Map> countStudentDegreeData();

    /**
     * 统计每个班级的人数
     */
    @MapKey("name")
    public List<Map> countStudentCountData();
}
