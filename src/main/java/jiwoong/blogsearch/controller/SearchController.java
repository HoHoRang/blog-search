package jiwoong.blogsearch.controller;

import jiwoong.blogsearch.data.RequestData;
import jiwoong.blogsearch.data.BlogResponse;
import jiwoong.blogsearch.domain.Word;
import jiwoong.blogsearch.error.ResponseError;
import jiwoong.blogsearch.service.KakaoApiService;
import jiwoong.blogsearch.service.NaverApiService;
import jiwoong.blogsearch.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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
    public ResponseEntity<Object> search(
//            @RequestParam(required = false, name = "engine", defaultValue = "kakao") String engine,
            @RequestParam(name = "query") String query,
            @RequestParam(required = false, name = "sort", defaultValue = "accuracy") String sort,
            @RequestParam(required = false, name = "page", defaultValue = "1") Integer page,
            @RequestParam(required = false, name = "size", defaultValue = "10") Integer size
    ) {
        Word word = new Word();
        word.setName(query);
        wordService.search(word);

        RequestData requestData = new RequestData(query, sort, page, size);

        try {
            return new ResponseEntity<>(new BlogResponse<>(requestData, kakaoApiService.useKakaoWebClient(query, sort, page, size)), HttpStatus.OK);
        } catch (Exception ex1) {
            try {
                if (ex1 instanceof IllegalStateException) {
                    if (sort.equals("accuracy")) {
                        sort = "sim";
                    } else if (sort.equals("recency")) {
                        sort = "date";
                    }
                    return new ResponseEntity<>(new BlogResponse<>(requestData, naverApiService.useNaverWebClient(query, sort, page, size)), HttpStatus.OK);
                } else if (ex1 instanceof IllegalArgumentException) {
                    return new ResponseEntity<>(new ResponseError(ex1.getMessage()), HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<>(new ResponseError(ex1.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            } catch (Exception ex2) {
                if (ex2 instanceof IllegalArgumentException) {
                    return new ResponseEntity<>(new ResponseError(ex2.getMessage()), HttpStatus.BAD_REQUEST);
                } else {
                    return new ResponseEntity<>(new ResponseError(ex2.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
                }
            }
        }
    }

    @GetMapping("/search/top-keywords")
    @ResponseBody
    public List<Word> topKeywords() {
        return wordService.findTopWords(10);
    }

}
