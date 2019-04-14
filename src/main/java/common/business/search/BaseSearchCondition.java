package common.business.search;

/**
 * Created by eliza on 26.02.19.
 */
public abstract class BaseSearchCondition <ID> {

    protected ID ID;
    protected SortDirection sortDirection;
    protected SortType sortType;
    protected Paginator paginator;

    public void setID(ID ID) {
        this.ID = ID;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }

    public void setSortType(SortType sortType) {
        this.sortType = sortType;
    }

    public void setPaginator(Paginator paginator) {
        this.paginator = paginator;
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

    public Paginator getPaginator() {
        return paginator;
    }

    public boolean needSorting() {
        return sortDirection != null && sortType != null;
    }

    public boolean needPaginator() {
        return paginator != null && paginator.getLimit() > 0 && paginator.getOffset() >= 0;
    }
}
