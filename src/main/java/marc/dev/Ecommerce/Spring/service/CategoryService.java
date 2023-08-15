package marc.dev.Ecommerce.Spring.service;

import lombok.Data;
import marc.dev.Ecommerce.Spring.entities.Category;
import marc.dev.Ecommerce.Spring.repository.CategoryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public  Category createCategory(Category category){
        return categoryRepository.insert(category);

    }

    public  Category getCategory(ObjectId categoryId){
      return  categoryRepository.findById(categoryId).orElse(null);
    }
    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }
}
