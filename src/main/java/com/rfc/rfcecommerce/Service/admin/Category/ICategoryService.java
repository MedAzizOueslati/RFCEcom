package com.rfc.rfcecommerce.Service.admin.Category;

import com.rfc.rfcecommerce.Entity.Category;
import com.rfc.rfcecommerce.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ICategoryService {
    public Category createCategory(CategoryDto categoryDto);
     List<Category> getAllCategories();




}