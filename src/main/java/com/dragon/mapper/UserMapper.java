package com.dragon.mapper;

import com.dragon.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUserName(String username);

    @Insert("insert into user(username, password, create_time, update_time)"+
                "values(#{username},#{password},now(),now())")
    void add(String username, String password);

    @Update("update user set nickname=#{nickname}, email=#{email}, update_time=#{updateTime} where id=#{id}")
    void update(User user);

    //sql语句中的#{avatarUrl}中的avatarUrl是从下面的void updateAvatar(String avatarUrl, Integer id);中的avatarUrl获取的
    @Update("update user set user_pic=#{avatarUrl}, update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    @Update("update user set password=#{newPwd}, update_time=now() where id=#{id}")
    void updatePwd(String newPwd, Integer id);
}
