package com.dragon.service.impl;

import com.dragon.mapper.UserMapper;
import com.dragon.pojo.User;
import com.dragon.service.UserService;
import com.dragon.utils.Md5Util;
import com.dragon.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    public UserMapper userMapper;
    public User findByUserName(String username){
        User u = userMapper.findByUserName(username);
        return u;
    }

    public void register(String username, String password){
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username, md5String);
    }

    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl, id);
    }

    @Override
    public void updatePwd(String newPwd) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
