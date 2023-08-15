package marc.dev.Ecommerce.Spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import marc.dev.Ecommerce.Spring.entities.Cart;
import marc.dev.Ecommerce.Spring.service.CartService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
    @Autowired
    CartService cartService;


    @PostMapping
    public ResponseEntity<Cart> addProduct(HttpServletRequest request, @RequestBody Map<String, Object> payload) {
        String userId = (String) request.getAttribute("userId");
        String productId = (String) payload.get("productId");
        Integer quantity = (Integer) payload.get("quantity");

        Cart cart = cartService.AddtoCart(userId, productId, quantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Map<String,String>> removeProduct(HttpServletRequest request,@RequestBody Map<String, String> payload){
        String userId = (String) request.getAttribute("userId");
//        System.out.println("userId: " + userId);
        String productId = payload.get("productId");
        Cart cart = cartService.removeProdFromCart(userId,productId);

        Map<String, String> response = new HashMap<>();
        response.put("message","Product has been deleted from the cart");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/{cartId}")
    public ResponseEntity<Cart> getCart(@PathVariable ObjectId cartId){
        Cart userCart = cartService.getCart(cartId);
        return new ResponseEntity<>(userCart,HttpStatus.OK);
    }
}
