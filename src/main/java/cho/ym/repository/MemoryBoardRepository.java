package cho.ym.repository;

import cho.ym.domain.Board;
import cho.ym.domain.Category;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemoryBoardRepository implements BoardRepository{
    private final Map<Long, Board> memory = new HashMap<Long, Board>();

    private static Long sequence = 0L;

    @Override
    public Board save(Board board) {
        Long id = ++sequence;
        Board saveBoard = Board.builder()
                .id(id)
                .title(board.getTitle())
                .categories(new ArrayList<Category>())
                .build();
        memory.put(id, saveBoard);
        return saveBoard;
    }
}
