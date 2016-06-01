package com.traineepath.volodymyrvashchenko.trainee_path_task_10;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.TouchUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class InSimpleSizeTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final String TAG = InSimpleSizeTest.class.getSimpleName();

    private static final int ERROR_RES_ID = R.string.error;
    private static final int WRONG_VALUES_RES_ID = R.string.wrong_input_values;

    private MainActivity mMainActivity;
    private Instrumentation mInstrumentation;

    private EditText mViewHeight;
    private EditText mViewWidth;
    private EditText mImageHeight;
    private EditText mImageWidth;

    private Button mCalculate;

    private TextView mResult;

    private Set<TextView> mViews;

    private String[] mViewWrongValues = new String[]{"", "0", "string"};
    private String[] mImageWrongValues = new String[]{"", "0", "string"};

    private String[] mViewCriticalValues = new String[]{String.valueOf(Long.MIN_VALUE), String.valueOf(Long.MAX_VALUE)};
    private String[] mImageCriticalValues = new String[]{String.valueOf(Long.MIN_VALUE), String.valueOf(Long.MAX_VALUE)};

    public InSimpleSizeTest() {
        super(MainActivity.class);
    }

    @Override
    @Before
    protected void setUp() throws Exception {
        super.setUp();

        setActivityInitialTouchMode(true);

        mInstrumentation = getInstrumentation();
        mMainActivity = getActivity();

        mViewHeight = (EditText) mMainActivity.findViewById(R.id.view_height);
        mViewWidth = (EditText) mMainActivity.findViewById(R.id.view_width);
        mImageHeight = (EditText) mMainActivity.findViewById(R.id.image_height);
        mImageWidth = (EditText) mMainActivity.findViewById(R.id.image_width);

        mCalculate = (Button) mMainActivity.findViewById(R.id.calculate);

        mResult = (TextView) getActivity().findViewById(R.id.result);

        mViews = new HashSet<>(Arrays.asList(mViewHeight, mViewWidth, mImageHeight, mImageWidth, mResult));
    }

    @Test
    public void testWrongValues() {
        for (String text : mViewWrongValues) {
            for (String param : mImageWrongValues) {

                clearAll();

                fillEditTextFields(text, text, param, param);

                TouchUtils.clickView(this, mCalculate);

                compare(mMainActivity.getResources().getString(WRONG_VALUES_RES_ID));

                pause();
            }
        }
    }

    @Test
    public void testCriticalValues() {
        for (String text : mViewCriticalValues) {
            for (String param : mImageCriticalValues) {

                clearAll();

                fillEditTextFields(text, text, param, param);

                TouchUtils.clickView(this, mCalculate);

                compare(mMainActivity.getResources().getString(ERROR_RES_ID));

                pause();
            }
        }
    }

    @Test
    public void testValidValues() {

        clearAll();

        fillEditTextFields("100", "200", "1000", "5000");

        TouchUtils.clickView(this, mCalculate);

        compare("8");

        pause();
    }


    private void clearAll() {
        for (final TextView view : mViews)
            getInstrumentation().runOnMainSync(new Runnable() {
                @Override
                public void run() {
                    view.setText("");
                }
            });
    }

    private void fillEditTextFields(String viewHeight, String viewWidth, String imageHeight, String imageWidth) {
        setViewText(mViewHeight, viewHeight);
        setViewText(mViewWidth, viewWidth);
        setViewText(mImageHeight, imageHeight);
        setViewText(mImageWidth, imageWidth);
    }

    private void compare(String expectedString) {
        String obtainedString = mResult.getText().toString();
        assertEquals(expectedString, obtainedString);
    }

    private void pause() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Log.e(TAG, "Interrupted error");
        }
    }

    private void setViewText(final View view, final String text) {

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                view.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        if (view instanceof TextView) {
            getInstrumentation().sendStringSync(text);
        }
        getInstrumentation().waitForIdleSync();
    }
}