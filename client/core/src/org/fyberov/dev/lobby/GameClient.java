package org.fyberov.dev.lobby;

import com.badlogic.gdx.Game;
import org.fyberov.dev.lobby.screens.MainMenuScreen;

public class GameClient extends Game {

	@Override
	public void create () {
		setScreen(new MainMenuScreen());
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		super.render();
	}
}
