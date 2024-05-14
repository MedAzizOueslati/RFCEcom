package com.rfc.rfcecommerce.Service.admin.product;

import com.rfc.rfcecommerce.Entity.Category;
import com.rfc.rfcecommerce.Entity.Product;
import com.rfc.rfcecommerce.Repository.ICategoryRepo;
import com.rfc.rfcecommerce.Repository.IProductRepo;
import com.rfc.rfcecommerce.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final IProductRepo productRepo;
    private final ICategoryRepo categoryRepo;


    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());

        Category category = categoryRepo.findById(productDto.getCategoryId()).orElseThrow();
        product.setCategory(category);
        return productRepo.save(product).getDto();


    }
    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepo.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
    public List<ProductDto> getAllProductsByName(String name){
        List<Product> products = productRepo.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
    public boolean deleteProduct(Long id){
        Optional<Product> optionalProduct = productRepo.findById(id);
        if(optionalProduct.isPresent()){
            productRepo.deleteById(id);
            return true;

        }
        return false;

    }
    public ProductDto getProductById(Long productId){
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent()){
            return  optionalProduct.get().getDto();
        }
        else {
            return null;
        }
    }
    public ProductDto updateProduct(Long productId,ProductDto productDto) throws IOException {
        Optional<Category> optionalCategory = categoryRepo.findById(productDto.getCategoryId());
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if (optionalProduct.isPresent() && optionalCategory.isPresent()){
            Product product = optionalProduct.get();
            product.setName(product.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setCategory(optionalCategory.get());

            if (productDto.getImg() != null){
                product.setImg(productDto.getImg().getBytes());
            }
            return productRepo.save(product).getDto();
        }else {
            return null;
        }
    }

}
