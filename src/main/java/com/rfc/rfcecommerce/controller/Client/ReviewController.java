package com.rfc.rfcecommerce.controller.Client;

import com.rfc.rfcecommerce.service.client.review.IReviewService;
import com.rfc.rfcecommerce.dto.OrderProductResponseDto;
import com.rfc.rfcecommerce.dto.ReviewDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class ReviewController {
    private final IReviewService reviewService;
    @GetMapping("/ordered-products/{orderId}")
    public ResponseEntity<OrderProductResponseDto> getOrderdProductsDetailsByOrderId(@PathVariable Long orderId){
        return ResponseEntity.ok(reviewService.getOrderedResponseDto(orderId));
    }
@PostMapping("/review")
    public ResponseEntity<Object> giveReview(@ModelAttribute ReviewDto reviewDto) throws IOException {
        ReviewDto reviewDto1 = reviewService.giveReview(reviewDto);
        if (reviewDto1==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something Went Wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewDto1);
    }
}
