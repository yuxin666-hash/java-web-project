package example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.operation.LogOperation;
import example.pojo.Dept;
import example.pojo.Result;
import example.service.DeptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * 部门管理控制器
 */
@Slf4j
@RequestMapping("/depts")
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 查询部门列表
     */
    // @RequestMapping(value = "/depts", method = RequestMethod.GET) 指定请求方式
    @GetMapping
    public Result list() {
        log.info("查询部门列表");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    /*
     * 删除部门列表
     */

    // 根据ID删除部门 - 简单参数接收: 方式一 (HttpServletRequest)
    /*
     * @DeleteMapping("/depts")
     * public Result delete(HttpServletRequest request) {
     * String idStr = request.getParameter("id");
     * int id = Integer.parseInt(idStr);
     * 
     * System.out.println("根据ID删除部门: " + id);
     * return Result.success();
     * 
     * }
     */

    // 方案二：通过Spring提供的 @RequestParam 注解，将请求参数绑定给方法形参
 /*    @DeleteMapping("/depts")
    public Result delete(@RequestParam("id") Integer deptId) {
        System.out.println("根据ID删除部门: " + deptId);
        return Result.success();
    } */

    //- 方案三：如果请求参数名与形参变量名相同，直接定义方法形参即可接收。（省略@RequestParam）
    // * 根据id删除部门 - delete http://localhost:8080/depts?id=1
    @LogOperation
    @DeleteMapping
    public Result delete(Integer id){
        System.out.println("根据ID删除部门:"+id);
        deptService.deleteById(id);
        return Result.success();
    }

       /*
     * 新增部门列表
     */
    @LogOperation
    @PostMapping
   public Result save(@RequestBody Dept dept){
        //System.out.println("根据ID添加部门:"+dept);
        log.info("新增部门,dept:{}",dept);
        deptService.save(dept);
        return Result.success();
    }

        /*
     * 查询部门列表
     */
    @GetMapping("/{id}")
   public Result getById(@PathVariable Integer id){
        //System.out.println("根据ID查询部门,id:"+id);
        log.info("根据ID查询,id:{}",id);
        Dept dept =deptService.getById(id);
        return Result.success(dept);
    }
    
       /*
     * 修改部门列表
     */
    @LogOperation
    @PutMapping
    public Result update(@RequestBody Dept dept){
        //System.out.println("修改部门,dept+"+dept);
        log.info("修改部门,dept:{}",dept);
        deptService.update(dept);
        return Result.success();
    }

}
