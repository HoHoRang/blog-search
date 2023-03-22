package jiwoong.blogsearch.controller;

import jiwoong.blogsearch.data.BlogResponse;
import jiwoong.blogsearch.domain.Word;
import jiwoong.blogsearch.service.KakaoApiService;
import jiwoong.blogsearch.service.NaverApiService;
import jiwoong.blogsearch.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {

    private final WordService wordService;
    private final KakaoApiService kakaoApiService;
    private final NaverApiService naverApiService;

    @Autowired
    public SearchController(WordService wordService, KakaoApiService kakaoApiService, NaverApiService naverApiService) {
        this.wordService = wordService;
        this.kakaoApiService = kakaoApiService;
        this.naverApiService = naverApiService;
    }

    @GetMapping("/search/list")
    @ResponseBody
    public BlogResponse<Object> search(
            @RequestParam(required = false, name = "engine", defaultValue = "kakao") String engine,
            @RequestParam(name = "query") String query,
            @RequestParam(required = false, name = "sort", defaultValue = "accuracy") String sort,
            @RequestParam(required = false, name = "page", defaultValue = "1") Integer page,
            @RequestParam(required = false, name = "size", defaultValue = "10") Integer size
    ) {
        Word word = new Word();
        word.setName(query);
        word.setCount(1);
        wordService.search(word);

        if (engine.equals("kakao")) {
            return new BlogResponse("kakao", kakaoApiService.useKakaoWebClient(query, sort, page, size));
        } else {
            return new BlogResponse("naver", naverApiService.useNaverWebClient(query, sort, page, size));
        }

    }

    @GetMapping("/search/top-keywords")
    @ResponseBody
    public List<Word> topKeywords() {
        return wordService.findTopWords(10);
    }

}
