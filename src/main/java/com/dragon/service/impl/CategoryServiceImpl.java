package com.dragon.service.impl;

import com.dragon.mapper.CategoryMapper;
import com.dragon.pojo.Category;
import com.dragon.service.CategoryService;
import com.dragon.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public void add(Category category) {
        String categoryName = category.getCategoryName();
        String categoryAlias = category.getCategoryAlias();
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        categoryMapper.add(categoryName,categoryAlias,userId);
    }

    @Override
    public List<Category> list() {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Category> list = categoryMapper.list(userId);
        return list;
    }

    @Override
    public Category findById(Integer id) {
        Category category = categoryMapper.findById(id);
        return category;
    }

    @Override
    public void update(Category category) {
        Integer id = category.getId();
        String categoryName = category.getCategoryName();
        String categoryAlias = category.getCategoryAlias();
        categoryMapper.update(categoryName,categoryAlias,id);
    }
}
