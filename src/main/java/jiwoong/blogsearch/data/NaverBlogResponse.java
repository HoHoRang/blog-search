package jiwoong.blogsearch.data;

import java.util.List;

public class NaverBlogResponse {
    private int total;
    private int start;
    private int display;
    private List<NaverDocument> items;

    public int getTotal() {
        return total;
    }

    public int getStart() {
        return start;
    }

    public int getDisplay() {
        return display;
    }

    public List<NaverDocument> getItems() {
        return items;
    }
}
