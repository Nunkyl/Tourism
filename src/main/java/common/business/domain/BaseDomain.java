package common.business.domain;

/**
 * Created by eliza on 26.02.19.
 */
public abstract class BaseDomain {

    protected Integer ID;

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getID() {
        return ID;
    }
}
