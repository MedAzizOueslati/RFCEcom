package com.rfc.rfcecommerce.service.client;

import com.rfc.rfcecommerce.entity.Product;
import com.rfc.rfcecommerce.entity.Review;
import com.rfc.rfcecommerce.repository.IProductRepo;
import com.rfc.rfcecommerce.repository.IReviewRepo;
import com.rfc.rfcecommerce.dto.ProductDetailsDto;
import com.rfc.rfcecommerce.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientProductServiceImpl implements IClientProductService{

    private final IProductRepo productRepo;
    private final IReviewRepo reviewRepo;


    public List<ProductDto> getAllProducts(){
        List<Product> products = productRepo.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
    public List<ProductDto> searchAllProductsByName(String name){
        List<Product> products = productRepo.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
    public ProductDetailsDto getProductDetailById(Long productId){
        Optional<Product>   optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isPresent()){
        List<Review> reviewList = reviewRepo.findAllByProductId(productId);

        ProductDetailsDto productDetailDto = new ProductDetailsDto();
        productDetailDto.setProductDto(optionalProduct.get().getDto());
        productDetailDto.setReviewDtoList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));
        return productDetailDto;
        }


        return null;
    }
}
