package example.pojo;

import lombok.Data;

/**
 *  学员分页查询参数
 */
@Data
public class StudentQueryParam {
    private Integer page = 1; //页码
    private Integer pageSize = 10; //每页展示记录数
    private String name; //姓名
    private Integer degree; //学历
    private Integer clazzId; //班级ID
}
