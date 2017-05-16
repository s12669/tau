package com.example.restGameDemo.domain;

import org.springframework.http.HttpStatus;

public class GameDto {

	private Game game;
	private HttpStatus status;
	
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	public GameDto(){
		
	}
	
	public GameDto(Game game, HttpStatus status) {
		super();
		this.game = game;
		this.status = status;
	}
	
	
	
	
}
