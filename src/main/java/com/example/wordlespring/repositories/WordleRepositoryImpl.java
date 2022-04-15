package com.example.wordlespring.repositories;

import com.example.wordlespring.exceptions.EntityNotFoundException;
import com.example.wordlespring.models.Wordle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WordleRepositoryImpl implements WordleRepository {

    private final SessionFactory sessionFactory;

    @Autowired
    public WordleRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Wordle getById(int id) {
        try (Session session = sessionFactory.openSession()) {
            Wordle wordle = session.get(Wordle.class, id);
            if (wordle == null) {
                throw new EntityNotFoundException("Wordle", id);
            }
            return wordle;
        }
    }
}