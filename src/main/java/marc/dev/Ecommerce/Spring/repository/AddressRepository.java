package marc.dev.Ecommerce.Spring.repository;

import marc.dev.Ecommerce.Spring.entities.Address;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AddressRepository extends MongoRepository<Address, ObjectId> {
    Address findByUserIdAndAddressId(String userId,ObjectId addressId);
    Address findByUserId(String userId);
}

