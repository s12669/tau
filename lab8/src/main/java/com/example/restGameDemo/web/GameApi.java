package com.example.restGameDemo.web;

import com.example.restGameDemo.domain.GameDto;
import com.example.restGameDemo.domain.Game;
import com.example.restGameDemo.domain.ServerResponse;
import com.example.restGameDemo.service.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;
//import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple web api demo -- it only shows how to get some sample data
 *
 * tryout: ()
 *
 * Created by tp on 24.04.17.
 */
@RestController
public class GameApi {
    //private final AtomicLong counter = new AtomicLong();

    @Autowired
    GameManager gameManager;


    @RequestMapping("/")
    public String index() {
        return "This is non rest, just checking if everything works.";
    }

    @RequestMapping(
            value = "/game/{gameTitle}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public GameDto getGame(@PathVariable String gameTitle) {
    	Game game;
    	HttpStatus status;
    	
        try{
        	game = this.gameManager.getGame(new Game(gameTitle,"","","",""));
        	status = HttpStatus.OK;
        	
        }catch(NoSuchElementException e){
        	game = null;
        	status = HttpStatus.NOT_FOUND;
        }
        
    	return new GameDto(game,status);
    	
    }  
    
    @RequestMapping(
            value = "/games",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public List<Game> getAllGames() {
    	
    	return gameManager.getAllGames();
    }
    
    
    @RequestMapping(
            value = "/game/{gameTitle}/delete",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ServerResponse deleteGame(@PathVariable String gameTitle) {
    	
    	try {
			if(gameManager.deleteGame(new Game(gameTitle,"","","","")) == 1) return new ServerResponse(HttpStatus.ACCEPTED, "Game successfuly deleted");
			else return new ServerResponse(HttpStatus.NOT_FOUND, "No game found to delete");
		} catch (SQLException e) {
			return new ServerResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!");
		}
    	
    }
    
    
    @RequestMapping(
            value = "/addGame",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ServerResponse addGame(@RequestBody Game game) {
    	
    	if(gameManager.addGame(game) == 1) return new ServerResponse(HttpStatus.ACCEPTED, "Game successfuly added");
		else return new ServerResponse(HttpStatus.NOT_ACCEPTABLE, "Cannot add that game");
    	
    }
    
    @RequestMapping(
            value = "/updateGame",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ServerResponse updateGame(@RequestBody Game game) {
    	
    	try {
			if(gameManager.updateGame(game)==1)return new ServerResponse(HttpStatus.ACCEPTED, "Game successfuly updated");
			else return new ServerResponse(HttpStatus.NOT_FOUND, "No game found to update");
		} catch (SQLException e) {
			return new ServerResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong!");
		}
    }

}
