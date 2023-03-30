package cho.ym.repository;

import cho.ym.domain.Category;

import java.util.List;

public interface CategoryRepository {
    Category save(Category category);

    Category findById(long id);

    List<Category> findByName(String name);

    List<Category> findAllChildById(long id);
}
