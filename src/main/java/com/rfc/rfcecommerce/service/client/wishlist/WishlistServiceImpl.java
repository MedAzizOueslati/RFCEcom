package com.rfc.rfcecommerce.service.client.wishlist;

import com.rfc.rfcecommerce.entity.Product;
import com.rfc.rfcecommerce.entity.User;
import com.rfc.rfcecommerce.entity.Wishlist;
import com.rfc.rfcecommerce.repository.IProductRepo;
import com.rfc.rfcecommerce.repository.IUserRepo;
import com.rfc.rfcecommerce.repository.IWishlistRepo;
import com.rfc.rfcecommerce.dto.WishlistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements IWishlistService {
    private final IUserRepo userRepo;
    private final IProductRepo productRepo;
    private final IWishlistRepo wishlistRepo;

    public WishlistDto addProductToWishlist(WishlistDto wishlistDto){
        Optional<Product> optionalProduct = productRepo.findById(wishlistDto.getProductId());
        Optional<User> optionalUser = userRepo.findById(wishlistDto.getUserId());

        if (optionalProduct.isPresent() && optionalUser.isPresent()){
            Wishlist wishlist = new Wishlist();
            wishlist.setProduct(optionalProduct.get());
            wishlist.setUser(optionalUser.get());

            return wishlistRepo.save(wishlist).getWishlistDto();
        }
        return null;
    }

    public List<WishlistDto> getWishlistByUserId(Long userId){
        return wishlistRepo.findAllByUserId(userId).stream().map(Wishlist::getWishlistDto).collect(Collectors.toList());   }
}
