package jiwoong.blogsearch.data;

public class BlogResponse<T> {

    private final String engine;
    private final T response;

    public BlogResponse(String engine, T response) {
        this.engine = engine;
        this.response = response;
    }

    public String getEngine() {
        return engine;
    }

    public T getResponse() {
        return response;
    }
}
