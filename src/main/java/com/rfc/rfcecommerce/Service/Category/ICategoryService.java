package com.rfc.rfcecommerce.Service.Category;

import com.rfc.rfcecommerce.Entity.Category;
import com.rfc.rfcecommerce.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


public interface ICategoryService {
    public Category createCategory(CategoryDto categoryDto);
}