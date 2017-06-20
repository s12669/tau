package com.example.service;

// w oparciu o przyklad J Neumanna, przerobiony przez T Puzniakowskiego

import com.example.domain.Developer;
import com.example.domain.Game;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@Transactional
public class GameManagerImpl implements GameManager {

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    //Developer stuff 

    @Override
    public void addDeveloper(Developer developer) {
        developer.setId(null);
        sessionFactory.getCurrentSession().persist(developer);
        sessionFactory.getCurrentSession().flush();
    }
    @Override
    public void updateDeveloper(Developer developer) {
        sessionFactory.getCurrentSession().update(developer);
    }

    @Override
    public void deleteDeveloper(Developer developer) {

        developer = (Developer) sessionFactory.getCurrentSession().get(Developer.class,
                developer.getId());

        // lazy loading here
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
        developer = (Developer) sessionFactory.getCurrentSession().get(Developer.class,
                developer.getId());
        // lazy loading here - try this code without (shallow) copying
        //List<Game> games = new ArrayList<Game>(developer.getGames());
        return developer.getGames();
    }


    //Game stuff

    @Override
    public void addGame(Game game) {
        game.setId(null);
        sessionFactory.getCurrentSession().persist(game);
        sessionFactory.getCurrentSession().flush();
    }
    @Override
    public void updateGame(Game game) {
        sessionFactory.getCurrentSession().update(game);
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
        Developer d = (Developer) sessionFactory.getCurrentSession().get(
                Developer.class, developerId);

        Game g = (Game) sessionFactory.getCurrentSession()
                .get(Game.class, gameId);
        g.setDeveloped(true);

//        if (d.getGames() == null) {
//            d.setGames(new ArrayList<Game>());
//        }
//        d.getGames().add(g);
        boolean dane = true;
        for (Game gg : d.getGames()) {
            if (gg.getId() == g.getId()) {
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

    @SuppressWarnings("unchecked")
    @Override
    public List<Game> getGamesDeveloped() {
        return sessionFactory.getCurrentSession().getNamedQuery("game.developed")
                .list();
    }

    @Override
    public void clearAllTables() {
        sessionFactory.getCurrentSession().getNamedQuery("developer.clear").executeUpdate();
        sessionFactory.getCurrentSession().getNamedQuery("game.clear").executeUpdate();

        sessionFactory.getCurrentSession().flush();

    }
}