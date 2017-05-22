package com.example.dbunit.service;

// w oparciu o przyklad J Neumanna, przerobiony przez T Puzniakowskiego

import com.example.dbunit.domain.Game;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GameManager {
    public Connection getConnection();

    public int deleteGame(Game game) throws SQLException;

    public int updateGame(Game game) throws SQLException;

    public void clearGames() throws SQLException;

    public int addGame(Game game);

    public Game getGame(Game game);

    public List<Game> getAllGames();

}
