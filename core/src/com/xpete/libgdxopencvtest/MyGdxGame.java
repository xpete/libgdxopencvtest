package com.xpete.libgdxopencvtest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	private DeviceCameraControl deviceCameraControl;
	
	public MyGdxGame (DeviceCameraControl deviceCameraControl){
		this.deviceCameraControl = deviceCameraControl;
	}
	
	@Override
	public void create () {
		setScreen(new GameScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width,height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
	
	/**
	 * @return the deviceCameraControl
	 */
	public DeviceCameraControl getDeviceCameraControl() {
		return deviceCameraControl;
	}
}
