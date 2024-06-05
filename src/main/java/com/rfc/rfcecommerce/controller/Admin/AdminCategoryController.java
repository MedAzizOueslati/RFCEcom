package com.rfc.rfcecommerce.controller.Admin;

import com.rfc.rfcecommerce.entity.Category;
import com.rfc.rfcecommerce.service.admin.category.CategoryServiceImpl;
import com.rfc.rfcecommerce.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminCategoryController {
    private final CategoryServiceImpl categoryService;
    @PostMapping("category")
    public ResponseEntity<Category> createCategory(@RequestBody CategoryDto categoryDto){
        Category category = categoryService.createCategory(categoryDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(category);

    }

    @GetMapping("allCategory")
    public ResponseEntity<List<Category>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());

    }

}
