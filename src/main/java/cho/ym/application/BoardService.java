 package cho.ym.application;

import cho.ym.domain.Board;
import cho.ym.repository.BoardRepository;
import cho.ym.repository.MemoryBoardRepository;

public class BoardService {
    BoardRepository boardRepository;
    public BoardService(MemoryBoardRepository memoryBoardRepository) {
        boardRepository = memoryBoardRepository;
    }

    public Board save(Board board) {
        return boardRepository.save(board);
    }
}
