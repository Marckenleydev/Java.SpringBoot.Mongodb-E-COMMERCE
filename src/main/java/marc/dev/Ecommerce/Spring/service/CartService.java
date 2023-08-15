package marc.dev.Ecommerce.Spring.service;

import lombok.Data;

import marc.dev.Ecommerce.Spring.entities.Cart;
import marc.dev.Ecommerce.Spring.entities.CartItem;
import marc.dev.Ecommerce.Spring.entities.Product;
import marc.dev.Ecommerce.Spring.repository.CartRepository;
import marc.dev.Ecommerce.Spring.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ProductRepository productRepository;
    public Cart AddtoCart(String userId,String productId,Integer quantity){

        Cart userCart = cartRepository.findByUserId(userId);
        if(userCart == null){
            userCart = new Cart();
            userCart.setUserId(userId);
            userCart.setProducts(new ArrayList<>());

        }
        List<CartItem> products = userCart.getProducts();
        boolean productExist = false;

        for(CartItem item : products){
            if(item.getProductId().equals(productId)){
                item.setQuantity(item.getQuantity() + quantity);
                productExist = true;
                break;
            }
        }
        if(!productExist){

            CartItem newCartItem = new CartItem();
            newCartItem.setProductId(productId);
            newCartItem.setQuantity(quantity);
            products.add(newCartItem);
        }

        // Calculate total price and update it in the cart
        BigDecimal totalPrice = calculateTotalPrice(products);
        userCart.setTotalPrice(totalPrice);
        return cartRepository.save(userCart);
    }

    public Cart removeProdFromCart(String userId, String productId){
        Cart userCart = cartRepository.findByUserId(userId);
//        System.out.println("userCart: " + userCart);


        if(userCart != null){

            List<CartItem> products = userCart.getProducts();
            products.removeIf(item -> item.getProductId().equals(productId));


//            System.out.println( productId);
            for (CartItem item : products) {
                System.out.println("Comparing " + item.getProductId() + " with " + productId);
                if (item.getProductId().equals(productId)) {
                    System.out.println("Match found!");
                }
            }



            // Recalculate total price and update it in the cart
            BigDecimal totalPrice = calculateTotalPrice(products);
            userCart.setTotalPrice(totalPrice);

            return  cartRepository.save(userCart);
        }
        return null;
    }

    public Cart getCart(ObjectId cartId){
        Cart userCart = cartRepository.findById(cartId).orElse(null);
        return userCart;


    }

    private  BigDecimal calculateTotalPrice(List<CartItem> cartItems){
        BigDecimal total = BigDecimal.ZERO;
        for (CartItem cartItem : cartItems){
            Product product = productRepository.findByProductId(cartItem.getProductId());

            if(product != null){
                BigDecimal productPrice = product.getPrice();
                Integer quantity = cartItem.getQuantity();
                total = total.add(productPrice.multiply(BigDecimal.valueOf(quantity)));
            }
        }
        return total;
    }



}
