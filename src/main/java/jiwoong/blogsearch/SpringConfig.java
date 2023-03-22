package jiwoong.blogsearch;

import jakarta.persistence.EntityManager;
import jiwoong.blogsearch.repository.JpaWordRepository;
import jiwoong.blogsearch.repository.WordRepository;
import jiwoong.blogsearch.service.KakaoApiService;
import jiwoong.blogsearch.service.NaverApiService;
import jiwoong.blogsearch.service.WordService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public WordService wordService() {
        return new WordService(wordRepository());
    }

    @Bean
    public WordRepository wordRepository() {
        return new JpaWordRepository(em);
    }

    @Value("${kakao.openapi.blog.url}")
    private String kakaoOpenApiBlogUrl;

    @Bean
    public WebClient kakaoWebClient() {
        return WebClient.builder()
                .baseUrl(kakaoOpenApiBlogUrl)
                .build();
    }

    @Bean
    public KakaoApiService kakaoApiService() {
        return new KakaoApiService(kakaoWebClient());
    }

    @Value("${naver.openapi.blog.url}")
    private String naverOpenApiBlogUrl;

    @Bean
    public WebClient naverWebClient() {
        return WebClient.builder()
                .baseUrl(naverOpenApiBlogUrl)
                .build();
    }

    @Bean
    public NaverApiService naverApiService() {
        return new NaverApiService(naverWebClient());
    }

}
