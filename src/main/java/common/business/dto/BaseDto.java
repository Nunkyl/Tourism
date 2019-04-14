package common.business.dto;

import java.io.Serializable;

/**
 * Created by eliza on 14.04.19.
 */
public abstract class BaseDto<ID> implements Serializable {
    protected ID id;

    public ID getID() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }
}