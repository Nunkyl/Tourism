package common.business.search;

/**
 * Created by eliza on 26.02.19.
 */
public abstract class BaseSearchCondition <ID> {

    protected ID ID;

    protected SortDirection sortDirection;

    protected SortType sortType;

    public void setID(ID ID) {
        this.ID = ID;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public ID getID() {
        return ID;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public SortType getSortType() {
        return sortType;
    }

    public boolean needOrdering() {
        return sortDirection != null && sortType != null;
    }
}
