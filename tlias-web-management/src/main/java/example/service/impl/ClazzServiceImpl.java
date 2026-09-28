package example.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import example.exception.BusinessException;
import example.mapper.ClazzMapper;
import example.pojo.Clazz;
import example.pojo.ClazzQueryParam;
import example.pojo.PageResult;
import example.service.ClazzService;

@Service
public class ClazzServiceImpl implements ClazzService {

    @Autowired
    private ClazzMapper clazzMapper;

    /**
     * 条件分页查询班级列表
     */
    @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
        // 1. 设置PageHelper分页参数
        PageHelper.startPage(clazzQueryParam.getPage(), clazzQueryParam.getPageSize());
        // 2. 执行查询
        List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
        // 3. 封装分页结果
        Page<Clazz> p = (Page<Clazz>) clazzList;
        // 4. 计算每个班级的状态（status不是数据库字段，需要根据时间动态计算）
        p.getResult().forEach(this::fillStatus);
        return new PageResult<Clazz>(p.getTotal(), p.getResult());
    }

    /**
     * 查询所有班级
     */
    @Override
    public List<Clazz> listAll() {
        return clazzMapper.listAll();
    }

    /**
     * 新增班级
     */
    @Override
    public void save(Clazz clazz) {
        // 补全基础属性
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.insert(clazz);
    }

    /**
     * 根据ID查询班级
     */
    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    /**
     * 修改班级
     */
    @Override
    public void update(Clazz clazz) {
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.updateById(clazz);
    }

    /**
     * 根据ID删除班级
     */
    @Transactional
    @Override
    public void deleteById(Integer id) {
        // 业务校验：该班级下如果关联的有学生，则不允许删除
        Integer count = clazzMapper.countStudentByClazzId(id);
        if (count != null && count > 0) {
            throw new BusinessException("对不起, 该班级下有学生, 不能直接删除");
        }
        clazzMapper.deleteById(id);
    }

    /**
     * 计算并填充班级状态：未开班 / 在读中 / 已结课
     * 说明：status 不是数据库字段，而是根据当前时间与开课、结课时间比较得出
     */
    private void fillStatus(Clazz clazz) {
        LocalDate now = LocalDate.now();
        if (clazz.getEndDate() != null && now.isAfter(clazz.getEndDate())) {
            clazz.setStatus("已结课");
        } else if (clazz.getBeginDate() != null && now.isBefore(clazz.getBeginDate())) {
            clazz.setStatus("未开班");
        } else {
            clazz.setStatus("在读中");
        }
    }
}
