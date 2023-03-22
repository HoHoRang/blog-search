package jiwoong.blogsearch.repository;

import jakarta.persistence.EntityManager;
import jiwoong.blogsearch.domain.Word;

import java.util.List;
import java.util.Optional;

public class JpaWordRepository implements WordRepository{
    private final EntityManager em;

    public JpaWordRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Word save(Word word) {
        em.persist(word);
        return word;
    }

    @Override
    public Optional<Word> findById(Long id) {
        Word word = em.find(Word.class, id);
        return Optional.ofNullable(word);
    }

    @Override
    public Optional<Word> findByName(String name) {
        List<Word> result = em.createQuery("select w from Word w where w.name = :name", Word.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Word> findAll(Integer limit) {
        return em.createQuery("select w from Word w order by count desc, id asc", Word.class)
                .setMaxResults(limit)
                .getResultList();
    }

    @Override
    public Word increaseCount(Long id) {
        Word target = em.find(Word.class, id);

        target.setCount(target.getCount()+1);

        em.merge(target);

        return target;
    }
}
