package jiwoong.blogsearch.service;

import jiwoong.blogsearch.domain.Word;
import jiwoong.blogsearch.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class WordService {

    private final WordRepository wordRepository;

    @Autowired
    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> findTopWords(Integer limit) {
        return wordRepository.findAll(limit);
    }

    public Optional<Word> findOne(Long id) {
        return wordRepository.findById(id);
    }

    public Word search(Word word) {
        Optional<Word> target = wordRepository.findByName(word.getName());

        if (target.isPresent()) {
            return wordRepository.increaseCount(target.get().getId());
        } else {
            return wordRepository.save(word);
        }
    }
}
