package marc.dev.Ecommerce.Spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import marc.dev.Ecommerce.Spring.entities.Order;
import marc.dev.Ecommerce.Spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping
    public ResponseEntity<Order> createOrder(HttpServletRequest request, @RequestBody Map<String, String> payload){
        String userId = (String) request.getAttribute("userId");
//        String userId = (String) payload.get("userId");
        Order order = orderService.createOrder(userId);
        return new ResponseEntity<>(order, HttpStatus.OK);

    }
}
