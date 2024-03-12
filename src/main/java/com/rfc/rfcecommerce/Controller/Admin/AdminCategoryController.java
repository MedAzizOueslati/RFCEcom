package com.rfc.rfcecommerce.Controller.Admin;

import com.rfc.rfcecommerce.Entity.Category;
import com.rfc.rfcecommerce.Service.admin.Category.CategoryServiceImpl;
import com.rfc.rfcecommerce.dto.CategoryDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/admin")
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
