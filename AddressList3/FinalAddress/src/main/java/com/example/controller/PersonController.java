package com.example.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.QueryPageParam;
import com.example.common.Result;
import com.example.entity.Person;
import com.example.service.IPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Han
 * @since 2023-04-20
 */
@RestController
@RequestMapping("/person")
public class PersonController {
    @Autowired
    private IPersonService ipersonService;
    @GetMapping("/list")
    public List<Person> list(){
        return ipersonService.list();
    }

    //新增
    @PostMapping("/save")
    public boolean save(@RequestBody Person person){
        return ipersonService.save(person);
    }
    //修改
    @PostMapping("/mod")
    public boolean mod(@RequestBody Person person){
        return ipersonService.updateById(person);
    }
    //删除
    @GetMapping("/delete")
    public boolean delete(Integer id){
        return ipersonService.removeById(id);
    }

    //模糊查询
    @PostMapping("/listP")
    public Result listP(@RequestBody Person person){
        LambdaQueryWrapper<Person> lambdaQueryWrapper=new LambdaQueryWrapper();//
        if(StringUtils.isNotBlank(person.getName()) && !"null".equals(person.getName())){
            lambdaQueryWrapper.like(Person::getName,person.getName());
        }
        return Result.suc(ipersonService.list(lambdaQueryWrapper));}
}


