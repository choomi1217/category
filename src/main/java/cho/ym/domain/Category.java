package cho.ym.domain;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Category {

    private Long id;
    private String name;
    private List<Long> childIds;

    private List<Long> parentIds;

    public void addChild(Category childCategory){
        /*if (childCategory.getChildIds().size() > 0){
            childIds.addAll(childCategory.getChildIds());
        }*/
        childIds.add(childCategory.getId());
        //add parent when add child
        childCategory.addParent(this.id);
    }

    public void addChild(List<Category> childCategories){
        childCategories.stream().forEach(category -> {
            /*if (category.getChildIds().size() > 0){
                childIds.addAll(category.getChildIds());
            }*/
            childIds.add(category.getId());
            category.addParent(this.id);
        });
    }

    public void addBoard(Long id){
        this.childIds.add(id);
    }

    private void addParent(Long parentId){
        this.parentIds.add(parentId) ;
    }
}
