package jiwoong.blogsearch.data;

import java.util.Date;

public class KakaoDocument {
    private String title;
    private String contents;
    private String url;
    private String blogname;
    private String thumbnail;
    private Date datetime;

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getUrl() {
        return url;
    }

    public String getBlogname() {
        return blogname;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public Date getDatetime() {
        return datetime;
    }
}
