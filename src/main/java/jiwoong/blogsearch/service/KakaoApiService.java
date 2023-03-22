package jiwoong.blogsearch.service;

import jiwoong.blogsearch.data.KakaoBlogResponse;
import jiwoong.blogsearch.error.KakaoError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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
        return kakaoWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", query)
                        .queryParam("sort", Optional.ofNullable(sort))
                        .queryParam("page", Optional.ofNullable(page))
                        .queryParam("size", Optional.ofNullable(size))
                        .build())
                .header("Authorization", "KakaoAK " + kakaoOpenApiAuthorization)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError
                        , clientResponse -> clientResponse.bodyToMono(KakaoError.class)
                                .flatMap(error ->
                                        Mono.error(new IllegalArgumentException(error.getMessage() + ":" + error.getErrorType()))
                                )
                )
                .onStatus(HttpStatusCode::is5xxServerError
                        , clientResponse -> clientResponse.bodyToMono(KakaoError.class)
                                .flatMap(error ->
                                        Mono.error(new IllegalStateException(error.getMessage() + ":" + error.getErrorType()))
                                )
                )
                .bodyToMono(KakaoBlogResponse.class)
                .block();
    }
}
