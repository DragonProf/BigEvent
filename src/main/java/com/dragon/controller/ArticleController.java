package com.dragon.controller;

import com.dragon.pojo.Article;
import com.dragon.pojo.Result;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/article")
public class ArticleController {
    @GetMapping ("/list")
    public Result<String> list(/*@RequestHeader(name="Authorization") String token, HttpServletResponse response*/) {
/*        //验证token
        try {
            Map<String,Object> claims = JwtUtil.parseToken(token);

        } catch (Exception e) {
            //http响应状态码为401
            response.setStatus(401);
            return Result.error("未登录");
        }*/
        return Result.success("所有文章数据..");
    }

    @PostMapping
    public Result add(Article article){

        return Result.success();
    }

}
