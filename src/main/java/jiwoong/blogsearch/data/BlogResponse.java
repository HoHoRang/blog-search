package jiwoong.blogsearch.data;

public class BlogResponse<T, V> {

    private final T requestData;
    private final V responseData;

    public BlogResponse(T requestData, V responseData) {
        this.requestData = requestData;
        this.responseData = responseData;
    }

    public T getRequestData() {
        return requestData;
    }

    public V getResponseData() {
        return responseData;
    }
}
