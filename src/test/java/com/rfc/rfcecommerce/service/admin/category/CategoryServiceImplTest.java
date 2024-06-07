package com.rfc.rfcecommerce.service.admin.category;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.rfc.rfcecommerce.dto.CategoryDto;
import com.rfc.rfcecommerce.entity.Category;
import com.rfc.rfcecommerce.repository.ICategoryRepo;
import com.rfc.rfcecommerce.service.admin.category.CategoryServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;


class CategoryServiceImplTest {


    @Mock
    private ICategoryRepo categoryRepo;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createCategory() {
        // Arrange
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("Electronics");
        categoryDto.setDescription("Devices and gadgets");

        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        when(categoryRepo.save(any(Category.class))).thenReturn(category);

        // Act
        Category createdCategory = categoryService.createCategory(categoryDto);

        // Assert
        assertNotNull(createdCategory);
        assertEquals("Electronics", createdCategory.getName());
        assertEquals("Devices and gadgets", createdCategory.getDescription());
        verify(categoryRepo, times(1)).save(any(Category.class));
    }

    @Test
    void getAllCategories() {
        // Arrange
        Category category1 = new Category();
        category1.setName("Computers");
        category1.setDescription("Devices and gadgets");

        Category category2 = new Category();
        category2.setName("Storage");
        category2.setDescription("Storage serv");

        when(categoryRepo.findAll()).thenReturn(Arrays.asList(category1, category2));

        // Act
        List<Category> categories = categoryService.getAllCategories();

        // Assert
        assertNotNull(categories);
        assertEquals(2, categories.size());
        verify(categoryRepo, times(1)).findAll();
    }
}