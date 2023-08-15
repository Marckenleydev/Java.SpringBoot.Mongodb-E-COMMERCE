package marc.dev.Ecommerce.Spring.repository;

import marc.dev.Ecommerce.Spring.entities.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface USerRepository extends MongoRepository<User, ObjectId> {
    User findByEmail(String email);
}
