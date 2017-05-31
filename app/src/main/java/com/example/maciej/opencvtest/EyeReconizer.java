package com.example.maciej.opencvtest;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import org.opencv.core.Rect;
import org.opencv.objdetect.CascadeClassifier;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by maciej on 30.05.17.
 */

public class EyeReconizer {
    private Context context;
    private CascadeClassifier cascadeClassifier;
    EyeReconizer(Context current){
        context=current;
        initializeOpenCVDependencies(); ;
    }
    private void initializeOpenCVDependencies() {
        try {
            // Copy the resource into a temp file so OpenCV can load it
            InputStream is = context.getResources().openRawResource(R.raw.haarcascade_righteye_2splits);
            File cascadeDir = context.getDir("cascade", Context.MODE_PRIVATE);
            File mCascadeFile = new File(cascadeDir, "haarcascade_righteye_2splits.xml");
            FileOutputStream os = new FileOutputStream(mCascadeFile);


            byte[] buffer = new byte[4096];
            int bytesRead;

            while ((bytesRead = is.read(buffer)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            is.close();
            os.close();
// Load the cascade classifier
            cascadeClassifier = new CascadeClassifier(mCascadeFile.getAbsolutePath());
            cascadeClassifier.load(mCascadeFile.getAbsolutePath());
            if (cascadeClassifier.empty()) {
                cascadeClassifier = null;
            }
        } catch (Exception e) {

            Log.e("OpenCVActivity", "Error loading cascade", e);
        }
    }
}


