package example.pojo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
//分页结果封装类
@Data 
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
  private Long total; //总记录数
  private List<T> rows;  //当前页数据
}
