package marc.dev.Ecommerce.Spring.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "addresses")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {
    @Id
    private String addressId;
    private String userId;
    private String city;
    private String postalCode;
    private String country;


}
