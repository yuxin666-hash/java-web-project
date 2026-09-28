package example.service;

import java.util.List;
import java.util.Map;

import example.pojo.ClazzOption;
import example.pojo.JobOption;

public interface ReportService {
  JobOption getEmpJobData();

  List<Map> getEmpGenderData();

  /**
   * 统计学员学历信息
   */
  List<Map> getStudentDegreeData();

  /**
   * 统计每个班级的人数
   */
  ClazzOption getStudentCountData();
}
