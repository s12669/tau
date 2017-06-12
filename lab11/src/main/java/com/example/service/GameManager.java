package com.example.service;

// w oparciu o przyklad J Neumanna, przerobiony przez T Puzniakowskiego

import com.example.domain.Developer;
import com.example.domain.Game;

import java.util.List;

public interface GameManager {

    void deleteGame(Game game);

    void updateGame(Game game);

    void addGame(Game game);

    List<Game> getAllGames();

    void completeDevelopment(Long developerId, Long gameId);

    Game findGameByTitle(String title);

    List<Game> getGamesDeveloped();

    void clearAllTables();


    void addDeveloper(Developer developer);

    void updateDeveloper(Developer developer);

    void deleteDeveloper(Developer developer);

    Developer findDeveloperByName(String name);

    List<Developer> getAllDevelopers();

    List<Game> getDevelopedGames(Developer developer);
}
