package jiwoong.blogsearch.error;

public class NaverError {
    private String errorMessage;
    private String errorCode;

    public NaverError() {
    }

    public NaverError(String errorMessage, String errorCode) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
