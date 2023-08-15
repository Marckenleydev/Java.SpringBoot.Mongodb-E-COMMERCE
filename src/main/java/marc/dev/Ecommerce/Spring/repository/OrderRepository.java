package marc.dev.Ecommerce.Spring.repository;

import marc.dev.Ecommerce.Spring.entities.Order;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, ObjectId> {
    Order findOrderByUserId(String userId);
}
