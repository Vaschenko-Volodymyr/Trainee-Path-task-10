package com.traineepath.volodymyrvashchenko.trainee_path_task_10.utils;

import android.util.Log;

public class BitmapUtils {
    private static final String TAG = BitmapUtils.class.getSimpleName();

    public static int inSampleSize(int viewHeight, int viewWidth, int imageHeight, int imageWidth) {
        Log.v(TAG, ">> Method: inSampleSize()");

        if (viewHeight <= 0 || viewWidth <= 0 ||
                imageHeight <= 0 || imageWidth <= 0) {
            Log.v(TAG, "<< Method: inSampleSize()");
            return -1;
        }

        int inSampleSize = 1;

        final int halfHeight = imageHeight / 2;
        final int halfWidth = imageWidth / 2;

        while ((halfHeight / inSampleSize) > viewHeight
                && (halfWidth / inSampleSize) > viewWidth) {
            inSampleSize *= 2;
        }
        Log.v(TAG, "<< Method: inSampleSize()");
        return inSampleSize;
    }
}
