package com.rfc.rfcecommerce.Service.admin.Category;

import com.rfc.rfcecommerce.Entity.*;
import com.rfc.rfcecommerce.Repository.ICartItemRepo;
import com.rfc.rfcecommerce.Repository.ICategoryRepo;
import com.rfc.rfcecommerce.Repository.IOrderRepo;
import com.rfc.rfcecommerce.Repository.IProductRepo;
import com.rfc.rfcecommerce.dto.AddProductToCart;
import com.rfc.rfcecommerce.dto.CategoryDto;
import com.rfc.rfcecommerce.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
