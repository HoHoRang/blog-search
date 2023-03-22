package jiwoong.blogsearch.data;

import java.util.List;
public class KakaoBlogResponse {
    private KakaoMeta meta;
    private List<KakaoDocument> documents;

    public KakaoMeta getMeta() {
        return meta;
    }

    public List<KakaoDocument> getDocuments() {
        return documents;
    }
}
