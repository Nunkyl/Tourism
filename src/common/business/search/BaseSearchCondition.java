package common.business.search;

/**
 * Created by eliza on 26.02.19.
 */
public abstract class BaseSearchCondition {

    protected SortType sortType;

    protected Integer ID;

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public SortType getSortType() {
        return sortType;
    }

    public Integer getID() {
        return ID;
    }
}
