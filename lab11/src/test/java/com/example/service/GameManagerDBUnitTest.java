package com.example.service;

import com.example.domain.Developer;
import com.example.domain.Game;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//import org.springframework.test.annotation.Commit;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@Rollback
//@Commit
@Transactional(transactionManager = "txManager")
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class GameManagerDBUnitTest {


    @Autowired
    GameManager gameManager;

    @Test
    @DatabaseSetup(value = "/fullData.xml")
    @ExpectedDatabase(value = "/completeDevelopmentData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void completeDevelopmentTest() {

        assertEquals(2, gameManager.getAllDevelopers().size());

        Developer retrievedDeveloper = gameManager.findDeveloperByName("Bobo");
        Game retrievedGame = gameManager.findGameByTitle("Unannounced");

        gameManager.completeDevelopment(retrievedDeveloper.getId(), retrievedGame.getId());

        retrievedGame = gameManager.findGameByTitle("Unannounced");
        assertTrue(retrievedGame.getDeveloped());

    }


    @Test
    @DatabaseSetup(value = "/fullData.xml")
    @ExpectedDatabase(value = "/deleteDeveloperData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteDeveloperTest() {

        assertEquals(2, gameManager.getAllDevelopers().size());

        Developer retrievedDeveloper = gameManager.findDeveloperByName("Rusek");
        Game retrievedGame = gameManager.findGameByTitle("Diablo III");

        assertTrue(retrievedGame.getDeveloped());
        gameManager.deleteDeveloper(retrievedDeveloper);

        List<Developer> retrievedDevelopers = gameManager.getAllDevelopers();

        assertEquals(retrievedDevelopers.size(),1);
    }


    @Test
    @DatabaseSetup(value = "/fullData.xml")
    @ExpectedDatabase(value = "/deleteGameData.xml",
            assertionMode = DatabaseAssertionMode.NON_STRICT)
    public void deleteGameTest() {

        assertEquals(2, gameManager.getAllDevelopers().size());

        Game retrievedGame = gameManager.findGameByTitle("Unannounced");

        assertFalse(retrievedGame.getDeveloped());

        gameManager.deleteGame(retrievedGame);

        List<Game> retrievedGames = gameManager.getAllGames();

        assertEquals(retrievedGames.size(),1);

    }
}