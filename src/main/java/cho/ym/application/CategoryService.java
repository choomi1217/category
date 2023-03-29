package cho.ym.application;

import cho.ym.domain.Category;
import cho.ym.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CategoryService {
    CategoryRepository categoryRepository;
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    public Category findById(long id) {
        return categoryRepository.findById(id);
    }

    public Category findByName(String name) {
        return categoryRepository.findByName(name);
    }

    public List<Category> findAllChildById(long id) {
        return categoryRepository.findAllChildById(id);
    }


    public void findAllChildByIdFormatJSON(long id) {

    }
}
