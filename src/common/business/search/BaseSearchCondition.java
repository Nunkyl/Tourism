package common.business.search;

/**
 * Created by eliza on 26.02.19.
 */
public abstract class BaseSearchCondition {

    protected Integer ID;

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }
}
