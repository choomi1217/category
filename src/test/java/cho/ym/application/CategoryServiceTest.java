package cho.ym.application;

import cho.ym.domain.Board;
import cho.ym.domain.Category;
import cho.ym.repository.MemoryBoardRepository;
import cho.ym.repository.MemoryCategoryRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryServiceTest {

    MemoryCategoryRepository memoryCategoryRepository = new MemoryCategoryRepository();
    CategoryService categoryService = new CategoryService(memoryCategoryRepository);

    MemoryBoardRepository memoryBoardRepository = new MemoryBoardRepository();
    BoardService boardService = new BoardService(memoryBoardRepository);

    @BeforeEach
    public void clearMemory(){
        memoryCategoryRepository.clear();
    }

    @Test
    public void createCategory(){
        Category test = Category.builder().name("test").build();
        Category testCategory = categoryService.save(test);
        assertEquals(1L, testCategory.getId());
        assertEquals("test", testCategory.getName());
        assertEquals(0, testCategory.getChildIds().size());
    }

    @Test
    public void insertToParent(){
        Category parent = categoryService.save(Category.builder().name("parent").build());
        Category child = categoryService.save(Category.builder().name("child").build());
        parent.addChild(child);

        Long testChildId = parent.getChildIds().stream().filter(childId -> childId.equals(child.getId())).findFirst().get();
        assertEquals(child.getId(), testChildId);
        assertEquals(child.getParentIds().get(0), 1L);
    }

    @Test
    public void insertToGrandparent(){
        Category grand = categoryService.save(Category.builder().name("grand").build());
        Category parent = categoryService.save(Category.builder().name("parent").build());
        Category child = categoryService.save(Category.builder().name("child").build());

        parent.addChild(child);
        grand.addChild(parent);

        assertEquals(1, grand.getChildIds().size());
        assertEquals(2L, grand.getChildIds().stream().filter(id->id.equals(2L)).findFirst().get());
        assertEquals(3L,parent.getChildIds().stream().filter(id->id.equals(3L)).findFirst().get());
    }

    @Test
    public void findById(){
        setCategories();
        Category category1 = categoryService.findById(1L);
        assertEquals(1L, category1.getId());
    }

    @Test
    public void findByName(){
        setCategories();
        Category category1 = categoryService.findByName("man");
        assertEquals("man", category1.getName());
    }

    @Test
    public void findAllChildById(){
        setCategories();
        List<Category> categories = categoryService.findAllChildById(1L);
        assertEquals(2, categories.size());
    }

    @Test
    public void findAllChildByIdFormatJSON() throws IOException {
        setBoards();
        ObjectMapper mapper = new ObjectMapper();
        String s = mapper.writeValueAsString(categoryService.findAllChildByIdFormatJSON(1L));
        String s2 = mapper.writeValueAsString(categoryService.findAllChildByIdFormatJSON(11L));
        System.out.println(s);
        System.out.println(s2);
    }

    @Test
    public void addBoardToCategory(){
        setCategories();
        Category chen = categoryService.findByName("chen");
        Board board = boardService.save(Board.builder().title("1").build(), chen);
        assertEquals(chen.getBoardIds().stream().filter(id->id.equals(board.getId())).findFirst().get(), 1L);
    }

    private void setCategories(){
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
    private void setBoards() {
        setCategories();
        Category notice = categoryService.findById(3L);
        Category chen = categoryService.findByName("chen");
        Category bakhyeon = categoryService.findByName("bakhyeon");
        Category xiumin = categoryService.findByName("xiumin");
        Category notice2 = categoryService.findById(8L);
        Category unknown = categoryService.findByName("unknown");
        Category view = categoryService.findByName("view");
        Category notice3 = categoryService.findById(13L);
        Category rose = categoryService.findByName("rose");
        Board first = boardService.save(Board.builder().title("1").build(), notice);
        Board second = boardService.save(Board.builder().title("2").build(), chen);
        Board third = boardService.save(Board.builder().title("3").build(), bakhyeon);
        Board fourth = boardService.save(Board.builder().title("4").build(), xiumin);
        Board fifth = boardService.save(Board.builder().title("5").build(), notice2);
        Board sixth = boardService.save(Board.builder().title("6").build(), unknown);
        Board seventh = boardService.save(Board.builder().title("7").build(), view);
        Board eighth = boardService.save(Board.builder().title("8").build(), notice3);
        Board ninth = boardService.save(Board.builder().title("9").build(), rose);
    }

}