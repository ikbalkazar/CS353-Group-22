package com.bilplay.view;

import io.dropwizard.views.*;
import com.bilplay.model.Game;

import java.util.List;

public class MyLibraryView extends View{

	private List<Game> games;
	String username;
	int firstGame;
	int timePlayed;
	private Game game;

	public MyLibraryView( String username, List<Game> games, int firstGame, int timePlayed, Game game ){
		super( "mylibrary.ftl" );
		this.games = games;
		this.username = username;
		this.firstGame = firstGame;
		this.timePlayed = timePlayed;
		this.game = game;
	}

	public Game getGame() {
		return game;
	}

	public int getTimePlayed(){ return timePlayed; }
	public String getUsername(){
		return username;
	}
	public List<Game> getGames(){
		return games;
	}
	public int getFirstGame(){
		return firstGame;
	}

}
