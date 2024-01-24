package com.dragon.controller;

import com.dragon.pojo.Category;
import com.dragon.pojo.Result;
import com.dragon.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody Category category){
        categoryService.add(category);
        return Result.success("添加文章分类成功");
    }
    @GetMapping
    public Result<List<Category>> list(){
        List<Category> list = categoryService.list();
        return Result.success(list);
    }
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    @PutMapping
    public Result update(@RequestBody @Validated Category category){
        categoryService.update(category);
        return Result.success();
    }


}
