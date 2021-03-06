package com.traineepath.volodymyrvashchenko.trainee_path_task_10.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.traineepath.volodymyrvashchenko.trainee_path_task_10.R;
import com.traineepath.volodymyrvashchenko.trainee_path_task_10.utils.BitmapUtils;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private static final int ERROR_RES_ID = R.string.error;
    private static final int WRONG_VALUES_RES_ID = R.string.wrong_input_values;

    private EditText mViewHeight;
    private EditText mViewWidth;
    private EditText mImageHeight;
    private EditText mImageWidth;

    private TextView mResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, ">> Method: onCreate()");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewHeight = (EditText) findViewById(R.id.view_height);
        mViewWidth = (EditText) findViewById(R.id.view_width);
        mImageHeight = (EditText) findViewById(R.id.image_height);
        mImageWidth = (EditText) findViewById(R.id.image_width);

        Button calculate = (Button) findViewById(R.id.calculate);
        setCalculateOnClickListener(calculate);

        mResult = (TextView) findViewById(R.id.result);

        Log.v(TAG, "<< Method: onCreate()");
    }

    private void setCalculateOnClickListener(final Button calculate) {
        Log.v(TAG, ">> Method: setCalculateOnClickListener()");

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mResult.setText(String.valueOf(choseTextToShow(
                        mViewHeight.getText().toString(),
                        mViewWidth.getText().toString(),
                        mImageHeight.getText().toString(),
                        mImageWidth.getText().toString()
                )));

                hideSoftKeyboard(v);
            }
        });

        Log.v(TAG, "<< Method: setCalculateOnClickListener()");
    }

    private void hideSoftKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) MainActivity.this.getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }

    private String choseTextToShow(
            String viewHeight, String viewWidth, String imageHeight, String imageWidth) {
        Log.v(TAG, ">> Method: choseTextToShow()");

        if (viewHeight.equals("") || viewWidth.equals("") ||
                imageHeight.equals("") || imageWidth.equals("")) {
            Log.v(TAG, "<< Method: choseTextToShow()");
            return getString(WRONG_VALUES_RES_ID);
        }

        try {
            int viewHeightValue = Integer.valueOf(viewHeight);
            int viewWidthValue = Integer.valueOf(viewWidth);
            int imageHeightValue = Integer.valueOf(imageHeight);
            int imageWidthValue = Integer.valueOf(imageWidth);

            int result = BitmapUtils.inSampleSize(viewHeightValue, viewWidthValue, imageHeightValue, imageWidthValue);

            if (result == -1) {
                return getString(WRONG_VALUES_RES_ID);
            }

            Log.v(TAG, "<< Method: choseTextToShow()");
            return String.valueOf(result);
        } catch (NumberFormatException e) {
            return getString(ERROR_RES_ID);
        }
    }
}
