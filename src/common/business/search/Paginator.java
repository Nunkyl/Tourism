package common.business.search;

import common.business.application.ApplicationConfigurations;

/**
 * Created by eliza on 26.03.19.
 */
public class Paginator {

    private int limit = ApplicationConfigurations.PAGE_SIZE;
    private int offset;

    public Paginator() {
    }

    public Paginator(int offset) {
        this.offset = offset;
    }

    public Paginator(int limit, int offset) {

        this.limit = limit;
        this.offset = offset;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public int getOffset() {
        return offset;
    }
}
