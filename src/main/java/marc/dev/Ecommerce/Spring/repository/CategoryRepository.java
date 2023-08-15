package marc.dev.Ecommerce.Spring.repository;

import marc.dev.Ecommerce.Spring.entities.Category;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, ObjectId> {

}
