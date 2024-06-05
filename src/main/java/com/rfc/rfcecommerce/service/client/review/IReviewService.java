package com.rfc.rfcecommerce.service.client.review;

import com.rfc.rfcecommerce.dto.OrderProductResponseDto;
import com.rfc.rfcecommerce.dto.ReviewDto;

import java.io.IOException;

public interface IReviewService {
    public OrderProductResponseDto getOrderedResponseDto(Long orderId);
    public ReviewDto giveReview(ReviewDto reviewDto) throws IOException ;


    }
