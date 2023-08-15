package marc.dev.Ecommerce.Spring.service;

import lombok.Data;
import marc.dev.Ecommerce.Spring.entities.Product;
import marc.dev.Ecommerce.Spring.exception.AuthenticationException;
import marc.dev.Ecommerce.Spring.repository.ProductRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Product createProduct(String categoryId,Product product){

        product.setCategoryId(categoryId);
        return productRepository.insert(product);
    }

    public Product getProduct( ObjectId productId){
       return productRepository.findById(productId).orElse(null);

    }
    public String DeleteProduct(ObjectId productId){
        Product product = productRepository.findById(productId).orElse(null);
        if(product != null){
            productRepository.deleteById(productId);
            return "The product has been deleted";
        }
        throw new AuthenticationException("This product does not exist");

    }

    public List<Product> getProductByCategory(String categoryId) {
        List<Product> products = productRepository.findByCategoryId(categoryId); // Use findByCategoryId here
        if (products != null && !products.isEmpty()) { // Check if the list is not empty
            return products;
        } else {
            throw new AuthenticationException("This Category has no product");
        }
    }


    public List<Product> getAllProducts(){
        return productRepository.findAll();

    }
}
