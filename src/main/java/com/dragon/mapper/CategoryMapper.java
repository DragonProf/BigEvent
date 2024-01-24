package com.dragon.mapper;

import com.dragon.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Insert("insert into category(category_name, category_alias, " +
            "create_user, create_time, update_time) " +
            "values(#{categoryName},#{categoryAlias},#{userId},now(),now())")
    void add(String categoryName, String categoryAlias, Integer userId);

    @Select("select * from category where create_user=#{userId}")
    List<Category> list(Integer userId);

    @Select("select * from category where id=#{id}")
    Category findById(Integer id);

    @Update("update category set category_name=#{categoryName}," +
            " category_alias=#{categoryAlias},update_time=now() where id=#{id}")
    void update(String categoryName, String categoryAlias, Integer id);
}
