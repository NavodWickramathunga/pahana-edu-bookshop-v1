package com.pahanaedu.billingsystem.service;

import com.pahanaedu.billingsystem.model.Cart;
import com.pahanaedu.billingsystem.model.CartItem;
import com.pahanaedu.billingsystem.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart getCartByUserId(Long userId) {
        return cartRepository.findById(userId).orElse(new Cart(userId));
    }

    public Cart addItemToCart(Long userId, CartItem item) {
        Cart cart = getCartByUserId(userId);
        cart.getItems().add(item);
        return cartRepository.save(cart);
    }

    public Cart removeItemFromCart(Long userId, Long itemId) {
        Cart cart = getCartByUserId(userId);
        cart.getItems().removeIf(cartItem -> cartItem.getItemId().equals(itemId));
        return cartRepository.save(cart);
    }
}