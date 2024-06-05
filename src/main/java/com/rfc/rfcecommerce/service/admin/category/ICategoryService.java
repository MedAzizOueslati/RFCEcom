package com.rfc.rfcecommerce.service.admin.category;

import com.rfc.rfcecommerce.entity.Category;
import com.rfc.rfcecommerce.dto.CategoryDto;


import java.util.List;


public interface ICategoryService {
    public Category createCategory(CategoryDto categoryDto);
     List<Category> getAllCategories();




}