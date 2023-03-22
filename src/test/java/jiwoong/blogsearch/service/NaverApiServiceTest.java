package jiwoong.blogsearch.service;

import jiwoong.blogsearch.data.NaverBlogResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class NaverApiServiceTest {
    @Autowired
    NaverApiService naverApiService;

    @Test
    public void naverSuccess() {
        // given
        String query = "검색어";
        String sort = "sim";
        int page = 1;
        int size = 10;

        // when
        NaverBlogResponse response = naverApiService.useNaverWebClient(query, sort, page, size);

        // then
        assertThat(response.getItems().size()).isGreaterThanOrEqualTo(0).isLessThanOrEqualTo(10);
    }

    @Test
    public void naverFail() {
        // given
        String query = "검색어";
        String sort = "abcde";
        int page = 1;
        int size = 10;

        // when

        // then
        assertThrows(WebClientResponseException.BadRequest.class,
                () -> naverApiService.useNaverWebClient(query, sort, page, size));
    }
}