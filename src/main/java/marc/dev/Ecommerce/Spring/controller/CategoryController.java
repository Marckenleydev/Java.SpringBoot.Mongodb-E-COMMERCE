package marc.dev.Ecommerce.Spring.controller;

import marc.dev.Ecommerce.Spring.entities.Category;
import marc.dev.Ecommerce.Spring.service.CategoryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){

        Category newCategory = categoryService.createCategory(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);

    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategory(@PathVariable ObjectId categoryId){

        Category category = categoryService.getCategory(categoryId);
        return new ResponseEntity<>(category, HttpStatus.CREATED);

    }
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories(){
        List<Category> categories = categoryService.getAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

}
