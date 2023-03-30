package cho.ym.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Board {
    private Long id;
    private String title;

}
