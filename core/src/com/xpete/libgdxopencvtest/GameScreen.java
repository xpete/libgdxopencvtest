package com.xpete.libgdxopencvtest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

public class GameScreen implements Screen {
	
    private final DeviceCameraControl deviceCameraControl;
    private MyGdxGame game;
    
    public GameScreen(MyGdxGame game) {
    	this.game = game;
        this.deviceCameraControl = game.getDeviceCameraControl();
    }


	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl20.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
        this.deviceCameraControl.create();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		this.deviceCameraControl.pause();
	}

	@Override
	public void resume() {
		this.deviceCameraControl.resume();
		
	}

	@Override
	public void dispose() {
		
	}
	
}
