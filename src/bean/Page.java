package bean;

import java.util.List;

/**
 *
 * @author Administrator
 * @date 2017/12/5
 */
public class Page<T> {

    /**
     * //总页数
     */
    private int totalPage;
    /**
     * // 当前页数
     */
    private int currentPage;

    /**
     * //当前页显示数目
     */
    private int currentCount;
    /**
     * // 总共的数目
     */
    private int totalCount;

    private List<T> list;

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
