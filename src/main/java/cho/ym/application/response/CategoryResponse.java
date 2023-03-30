package cho.ym.application.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryResponse {

    private long id;
    private String name;
    private List<CategoryResponse> children;

}
