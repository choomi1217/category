package cho.ym.application;

import cho.ym.domain.Board;
import cho.ym.domain.Category;
import cho.ym.repository.MemoryBoardRepository;
import cho.ym.repository.MemoryCategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BoardServiceTest {

    MemoryCategoryRepository memoryCategoryRepository = new MemoryCategoryRepository();
    CategoryService categoryService = new CategoryService(memoryCategoryRepository);

    MemoryBoardRepository memoryBoardRepository = new MemoryBoardRepository();
    BoardService boardService = new BoardService(memoryBoardRepository);

    @BeforeEach
    public void setup(){
        category_setting();
    }
    @Test
    public void create_board(){
        Category category = categoryService.findById(3L);
        Board board = boardService.save(Board.builder()
                .title("1")
                .build());
        board.addCategory(category);
        assertEquals(board.getTitle(), "1");
        assertNotNull(board.getCategories().stream().filter(category1 -> category1.getId().equals(3L)).findFirst().get());
    }

    private void set_all(){
        category_setting();
        board_setting();
    }
    private void category_setting(){
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
    private void board_setting() {
        Board first = boardService.save(Board.builder().title("1").build());
        Board second = boardService.save(Board.builder().title("2").build());
        Board third = boardService.save(Board.builder().title("3").build());
        Board fourth = boardService.save(Board.builder().title("4").build());
        Board fifth = boardService.save(Board.builder().title("5").build());
        Board sixth = boardService.save(Board.builder().title("6").build());
        Board seventh = boardService.save(Board.builder().title("7").build());
        Board eighth = boardService.save(Board.builder().title("8").build());
        Board ninth = boardService.save(Board.builder().title("9").build());

        Category notice = categoryService.findById(3L);
        Category chen = categoryService.findByName("chen");
        Category bakhyeon = categoryService.findByName("bakhyeon");
        Category xiumin = categoryService.findByName("xiumin");
        Category notice2 = categoryService.findById(8L);
        Category unknown = categoryService.findByName("unknown");
        Category view = categoryService.findByName("view");
        Category notice3 = categoryService.findById(13L);
        Category rose = categoryService.findByName("rose");

        first.addCategory(notice);
        second.addCategory(chen);
        third.addCategory(bakhyeon);
        fourth.addCategory(xiumin);
        fifth.addCategory(notice2);
        sixth.addCategory(unknown);
        seventh.addCategory(view);
        eighth.addCategory(notice3);
        ninth.addCategory(rose);
    }
}