package com.example.restGameDemo.tests;
// przyklad na podstawie przykladow J. Neumanna

import com.example.restGameDemo.domain.Game;
import com.example.restGameDemo.service.GameManagerImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class GameManagerTest {

	GameManagerImpl gameManager = new GameManagerImpl();

	private final static String TITLE_1 = "Diablo";
	private final static String TITLE_2 = "The Witcher";
	private final static String TITLE_3 = "Borderlands";


	private final static String DIRECTORN_1 = "Spock";
	private final static String DIRECTORN_2 = "Bobo";

	private final static String SURNAME_1 = "Gzda";

	private final static String COMPANY_1 = "Blizzard";

	private final static String PRICE_1 = "59.99";
	private final static String PRICE_2 = "23.42";


	public GameManagerTest() throws SQLException {
	}

	@Before
	@After
	public void cleanup() throws SQLException {
		gameManager.clearGames();
	}

	@Test
	public void connectionTest() {
		assertNotNull(gameManager.getConnection());
	}

	@Test
	public void addGameTest() throws SQLException{
		Game game = new Game(TITLE_1, DIRECTORN_1, SURNAME_1, COMPANY_1, PRICE_1);

		assertEquals(1,gameManager.addGame(game));

		List<Game> games = gameManager.getAllGames();
		Game gameRetrieved = games.get(0);

		assertEquals(TITLE_1, gameRetrieved.getTitle());
		assertEquals(SURNAME_1, gameRetrieved.getDirectorSurname());
	}

	@Test(expected = NoSuchElementException.class)
	public void deleteGameTest() throws SQLException{
		Game game1 = new Game(TITLE_1, DIRECTORN_1, SURNAME_1, COMPANY_1, PRICE_1);
		Game game2 = new Game(TITLE_2, DIRECTORN_2, SURNAME_1,COMPANY_1, PRICE_2);


		assertEquals(1,gameManager.addGame(game1));
		assertEquals(1,gameManager.addGame(game2));

		assertEquals(1,gameManager.deleteGame(game1));

		assertEquals(1,gameManager.getAllGames().size());

		Game gameRetrieved = gameManager.getGame(game1);
	}

	@Test
	public void getGameTest() throws SQLException{
		Game game = new Game(TITLE_1, DIRECTORN_1, SURNAME_1,COMPANY_1, PRICE_1);

		assertEquals(1,gameManager.addGame(game));

		Game gameRetrieved = gameManager.getGame(game);

		assertEquals(TITLE_1, gameRetrieved.getTitle());
		assertEquals(DIRECTORN_1, gameRetrieved.getDirectorName());
		assertEquals(SURNAME_1, gameRetrieved.getDirectorSurname());
		assertEquals(COMPANY_1, gameRetrieved.getCompany());
		assertEquals(PRICE_1, gameRetrieved.getPrice());
	}

	@Test
	public void getAllGamesTest() throws SQLException{
		Game game = new Game(TITLE_1, DIRECTORN_1, SURNAME_1,COMPANY_1, PRICE_1);
		Game game2 = new Game(TITLE_2, DIRECTORN_1, SURNAME_1,COMPANY_1, PRICE_2);

		assertEquals(1,gameManager.addGame(game));
		assertEquals(1,gameManager.addGame(game2));

		List<Game> games = gameManager.getAllGames();

		assertEquals(2, games.size());
		assertEquals(TITLE_1, games.get(0).getTitle());
		assertEquals(TITLE_2, games.get(1).getTitle());
	}

	@Test
	public void updateGameTest() throws SQLException{
		Game game = new Game(TITLE_1, DIRECTORN_1, SURNAME_1,COMPANY_1, PRICE_1);
		Game game2 = new Game(TITLE_3, DIRECTORN_2, SURNAME_1,COMPANY_1, PRICE_2);

		assertEquals(1,gameManager.addGame(game));
		assertEquals(1,gameManager.addGame(game2));

		Game gameupdate = new Game(TITLE_1, DIRECTORN_1, SURNAME_1,COMPANY_1, PRICE_2);
		assertEquals(1,gameManager.updateGame(gameupdate));

		Game gameRetrieved = gameManager.getGame(game);
		Game gameRetrieved2 = gameManager.getGame(game2);

		assertEquals(TITLE_1, gameRetrieved.getTitle());
		assertEquals(TITLE_3, gameRetrieved2.getTitle());
		assertEquals(PRICE_2, gameRetrieved.getPrice());

	}

}
