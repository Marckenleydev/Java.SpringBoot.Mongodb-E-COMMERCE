package marc.dev.Ecommerce.Spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Document(collection = "orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {
    @Id
    private String orderId;
    private String userId;
    private List<CartItem> productOrders;
    private OrderStatus status;
    private BigDecimal orderTotalPrice;
}
