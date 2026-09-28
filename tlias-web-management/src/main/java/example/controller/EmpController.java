package example.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.operation.LogOperation;
import example.pojo.Emp;
import example.pojo.EmpQueryParam;
import example.pojo.PageResult;
import example.pojo.Result;
import example.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//员工管理
@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {
    @Autowired
    private EmpService empService;

    // @GetMapping
    /*
     * public Result page(@RequestParam(defaultValue = "1") Integer page,
     * 
     * @RequestParam(defaultValue = "10") Integer pageSize,
     * String name, Integer gender,
     * 
     * @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
     * 
     * @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
     * log.info("查询请求参数： {}, {}, {}, {}, {}, {}", page, pageSize, name, gender,
     * begin, end);
     * PageResult pageResult = empService.page(page, pageSize);
     * return Result.success(pageResult);
     * }
     */
    // 查询员工
    @GetMapping
    public Result page(EmpQueryParam empQueryParam) {
        log.info("查询请求参数： {}", empQueryParam);
        PageResult pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    // 查询所有员工（用于班主任下拉列表）
    @GetMapping("/list")
    public Result listAll() {
        log.info("查询所有员工");
        List<Emp> empList = empService.listAll();
        return Result.success(empList);
    }

    // 添加员工
    @LogOperation
    @PostMapping
    public Result save(@RequestBody Emp emp) throws Exception {
        log.info("请求参数emp: {}", emp);
        empService.save(emp);
        return Result.success();
    }

    // 批量删除员工
    // 方法一:数组
    /*
     * @DeleteMapping
     * public Result delete(Integer[] ids) {
     * log.info("批量删除部门: ids={} ", Arrays.asList(ids));
     * return Result.success();
     * }
     */
    // 方法二:集合
    @LogOperation
    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids) {
        log.info("批量删除部门: ids={} ", ids);
        empService.deleteByIds(ids);
        return Result.success();
    }

    /**
     * 查询回显
     */
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id) {
        log.info("根据id查询员工的详细信息");
        Emp emp = empService.getInfo(id);
        return Result.success(emp);
    }

    /**
     * 更新员工信息
     */
    @LogOperation
    @PutMapping
    public Result update(@RequestBody Emp emp) {
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }

}
