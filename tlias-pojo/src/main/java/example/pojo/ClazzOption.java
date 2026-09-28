package example.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *  班级人数统计结果封装
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClazzOption {
    private List clazzList; //班级名称列表
    private List dataList; //班级人数列表
}
