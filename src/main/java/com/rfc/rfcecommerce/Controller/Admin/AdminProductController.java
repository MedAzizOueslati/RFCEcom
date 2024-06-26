package com.rfc.rfcecommerce.Controller.Admin;

import com.rfc.rfcecommerce.Service.admin.product.IProductService;
import com.rfc.rfcecommerce.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminProductController {
    private final IProductService productService;
   @PostMapping("/product")
    public ResponseEntity<ProductDto> addProduct(@ModelAttribute ProductDto productDto) throws IOException {
        ProductDto productDto1 = productService.addProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDto1);
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = productService.getAllProducts();
        return  ResponseEntity.ok(productDtos);
    }
    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>> getAllProductsByName(@PathVariable String name){
        List<ProductDto> productDtos = productService.getAllProductsByName(name);
        return  ResponseEntity.ok(productDtos);
    }
    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long productId){
       boolean deleted = productService.deleteProduct(productId);
       if (deleted){
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }
    @GetMapping("product/{productId}")
    public  ResponseEntity<ProductDto> getProductById(@PathVariable Long productId){
       ProductDto productDto = productService.getProductById(productId);
       if (productDto != null){
           return ResponseEntity.ok(productDto);
       }else {
           return ResponseEntity.notFound().build();
       }
    }
    @PutMapping("product/{productId}")
    public  ResponseEntity<ProductDto> updateProduct(@PathVariable Long productId,@ModelAttribute ProductDto productDto) throws IOException {
       ProductDto updatedProduct = productService.updateProduct(productId,productDto);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);}
            else{
                return ResponseEntity.notFound().build();
            }
        }
    }

