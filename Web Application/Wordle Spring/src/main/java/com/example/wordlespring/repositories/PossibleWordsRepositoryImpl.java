package com.example.wordlespring.repositories;

import com.example.wordlespring.exceptions.EntityNotFoundException;
import com.example.wordlespring.models.PossibleWords;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PossibleWordsRepositoryImpl implements PossibleWordsRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public PossibleWordsRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<PossibleWords> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("from PossibleWords", PossibleWords.class)
                    .list();
        }
    }

    @Override
    public PossibleWords getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            PossibleWords possibleWords = session.get(PossibleWords.class, id);
            if (possibleWords == null) {
                throw new EntityNotFoundException("PossibleWords", id);
            }
            return possibleWords;
        }
    }

    @Override
    public PossibleWords getByText(String text) {
        try (Session session = sessionFactory.openSession()) {
            Query<PossibleWords> query = session.createQuery("from PossibleWords where text = :text and language =:language", PossibleWords.class);
            query.setParameter("text", text);
            query.setParameter("language", "english");
            List<PossibleWords> result = query.list();
            if (result.size() == 0) {
                throw new EntityNotFoundException("PossibleWords", "text", text);
            }

            return result.get(0);
        }
    }

    @Override
    public PossibleWords getByTextBulgarian(String text) {
        String bulgarian = "bulgarian";
        try (Session session = sessionFactory.openSession()) {
            Query<PossibleWords> query = session.createQuery("from PossibleWords where text = :text and language =:language", PossibleWords.class);
            query.setParameter("text", text);
            query.setParameter("language", bulgarian);
            List<PossibleWords> result = query.list();
            if (result.size() == 0) {
                throw new EntityNotFoundException("PossibleWords", "text", text);
            }

            return result.get(0);
        }
    }
}
