package jiwoong.blogsearch.repository;

import jiwoong.blogsearch.domain.Word;

import java.util.List;
import java.util.Optional;

public interface WordRepository {
    Word save(Word word);

    Optional<Word> findById(Long id);

    Optional<Word> findByName(String name);

    List<Word> findAll(Integer limit);

    Word increaseCount(Long id);
}
