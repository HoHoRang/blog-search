package jiwoong.blogsearch.service;

import jiwoong.blogsearch.data.KakaoBlogResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

public class KakaoApiService {

    private final WebClient kakaoWebClient;
    @Value("${kakao.openapi.authorization}")
    private String kakaoOpenApiAuthorization;

    @Autowired
    public KakaoApiService(WebClient kakaoWebClient) {
        this.kakaoWebClient = kakaoWebClient;
    }

    public KakaoBlogResponse useKakaoWebClient(String query, String sort, Integer page, Integer size) {
        KakaoBlogResponse result = kakaoWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", query)
                        .queryParam("sort", Optional.ofNullable(sort))
                        .queryParam("page", Optional.ofNullable(page))
                        .queryParam("size", Optional.ofNullable(size))
                        .build())
                .header("Authorization", "KakaoAK " + kakaoOpenApiAuthorization)
                .retrieve()
                .bodyToMono(KakaoBlogResponse.class)
                .block();
        return result;
    }
}
