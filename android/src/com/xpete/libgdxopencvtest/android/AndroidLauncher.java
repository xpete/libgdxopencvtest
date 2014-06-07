package com.xpete.libgdxopencvtest.android;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.xpete.libgdxopencvtest.DeviceCameraControl;
import com.xpete.libgdxopencvtest.MyGdxGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
        DeviceCameraControl cameraControl = new AndroidDeviceCameraController(this);
		initialize(new MyGdxGame(cameraControl), config);
	}
}
