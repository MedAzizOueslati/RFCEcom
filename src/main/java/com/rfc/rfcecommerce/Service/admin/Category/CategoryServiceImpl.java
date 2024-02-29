package com.rfc.rfcecommerce.Service.admin.Category;

import com.rfc.rfcecommerce.Entity.Category;
import com.rfc.rfcecommerce.Repository.ICategoryRepo;
import com.rfc.rfcecommerce.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements ICategoryService {
    private final ICategoryRepo categoryRepo;
    public Category createCategory (CategoryDto categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        return categoryRepo.save(category);
    }
    public List<Category> getAllCategories(){
        return categoryRepo.findAll();
    }
}
