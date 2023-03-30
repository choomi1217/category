package cho.ym.application;

import cho.ym.domain.Category;
import cho.ym.repository.MemoryCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryServiceTest {

    MemoryCategoryRepository memoryCategoryRepository = new MemoryCategoryRepository();
    CategoryService categoryService = new CategoryService(memoryCategoryRepository);

    @AfterEach
    public void clearMemory(){
        memoryCategoryRepository.clear();
    }

    @Test
    public void create_category(){
        Category test = Category.builder().name("test").build();
        Category testCategory = categoryService.save(test);
        assertEquals(1L, testCategory.getId());
        assertEquals("test", testCategory.getName());
        assertEquals(0, testCategory.getChildIds().size());
    }

    @Test
    public void insert_to_parent(){
        Category parent = categoryService.save(Category.builder().name("parent").build());
        Category child = categoryService.save(Category.builder().name("child").build());
        parent.addChild(child);

        Long testChildId = parent.getChildIds().stream().filter(childId -> childId.equals(child.getId())).findFirst().get();
        assertEquals(child.getId(), testChildId);
        assertEquals(child.getParentIds(), 1L);
    }

    @Test
    public void insert_to_grandparent(){
        Category grand = categoryService.save(Category.builder().name("grand").build());
        Category parent = categoryService.save(Category.builder().name("parent").build());
        Category child = categoryService.save(Category.builder().name("child").build());

        parent.addChild(child);
        grand.addChild(parent);

        assertEquals(2, grand.getChildIds().size());
        assertEquals(2L, grand.getChildIds().stream().filter(id->id.equals(2L)).findFirst().get());
        assertEquals(3L, grand.getChildIds().stream().filter(id->id.equals(3L)).findFirst().get());
    }

    @Test
    public void settingExample(){
        Category man = categoryService.save(Category.builder().name("man").build());
        Category exo = categoryService.save(Category.builder().name("exo").build());
        Category notice = categoryService.save(Category.builder().name("notice").build());
        Category chen = categoryService.save(Category.builder().name("chen").build());
        Category bakhyeon = categoryService.save(Category.builder().name("bakhyeon").build());
        Category xiumin = categoryService.save(Category.builder().name("xiumin").build());
        Category bts = categoryService.save(Category.builder().name("bts").build());
        Category notice1 = categoryService.save(Category.builder().name("notice").build());
        Category unknown = categoryService.save(Category.builder().name("unknown").build());
        Category view = categoryService.save(Category.builder().name("view").build());
        Category woman = categoryService.save(Category.builder().name("woman").build());
        Category blackpink = categoryService.save(Category.builder().name("blackpink").build());
        Category notice2 = categoryService.save(Category.builder().name("notice").build());
        Category rose = categoryService.save(Category.builder().name("rose").build());

        exo.addChild(List.of(notice,chen,bakhyeon,xiumin));
        bts.addChild(List.of(notice1,unknown,view));
        blackpink.addChild(List.of(notice2,unknown,rose));

        man.addChild(List.of(exo, bts));
        woman.addChild(blackpink);

        assertEquals(9, man.getChildIds().size());
        assertEquals(4, woman.getChildIds().size());
    }

    @Test
    public void findById(){
        setting();
        Category category1 = categoryService.findById(1L);
        assertEquals(1L, category1.getId());
    }

    @Test
    public void findByName(){
        setting();
        Category category1 = categoryService.findByName("man");
        assertEquals("man", category1.getName());
    }

    @Test
    public void findAllChildById(){
        setting();
        List<Category> categories = categoryService.findAllChildById(1L);
        for (Category category : categories) {
            System.out.println(category.getId() + " : " + category.getName() + " : " + category.getParentIds());
        }
        assertEquals(9, categories.size());
    }

    @Test
    public void findAllChildByIdFormatJSON() throws IOException {
        setting();
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(categoryService.findAllChildByIdFormatJSON(1L));
        System.out.println(s);
    }

    private void setting(){
        Category man = categoryService.save(Category.builder().name("man").build());
        Category exo = categoryService.save(Category.builder().name("exo").build());
        Category notice = categoryService.save(Category.builder().name("notice").build());
        Category chen = categoryService.save(Category.builder().name("chen").build());
        Category bakhyeon = categoryService.save(Category.builder().name("bakhyeon").build());
        Category xiumin = categoryService.save(Category.builder().name("xiumin").build());
        Category bts = categoryService.save(Category.builder().name("bts").build());
        Category notice1 = categoryService.save(Category.builder().name("notice").build());
        Category unknown = categoryService.save(Category.builder().name("unknown").build());
        Category view = categoryService.save(Category.builder().name("view").build());
        Category woman = categoryService.save(Category.builder().name("woman").build());
        Category blackpink = categoryService.save(Category.builder().name("blackpink").build());
        Category notice2 = categoryService.save(Category.builder().name("notice").build());
        Category rose = categoryService.save(Category.builder().name("rose").build());

        exo.addChild(List.of(notice,chen,bakhyeon,xiumin));
        bts.addChild(List.of(notice1,unknown,view));
        blackpink.addChild(List.of(notice2,unknown,rose));

        man.addChild(List.of(exo, bts));
        woman.addChild(blackpink);
    }

}