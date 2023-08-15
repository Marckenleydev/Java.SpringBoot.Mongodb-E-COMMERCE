package marc.dev.Ecommerce.Spring.service;

import lombok.Data;
import marc.dev.Ecommerce.Spring.entities.Address;
import marc.dev.Ecommerce.Spring.exception.AuthenticationException;
import marc.dev.Ecommerce.Spring.repository.AddressRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public Address createAddress( String userId,Address address){

//        String Id = address.getUserId();
        Address existingAddress = addressRepository.findByUserId(userId);


        if(existingAddress != null){
            throw new AuthenticationException("You already have an address, you can only modify");
        }
        address.setUserId(userId);
        return addressRepository.insert(address);
    }

    public  Address getAddress(String userId, ObjectId addressId){
        Address address = addressRepository.findByUserIdAndAddressId(userId, addressId);
        if (address !=null){
            return address;
        }
        throw new AuthenticationException("Address not found");
    }

    public  Address updateAddress(String userId, ObjectId addressId, Address addressUpdate){
        Address address = addressRepository.findByUserIdAndAddressId(userId, addressId);

        if(address != null ){
            if(addressUpdate.getCity() != null){
                address.setCity(addressUpdate.getCity());
            }
            if(addressUpdate.getPostalCode() != null){
                address.setPostalCode(addressUpdate.getPostalCode());
            }
            if(addressUpdate.getCountry() != null){
                address.setCountry(addressUpdate.getCountry());
            }
            return addressRepository.save(address);
        }
        throw new AuthenticationException("Address not found");
    }
}
