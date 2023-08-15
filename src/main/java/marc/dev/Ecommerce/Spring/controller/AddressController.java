package marc.dev.Ecommerce.Spring.controller;

import jakarta.servlet.http.HttpServletRequest;
import marc.dev.Ecommerce.Spring.entities.Address;
import marc.dev.Ecommerce.Spring.service.AddressService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
public class AddressController {
    @Autowired
    AddressService addressService;


    @PostMapping("{userId}/address")

    public ResponseEntity<Address> createAddress(HttpServletRequest request,@PathVariable String userId, @RequestBody Address address){

//        String userId = (String) request.getAttribute("userId");

        Address userAddress = addressService.createAddress(userId,address);
        return new ResponseEntity<>(userAddress, HttpStatus.CREATED);

    }
    @GetMapping("{userId}/address/{addressId}")
    public ResponseEntity<Address> getAddress(@PathVariable String userId,
                                              @PathVariable ObjectId addressId){
        System.out.println(userId);
        Address userAddress = addressService.getAddress(userId, addressId);
        return new ResponseEntity<>(userAddress, HttpStatus.CREATED);
    }

    @PutMapping("{userId}/address/{addressId}")
    public  ResponseEntity<Address> updateAddress(@PathVariable String userId,
                                                  @PathVariable ObjectId addressId,
                                                  @RequestBody Address addressUpdate){
        Address address = addressService.updateAddress(userId,addressId,addressUpdate);
        return new ResponseEntity<>(address, HttpStatus.OK);
    }

}
