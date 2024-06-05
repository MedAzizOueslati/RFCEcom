package com.rfc.rfcecommerce.service.client.wishlist;

import com.rfc.rfcecommerce.dto.WishlistDto;

import java.util.List;

public interface IWishlistService {
    public WishlistDto addProductToWishlist(WishlistDto wishlistDto);
    public List<WishlistDto> getWishlistByUserId(Long userId);


    }
