package marc.dev.Ecommerce.Spring.service;

import lombok.Data;
import marc.dev.Ecommerce.Spring.entities.Cart;
import marc.dev.Ecommerce.Spring.entities.CartItem;
import marc.dev.Ecommerce.Spring.entities.Order;
import marc.dev.Ecommerce.Spring.entities.OrderStatus;
import marc.dev.Ecommerce.Spring.repository.CartRepository;
import marc.dev.Ecommerce.Spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CartRepository cartRepository;
    public Order createOrder(String userId){
        Cart userCart = cartRepository.findByUserId(userId);
        System.out.println(userId);
        List<CartItem> products = userCart.getProducts();
        if(userCart != null && products != null){
            Order newOrder = new Order();
            newOrder.setUserId(userId);
            newOrder.setProductOrders(new ArrayList<>(products));
            newOrder.setStatus(OrderStatus.PROCESSING);
            newOrder.setOrderTotalPrice(userCart.getTotalPrice());

            Order savedOrder = orderRepository.save(newOrder);
            if (savedOrder != null) {
                // Delete the user's cart after a successful order creation
                cartRepository.delete(userCart);
            }

            return savedOrder;




        }
        return null;

    }
}
