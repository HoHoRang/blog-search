package jiwoong.blogsearch.repository;

import jiwoong.blogsearch.domain.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class JpaWordRepositoryTest {
    @Autowired WordRepository wordRepository;

    @Test
    void save() {
        // given
        Word word = new Word();
        word.setName("검색어");

        // when
        wordRepository.save(word);

        // then
        Word result = wordRepository.findById(word.getId()).get();
        assertThat(result).isEqualTo(word);
    }

    @Test
    void findByName() {
        // given
        Word word = new Word();
        word.setName("검색어");
        wordRepository.save(word);

        // when
        Word result = wordRepository.findByName("검색어").get();

        // then
        assertThat(result).isEqualTo(word);
    }

    @Test
    void findAll() {
        // given
        Word word1 = new Word();
        word1.setName("검색어1");
        wordRepository.save(word1);
        Word word2 = new Word();
        word2.setName("검색어2");
        wordRepository.save(word2);

        // when
        List<Word> result = wordRepository.findAll(10);

        // then
        assertThat(result.size()).isEqualTo(2);
    }

    @Test
    void increaseCount() {
        // given
        Word word = new Word();

        word.setName("검색어");
        wordRepository.save(word);

        // when
        Word result = wordRepository.increaseCount(word.getId());

        // then
        assertThat(result.getCount()).isEqualTo(2);
    }

}