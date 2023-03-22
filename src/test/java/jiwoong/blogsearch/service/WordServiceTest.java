package jiwoong.blogsearch.service;

import jiwoong.blogsearch.domain.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class WordServiceTest {
    @Autowired WordService wordService;

    @Test
    public void searchOnce() {
        // given
        Word word = new Word();
        word.setName("검색어");
        word.setCount(1);

        // when
        Word findWord = wordService.search(word);

        // then
        assertThat(word.getName()).isEqualTo(findWord.getName());
        assertThat(word.getCount()).isEqualTo(1);

    }

    @Test
    public void searchMultiple() {
        // given
        Word word = new Word();
        word.setName("검색어1");
        word.setCount(1);

        // when
        wordService.search(word);
        Word findWord = wordService.search(word);

        // then
        assertThat(word.getName()).isEqualTo(findWord.getName());
        assertThat(word.getCount()).isEqualTo(2);

    }

    @Test
    public void findTopWords() {
        // given
        Word word1 = new Word();
        word1.setName("검색어1");
        word1.setCount(1);
        Word word2 = new Word();
        word2.setName("검색어2");
        word2.setCount(1);

        // when
        for (int i = 0; i < 5; i++) {
            wordService.search(word1);
        }
        for (int i = 0; i < 10; i++) {
            wordService.search(word2);
        }

        // then
        List<Word> topWords = wordService.findTopWords(10);

        assertThat(topWords.size()).isEqualTo(2);
        assertThat(topWords.get(0).getName()).isEqualTo("검색어2");
        assertThat(topWords.get(1).getName()).isEqualTo("검색어1");
    }


}
