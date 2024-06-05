package com.rfc.rfcecommerce.service.client.review;

import com.rfc.rfcecommerce.entity.*;
import com.rfc.rfcecommerce.repository.IOrderRepo;
import com.rfc.rfcecommerce.repository.IProductRepo;
import com.rfc.rfcecommerce.repository.IReviewRepo;
import com.rfc.rfcecommerce.repository.IUserRepo;
import com.rfc.rfcecommerce.dto.OrderProductResponseDto;
import com.rfc.rfcecommerce.dto.ProductDto;
import com.rfc.rfcecommerce.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements IReviewService {
    private final IOrderRepo orderRepo;
    private final IProductRepo productRepo;
    private final IUserRepo userRepo;
    private final IReviewRepo reviewRepo;
    public OrderProductResponseDto getOrderedResponseDto(Long orderId){
        Optional<Order> optionalOrder = orderRepo.findById(orderId);
        OrderProductResponseDto orderedProductResponseDto = new OrderProductResponseDto();
        if (optionalOrder.isPresent()){
            orderedProductResponseDto.setOrderAmount(optionalOrder.get().getAmount());
            List<ProductDto> productDtoList = new ArrayList<>();
            for (CartItems cartItems: optionalOrder.get().getCartItems()){
                ProductDto productDto = new ProductDto();
                productDto.setId(cartItems.getProduct().getId());
                productDto.setName(cartItems.getProduct().getName());
                productDto.setPrice(cartItems.getPrice());
                productDto.setQuantity(cartItems.getQuantity());

                productDto.setByteImg(cartItems.getProduct().getImg());
                productDtoList.add(productDto);


            }
            orderedProductResponseDto.setProductDtoList(productDtoList);
        }
        return orderedProductResponseDto;

    }
    public ReviewDto giveReview(ReviewDto reviewDto) throws IOException {
        Optional<Product> optionalProduct = productRepo.findById(reviewDto.getProductId());
        Optional<User> optionalUser = userRepo.findById(reviewDto.getUserId());

        if(optionalProduct.isPresent() && optionalUser.isPresent()){
            Review review =new Review();

            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setProduct(optionalProduct.get());
            review.setUser(optionalUser.get());
            review.setImg(reviewDto.getImg().getBytes());

            return reviewRepo.save(review).getDto();

        }
        return null;
    }
}
