package example.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import example.pojo.EmpExpr;

@Mapper 
public interface EmpExprMapper {
   /**
     * 批量插入员工工作经历信息
     */
    public void insertBatch(List<EmpExpr> exprList);
/* 批量删除员工工作经历 */
   public void deleteByEmpIds(List<Integer> empIds);
}
