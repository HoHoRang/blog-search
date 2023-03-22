package jiwoong.blogsearch.error;

public class KakaoError {
    private String errorType;
    private String message;

    public KakaoError() {
    }

    public KakaoError(String errorType, String message) {
        this.errorType = errorType;
        this.message = message;
    }

    public String getErrorType() {
        return errorType;
    }

    public String getMessage() {
        return message;
    }
}
