package cho.ym.application.response;

import java.util.List;

public class CategoryResponse {

    private long id;
    private String name;
    private List<CategoryResponse> children;

    public CategoryResponse() {
    }

    public CategoryResponse(long id, String name, List<CategoryResponse> children) {
        this.id = id;
        this.name = name;
        this.children = children;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<CategoryResponse> getChildren() {
        return children;
    }
}
