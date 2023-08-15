package marc.dev.Ecommerce.Spring.repository;

import marc.dev.Ecommerce.Spring.entities.Product;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, ObjectId> {
    Product findByCategoryIdAndProductId(String categoryId, ObjectId productId);
    List<Product> findByCategoryId(String categoryId);
     Product findByProductId(String productId);
}
