package com.dragon.controller;

import com.dragon.pojo.Result;
import com.dragon.pojo.User;
import com.dragon.service.UserService;
import com.dragon.utils.JwtUtil;
import com.dragon.utils.Md5Util;
import com.dragon.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {

        User u = userService.findByUserName(username);
        if(u == null) {
            //没有占用
            //注册
            userService.register(username, password);
            return Result.success();
        } else {
            return Result.error("用户名已被占用");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        if(loginUser==null){
            return Result.error("用户名错误");
        }

        //判断密码是否正确 loginUser对象中的password是密文
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword())){
            //登录成功
            Map<String, Object> claims =new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    //@RequestHeader从字段为Authorization的请求头获取token

    @GetMapping("/userInfo")
    public Result<User> userInfo(@RequestHeader(name = "Authorization") String token) {
        //根据用户名查询数据
        /*Map<String, Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");*/
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success("更新成功");
    }

    @PatchMapping ("updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success("更新成功");
    }

    @PatchMapping("/updatePwd")
    public Result updatePass(@RequestBody Map<String, String> params){
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数");
        }
        if (!rePwd.equals(newPwd)){
            return Result.error("两次填写的新密码不一样");
        }
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUserName(username);

        if(!Md5Util.getMD5String(oldPwd).equals(loginUser.getPassword())){
            return Result.error("原密码填写不正确");
        }
        userService.updatePwd(newPwd);
        return Result.success("更新密码成功");
    }

}