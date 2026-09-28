package example.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import example.mapper.EmpMapper;
import example.mapper.StudentMapper;
import example.pojo.ClazzOption;
import example.pojo.JobOption;
import example.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{

  @Autowired
    private EmpMapper empMapper;

  @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
       return empMapper.countEmpGenderData();
    }

    /**
     * 统计学员学历信息
     */
    @Override
    public List<Map> getStudentDegreeData() {
        return studentMapper.countStudentDegreeData();
    }

    /**
     * 统计每个班级的人数
     */
    @Override
    public ClazzOption getStudentCountData() {
        List<Map> list = studentMapper.countStudentCountData();
        List<Object> clazzList = list.stream().map(dataMap -> dataMap.get("name")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("value")).toList();
        return new ClazzOption(clazzList, dataList);
    }
}
