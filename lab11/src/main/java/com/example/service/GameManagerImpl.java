package com.example.service;

import com.example.domain.Developer;
import com.example.domain.Game;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class GameManagerImpl implements GameManager {

    private final SessionFactory sessionFactory;

    @Autowired
    public GameManagerImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addDeveloper(Developer developer) {
        developer.setId(null);
        sessionFactory.getCurrentSession().persist(developer);
        sessionFactory.getCurrentSession().flush();
    }

    @Override
    public void deleteDeveloper(Developer developer) {

        developer = sessionFactory.getCurrentSession().get(Developer.class,
                developer.getId());

        for (Game game : developer.getGames()) {
            game.setDeveloped(false);
            sessionFactory.getCurrentSession().update(game);
        }

        sessionFactory.getCurrentSession().delete(developer);
    }

    @Override
    public Developer findDeveloperByName(String name) {
        return (Developer) sessionFactory.getCurrentSession().getNamedQuery("developer.byName").setString("name", name).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Developer> getAllDevelopers() {
        return (List<Developer>) sessionFactory.getCurrentSession().getNamedQuery("developer.all")
                .list();
    }

    @Override
    public List<Game> getDevelopedGames(Developer developer) {
        developer = sessionFactory.getCurrentSession().get(Developer.class,
                developer.getId());
        return developer.getGames();
    }


    @Override
    public void addGame(Game game) {
        game.setId(null);
        sessionFactory.getCurrentSession().persist(game);
        sessionFactory.getCurrentSession().flush();
    }


    @Override
    public void deleteGame(Game game) {
        for (Developer d : this.getAllDevelopers()) {
            boolean toDel = false;
            for (Game g : d.getGames()) {
                if (Objects.equals(g.getId(), game.getId())) {
                    toDel = true;
                }
            }
            if (toDel) {
                d.getGames().remove(game);
                sessionFactory.getCurrentSession().delete(game);
            }
        }
    }

    @Override
    public void completeDevelopment(Long developerId, Long gameId) {
        Developer d = sessionFactory.getCurrentSession().get(
                Developer.class, developerId);

        Game g = sessionFactory.getCurrentSession()
                .get(Game.class, gameId);
        g.setDeveloped(true);

        boolean dane = true;
        for (Game gg : d.getGames()) {
            if (Objects.equals(gg.getId(), g.getId())) {
                dane = false;
            }
        }
        if (dane) d.getGames().add(g);
    }


    @SuppressWarnings("unchecked")
    @Override
    public List<Game> getAllGames() {
        return sessionFactory.getCurrentSession().getNamedQuery("game.all")
                .list();
    }

    @Override
    public Game findGameByTitle(String title) {
        return (Game) sessionFactory.getCurrentSession().getNamedQuery("game.byTitle").setString("title", title).uniqueResult();
    }

    @Override
    public void clearAllTables() {
        sessionFactory.getCurrentSession().getNamedQuery("developer.clear").executeUpdate();
        sessionFactory.getCurrentSession().getNamedQuery("game.clear").executeUpdate();
        sessionFactory.getCurrentSession().flush();

    }
}