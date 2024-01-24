package com.dragon.controller;

import com.dragon.pojo.Article;
import com.dragon.pojo.Result;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping ("/list")
    public Result<String> list() {
        return Result.success("所有文章数据..");
    }

    @PostMapping
    public Result add(Article article){

        return Result.success();
    }

}
