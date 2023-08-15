package marc.dev.Ecommerce.Spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Category {
    @Id
    private String categoryId;
    private String name;
    private String description;
}
