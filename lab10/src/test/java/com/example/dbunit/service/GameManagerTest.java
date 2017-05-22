package com.example.dbunit.service;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.example.dbunit.domain.Game;

import java.net.URL;
import java.util.List;


@RunWith(JUnit4.class)
public class GameManagerTest extends DBTestCase {
    GameManager gameManager;

    public GameManagerTest() throws Exception {
        super("GameManagerImpl test");
    }

    protected DatabaseOperation getSetUpOperation() throws Exception {
        return DatabaseOperation.CLEAN_INSERT;
    }

    protected DatabaseOperation getTearDownOperation() throws Exception {
        return DatabaseOperation.NONE;
    }

    /**
     * Gets the default dataset. This dataset will be the initial state of database
     * @return the default dataset
     * @throws Exception when there are errors getting resources
     */
    @Override
    protected IDataSet getDataSet() throws Exception {
        return this.getDataSet("dataset.xml");
    }

    /**
     * Returns dataset for selected resource
     * @param datasetName filename in resources
     * @return flat xml data set
     * @throws Exception when there is a problem with opening dataset
     */
    protected IDataSet getDataSet(String datasetName) throws Exception {
        URL url = getClass().getClassLoader().getResource(datasetName);
        FlatXmlDataSet ret = new FlatXmlDataSetBuilder().build(url.openStream());
        return ret;
    }

    @After
    public void tearDown() throws Exception {
        super.tearDown();
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        gameManager = new GameManagerImpl(this.getConnection().getConnection());
    }

    @Test
    public void addGameTest() throws Exception {
        Game game = new Game();
        game.setTitle("Gra10");
        game.setDirectorName("Spock10");
        game.setDirectorSurname("Spoko10");
        game.setCompany("Kompania10");
        game.setPrice("10.00");

        assertEquals(1, gameManager.addGame(game));

        // Data verification

        IDataSet dbDataSet = this.getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("GAME");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});
        IDataSet expectedDataSet = getDataSet("dataset-add-check.xml");
        ITable expectedTable = expectedDataSet.getTable("GAME");

        Assertion.assertEquals(expectedTable, filteredTable);
    }


    @Test
    public void deleteGameTest() throws Exception{

        assertEquals(1,gameManager.deleteGame(new Game("Gra1","","","","")));

        IDataSet dbDataSet = this.getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("GAME");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});
        IDataSet expectedDataSet = getDataSet("dataset-delete-check.xml");
        ITable expectedTable = expectedDataSet.getTable("GAME");

        Assertion.assertEquals(expectedTable, filteredTable);
    }

    @Test
    public void getGameTest() throws Exception{

        Game gameRetrieved = gameManager.getGame(new Game("Gra1","","","",""));

        IDataSet expectedDataSet = getDataSet("dataset-get-check.xml");
        ITable expectedTable = expectedDataSet.getTable("GAME");

        assertEquals(expectedTable.getValue(0, "title"), gameRetrieved.getTitle());
    }

    @Test
    public void getAllGamesTest() throws Exception{

        List<Game> games = gameManager.getAllGames();

        IDataSet expectedDataSet = getDataSet("dataset.xml");
        ITable expectedTable = expectedDataSet.getTable("GAME");

        assertEquals(expectedTable.getRowCount(), games.size());

    }

    @Test
    public void updateGameTest() throws Exception{

        Game gameupdate = new Game("Gra6", "Spock23", "Spoko23","Kompania23", "23.00");
        assertEquals(1,gameManager.updateGame(gameupdate));

        IDataSet dbDataSet = this.getConnection().createDataSet();
        ITable actualTable = dbDataSet.getTable("GAME");
        ITable filteredTable = DefaultColumnFilter.excludedColumnsTable
                (actualTable, new String[]{"ID"});
        IDataSet expectedDataSet = getDataSet("dataset-update-check.xml");
        ITable expectedTable = expectedDataSet.getTable("GAME");

        Assertion.assertEquals(expectedTable, filteredTable);

    }


}
