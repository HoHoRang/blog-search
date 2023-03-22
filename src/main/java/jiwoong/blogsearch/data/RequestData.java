package jiwoong.blogsearch.data;

public class RequestData {

    private final String query;
    private final String sort;
    private final int page;
    private final int size;

    public RequestData( String query, String sort, int page, int size) {
        this.query = query;
        this.sort = sort;
        this.page = page;
        this.size = size;
    }

    public String getQuery() {
        return query;
    }

    public String getSort() {
        return sort;
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }
}
