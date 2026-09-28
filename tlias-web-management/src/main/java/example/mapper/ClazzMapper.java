package example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import example.pojo.Clazz;
import example.pojo.ClazzQueryParam;

@Mapper
public interface ClazzMapper {

    /**
     * 条件分页查询班级列表（关联查询班主任姓名）
     */
    public List<Clazz> list(ClazzQueryParam clazzQueryParam);

    /**
     * 查询所有班级
     */
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz order by update_time desc")
    public List<Clazz> listAll();

    /**
     * 新增班级
     */
    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into clazz(name, room, begin_date, end_date, master_id, subject, create_time, update_time) "
            + "values (#{name},#{room},#{beginDate},#{endDate},#{masterId},#{subject},#{createTime},#{updateTime})")
    public void insert(Clazz clazz);

    /**
     * 根据ID查询班级
     */
    @Select("select id, name, room, begin_date, end_date, master_id, subject, create_time, update_time from clazz where id = #{id}")
    public Clazz getById(Integer id);

    /**
     * 根据ID修改班级
     */
    @Update("update clazz set name = #{name}, room = #{room}, begin_date = #{beginDate}, end_date = #{endDate}, "
            + "master_id = #{masterId}, subject = #{subject}, update_time = #{updateTime} where id = #{id}")
    public void updateById(Clazz clazz);

    /**
     * 根据ID删除班级
     */
    @Delete("delete from clazz where id = #{id}")
    public void deleteById(Integer id);

    /**
     * 统计该班级下的学员人数（用于删除前的业务校验）
     */
    @Select("select count(*) from student where clazz_id = #{clazzId}")
    public Integer countStudentByClazzId(Integer clazzId);
}
