package com.xpete.libgdxopencvtest.android;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewFrame;
import org.opencv.android.CameraBridgeViewBase.CvCameraViewListener2;
import org.opencv.android.JavaCameraView;
import org.opencv.android.LoaderCallbackInterface;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Mat;
import android.annotation.SuppressLint;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.os.Build;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import com.xpete.libgdxopencvtest.DeviceCameraControl;

public class AndroidDeviceCameraController implements CvCameraViewListener2, DeviceCameraControl {
    private static final String TAG = "OCVSample::Activity";

    private CameraBridgeViewBase mOpenCvCameraView;
    private AndroidLauncher activity;
    
    // The index of the active camera.
    private int mCameraIndex;
    
    // The number of cameras on the device.
    private int mNumCameras;

    public AndroidDeviceCameraController(AndroidLauncher activity) {
		this.activity = activity;
	}
    
    private BaseLoaderCallback mLoaderCallback = new BaseLoaderCallback(activity) {
        @Override
        public void onManagerConnected(int status) {
            switch (status) {
                case LoaderCallbackInterface.SUCCESS:
                {
                    Log.i(TAG, "OpenCV loaded successfully");
                    mOpenCvCameraView.enableView();
                 } break;
                default:
                {
                    super.onManagerConnected(status);
                } break;
            }
        }
    };

	/** Called when the activity is first created. */
    @SuppressLint("NewApi")
    @Override
    public void create() {
    	activity.runOnUiThread(new Runnable() {
    	     @Override
    	     public void run() {
    	    	 
    	         mCameraIndex = 0;

    	         //final Window window = activity.getWindow();
    	         //window.addFlags(
    	         //		WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    	         mCameraIndex = 0;
    	         
    	         if (Build.VERSION.SDK_INT >=
    	                 Build.VERSION_CODES.GINGERBREAD) {
    	             CameraInfo cameraInfo = new CameraInfo();
    	             Camera.getCameraInfo(mCameraIndex, cameraInfo);
    	             mNumCameras = Camera.getNumberOfCameras();
    	         } else { // pre-Gingerbread
    	             // Assume there is only 1 camera and it is rear-facing.
    	             mNumCameras = 1;
    	         }
    	         
    	         mOpenCvCameraView = new JavaCameraView(activity, mCameraIndex);

    	         mOpenCvCameraView.setMaxFrameSize(352, 288);
    	         mOpenCvCameraView.setCvCameraViewListener(AndroidDeviceCameraController.this);
    	         activity.setContentView(mOpenCvCameraView);
    	    }
    	});

    }

    @Override
    public void pause()
    {
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    @Override
    public void resume()
    {
        OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_9, activity, mLoaderCallback);
    }

    @Override
    public void destroy() {
        if (mOpenCvCameraView != null)
            mOpenCvCameraView.disableView();
    }

    public void onCameraViewStarted(int width, int height) {
    }

    public void onCameraViewStopped() {
    }

    public Mat onCameraFrame(CvCameraViewFrame inputFrame) {
        return inputFrame.rgba();
    }

}
