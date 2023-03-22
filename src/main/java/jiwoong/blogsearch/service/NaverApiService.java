package jiwoong.blogsearch.service;

import jiwoong.blogsearch.data.NaverBlogResponse;
import jiwoong.blogsearch.error.NaverError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Optional;

public class NaverApiService {
    private final WebClient naverWebClient;
    @Value("${naver.openapi.client.id}")
    private String naverOpenApiClientId;
    @Value("${naver.openapi.client.secret}")
    private String getNaverOpenApiClientId;

    @Autowired
    public NaverApiService(WebClient naverWebClient) {
        this.naverWebClient = naverWebClient;
    }

    public NaverBlogResponse useNaverWebClient(String query, String sort, Integer page, Integer size) {
        return naverWebClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("query", query)
                        .queryParam("sort", Optional.ofNullable(sort))
                        .queryParam("start", Optional.ofNullable(page))
                        .queryParam("display", Optional.ofNullable(size))
                        .build())
                .header("X-Naver-Client-Id", naverOpenApiClientId)
                .header("X-Naver-Client-Secret", getNaverOpenApiClientId)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError
                        , clientResponse -> clientResponse.bodyToMono(NaverError.class)
                                .flatMap(error ->
                                        Mono.error(new IllegalArgumentException(error.getErrorMessage() + ":" + error.getErrorCode()))
                                )
                )
                .onStatus(HttpStatusCode::is5xxServerError
                        , clientResponse -> clientResponse.bodyToMono(NaverError.class)
                                .flatMap(error ->
                                        Mono.error(new IllegalStateException(error.getErrorMessage() + ":" + error.getErrorCode()))
                                )
                )
                .bodyToMono(NaverBlogResponse.class)
                .block();
    }
}
