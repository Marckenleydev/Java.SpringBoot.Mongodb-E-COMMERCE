package marc.dev.Ecommerce.Spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;
@Document(collection = "carts")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {
    @Id
    private  String cartId;
    private String userId;
    private List<CartItem> products;
    private Integer quantity;
    private BigDecimal totalPrice;
}
