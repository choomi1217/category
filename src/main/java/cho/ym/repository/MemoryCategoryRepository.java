package cho.ym.repository;

import cho.ym.domain.Category;

import java.util.*;

public class MemoryCategoryRepository implements CategoryRepository{

    private final Map<Long, Category> memory = new HashMap<Long, Category>();
    private static Long sequence = 0L;
    @Override
    public Category save(Category category) {
        Long id = ++sequence;

        Category savingCategory = Category.builder()
                .id(id)
                .name(category.getName())
                .childIds(new ArrayList<Long>())
                .parentIds(new ArrayList<Long>())
                .boardIds(new ArrayList<Long>())
                .build();

        memory.put(id, savingCategory);
        return savingCategory;
    }

    @Override
    public Category findById(long id) {
        return memory.values()
                .stream()
                .filter(category -> category.getId().equals(id)).findFirst().get();
    }

    @Override
    public Category findByName(String name) {
        return memory.values()
                .stream()
                .filter(category -> category.getName().equals(name)).findFirst().get();
    }

    @Override
    public List<Category> findAllChildById(long id) {
        List<Long> childIds = findById(id).getChildIds();
        List<Category> childCategories = new ArrayList<>();
        childIds.forEach(childId ->{
            childCategories.add(memory.values().stream().filter(category -> category.getId().equals(childId)).findFirst().get());
        });

        Collections.sort(childCategories, new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return Long.compare(o1.getId(), o2.getId());
            }
        });

        return childCategories;
    }

    public void clear() {
        this.memory.clear();
        this.sequence = 0L;
    }
}
