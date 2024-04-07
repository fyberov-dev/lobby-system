package org.fyberov.dev.lobby;

import com.badlogic.gdx.Game;
import org.fyberov.dev.lobby.screens.MainMenuScreen;

public class GameClient extends Game {

	private static GameClient instance;

	public GameClient() {
		instance = this;
	}

	@Override
	public void create() {
		setScreen(new MainMenuScreen());
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.render();
	}

	public static GameClient getInstance() {
		return instance;
	}
}
