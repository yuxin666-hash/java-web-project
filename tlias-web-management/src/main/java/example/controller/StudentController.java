package example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.operation.LogOperation;
import example.pojo.PageResult;
import example.pojo.Result;
import example.pojo.Student;
import example.pojo.StudentQueryParam;
import example.service.StudentService;
import lombok.extern.slf4j.Slf4j;

/**
 * 学员管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * 条件分页查询学员列表
     */
    @GetMapping
    public Result page(StudentQueryParam studentQueryParam) {
        log.info("查询学员列表, 请求参数: {}", studentQueryParam);
        PageResult<Student> pageResult = studentService.page(studentQueryParam);
        return Result.success(pageResult);
    }

    /**
     * 新增学员
     */
    @LogOperation
    @PostMapping
    public Result save(@RequestBody Student student) {
        log.info("新增学员, student: {}", student);
        studentService.save(student);
        return Result.success();
    }

    /**
     * 根据ID查询学员
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        log.info("根据ID查询学员, id: {}", id);
        Student student = studentService.getById(id);
        return Result.success(student);
    }

    /**
     * 修改学员
     */
    @LogOperation
    @PutMapping
    public Result update(@RequestBody Student student) {
        log.info("修改学员, student: {}", student);
        studentService.update(student);
        return Result.success();
    }

    /**
     * 根据ID批量删除学员
     * 请求样例: /students/1,2,3
     */
    @LogOperation
    @DeleteMapping("/{ids}")
    public Result delete(@PathVariable List<Integer> ids) {
        log.info("根据ID批量删除学员, ids: {}", ids);
        studentService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 违纪处理
     * 请求样例: /students/violation/1/5
     */
    @LogOperation
    @PutMapping("/violation/{id}/{score}")
    public Result violationHandle(@PathVariable Integer id, @PathVariable Integer score) {
        log.info("违纪处理, id: {}, score: {}", id, score);
        studentService.violationHandle(id, score);
        return Result.success();
    }
}
