package jiwoong.blogsearch.service;

import jiwoong.blogsearch.data.KakaoBlogResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class KakaoApiServiceTest {

    @Autowired KakaoApiService kakaoApiService;

    @Test
    public void kakaoSuccess() {
        // given
        String query = "검색어";
        String sort = "accuracy";
        int page = 1;
        int size = 10;

        // when
        KakaoBlogResponse response = kakaoApiService.useKakaoWebClient(query, sort, page, size);

        // then
        assertThat(response.getDocuments().size()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(10);
    }

    @Test
    public void kakaoFailWithPage() {
        // given
        String query = "검색어";
        String sort = "accuracy";
        int page = 100;
        int size = 10;

        // when

        // then
        assertThrows(IllegalArgumentException.class,
                () -> kakaoApiService.useKakaoWebClient(query, sort, page, size));
    }

    @Test
    public void kakaoFailWithSize() {
        // given
        String query = "검색어";
        String sort = "accuracy";
        int page = 1;
        int size = 10000;

        // when

        // then
        assertThrows(IllegalArgumentException.class,
                () -> kakaoApiService.useKakaoWebClient(query, sort, page, size));
    }
}