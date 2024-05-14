package com.rfc.rfcecommerce.Controller.Client;

import com.rfc.rfcecommerce.Service.client.wishlist.IWishlistService;
import com.rfc.rfcecommerce.dto.WishlistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customer")
public class WishlistController {
    private final IWishlistService wishlistService;
@PostMapping("/wishlist")
    private ResponseEntity<?> addProductToWishlist(@RequestBody WishlistDto wishlistDto){
        WishlistDto postedWishlistDto = wishlistService.addProductToWishlist(wishlistDto);
        if (postedWishlistDto == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("something went wrong");
        return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);
    }

    @GetMapping("/wishlist/{userId}")
    public ResponseEntity<List<WishlistDto>> getWishlistByUserId(@PathVariable Long userId ){
        return ResponseEntity.ok(wishlistService.getWishlistByUserId(userId));

    }

}
