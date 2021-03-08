package org.xkidea.web.util;

import javax.faces.model.DataModel;

public abstract class AbstractPaginationHelper {

    public static final int DEFAULT_SIZE = 10;
    private transient int pageSize;
    private transient int page;

    public AbstractPaginationHelper(int pageSize) {
        this.pageSize = pageSize;
    }

    public abstract int getItemCount();

    public abstract DataModel createPageDataModel();

    public int getPageFirstItem() {
        return page*pageSize;
    }

    public int getPageLastItem() {
        int i = getPageFirstItem() + pageSize - 1;
        int count = getItemCount() - 1;
        if (i > count) {
            i = count;
        }
        if (i < 0) {
            i = 0;
        }
        return i;
    }

    public boolean isHasNextPage(){
        return (page+1)*pageSize+1 <= getItemCount();
    }

    public void nextPage() {
        if (isHasNextPage()) {
            page++;
        }
    }

    public boolean isHasPreviousPage(){
        return page > 0;
    }

    public void previousPage() {
        if (isHasPreviousPage()) {
            page--;
        }
    }

    public int getPageSize() {
        return pageSize;
    }
}
