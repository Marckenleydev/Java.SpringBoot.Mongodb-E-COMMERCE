package marc.dev.Ecommerce.Spring.repository;



import marc.dev.Ecommerce.Spring.entities.Cart;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CartRepository extends MongoRepository<Cart, ObjectId> {
    Cart findByUserId(String userId);

}
