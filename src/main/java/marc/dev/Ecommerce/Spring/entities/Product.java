package marc.dev.Ecommerce.Spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "products")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {
    @Id
    private String productId;
    private String name;
    private String description;
    private BigDecimal price;
    private String categoryId;
}
