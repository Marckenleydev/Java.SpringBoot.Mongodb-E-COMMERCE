package marc.dev.Ecommerce.Spring.controller;

import marc.dev.Ecommerce.Spring.entities.Product;
import marc.dev.Ecommerce.Spring.service.ProductService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("category/{categoryId}/product")
    public ResponseEntity<Product> createProduct(@PathVariable String categoryId, @RequestBody Product product){

        Product newProduct = productService.createProduct(categoryId,product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);


    }
    @DeleteMapping("product/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable ObjectId productId){
        return new ResponseEntity<>(productService.DeleteProduct(productId), HttpStatus.OK);
    }

    @GetMapping("product/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable ObjectId productId){
        Product product = productService.getProduct(productId);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("product/category/{categoryId}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String categoryId ){
        List<Product> productList = productService.getProductByCategory(categoryId);
        return new ResponseEntity<>(productList, HttpStatus.OK);


    }
    @GetMapping("products")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
