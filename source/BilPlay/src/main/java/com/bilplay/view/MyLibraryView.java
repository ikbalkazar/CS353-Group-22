package com.bilplay.view;

import io.dropwizard.views.View;
import com.bilplay.model.Game;

import java.util.List;

public class MyLibraryView extends View{

	private List<Game> games;
	String username;
	int firstGame;

	public MyLibraryView( String username, List<Game> games, int firstGame ){
		super( "mylibrary.ftl" );
		this.games = games;
		this.username = username;
		this.firstGame = firstGame;
	}

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
