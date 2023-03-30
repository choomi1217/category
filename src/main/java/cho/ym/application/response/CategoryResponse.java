package cho.ym.application.response;

import cho.ym.domain.Board;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({"id", "name", "children", "boards"})
public class CategoryResponse {

    private long id;
    private String name;
    private List<CategoryResponse> children;
    private List<Long> boards;

}
