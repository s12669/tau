package com.example.service;

import com.example.domain.Developer;
import com.example.domain.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

//import org.junit.Ignore;
//import org.springframework.test.annotation.Commit;


//@Ignore

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/beans.xml"})
@Rollback
//@Commit
@Transactional(transactionManager = "txManager")
public class GameManagerTest {

    @Autowired
    GameManager gameManager;

    private final String NAME_1 = "Bobo";
    private final String DIRNAME_1 = "Spock";
    private final String DIRSURN_1 = "Spocker";
    private final String TITLE_1 = "Diablo III";
    private final String COMPANY_1 = "Blizzard";
    private final String PRICE_1 = "40.00";


    @Before
    public void beforeTests() {
        gameManager.clearAllTables();
    }

    @Test
    public void addDeveloperTest() {

        List<Developer> retrievedDevelopers = gameManager.getAllDevelopers();
        assertEquals(retrievedDevelopers.size(), 0);

        Developer p = new Developer();
        p.setName(NAME_1);
        gameManager.addDeveloper(p);

        Developer retrievedDeveloper = gameManager.findDeveloperByName(NAME_1);

        retrievedDevelopers = gameManager.getAllDevelopers();

        assertEquals(NAME_1, retrievedDeveloper.getName());
        assertEquals(retrievedDevelopers.size(), 1);

    }

    @Test
    public void addGameTest() {

        List<Game> retrievedGames = gameManager.getAllGames();
        assertEquals(retrievedGames.size(), 0);

        Game game = new Game();
        game.setTitle(TITLE_1);
        game.setDirectorName(DIRNAME_1);
        game.setDirectorSurname(DIRSURN_1);
        game.setCompany(COMPANY_1);
        game.setPrice(PRICE_1);
        game.setDeveloped(true);

        gameManager.addGame(game);

        Game retrievedGame = gameManager.findGameByTitle(TITLE_1);
        assertEquals(retrievedGame.getDirectorName(), DIRNAME_1);
        assertEquals(retrievedGame.getCompany(), COMPANY_1);
        assertEquals(retrievedGame.getPrice(), PRICE_1);

    }

    @Test
    public void completeGameDevelopmentTest() {

        List<Developer> retrievedDevelopers = gameManager.getAllDevelopers();
        assertEquals(retrievedDevelopers.size(), 0);
        List<Game> retrievedGames = gameManager.getAllGames();
        assertEquals(retrievedGames.size(), 0);

        Developer d = new Developer();
        d.setName(NAME_1);
        gameManager.addDeveloper(d);
        assertEquals(gameManager.getAllDevelopers().size(), 1);

        Game game = new Game();
        game.setTitle(TITLE_1);
        game.setDirectorName(DIRNAME_1);
        game.setDirectorSurname(DIRSURN_1);
        game.setCompany(COMPANY_1);
        game.setPrice(PRICE_1);
        game.setDeveloped(false);
        gameManager.addGame(game);

        assertEquals(gameManager.getAllGames().size(), 1);
        Game retrievedGame = gameManager.findGameByTitle(TITLE_1);
        Developer retrievedDeveloper = gameManager.findDeveloperByName(NAME_1);

        assertEquals(retrievedGame.getDeveloped(), false);

        gameManager.completeDevelopment(retrievedDeveloper.getId(), retrievedGame.getId());

        List<Game> publishedGames = gameManager.getDevelopedGames(retrievedDeveloper);

        assertEquals(publishedGames.size(), 1);
        assertEquals(publishedGames.get(0).getTitle(), TITLE_1);
        assertEquals(retrievedGame.getDeveloped(), true);

    }

}
