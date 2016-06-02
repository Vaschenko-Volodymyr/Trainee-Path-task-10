package com.traineepath.volodymyrvashchenko.trainee_path_task_10;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.traineepath.volodymyrvashchenko.trainee_path_task_10.activities.MainActivity;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class InSimpleSizeUITest extends ActivityInstrumentationTestCase2<MainActivity> {
    private static final String TAG = InSimpleSizeUITest.class.getSimpleName();

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

    public InSimpleSizeUITest() {
        super(MainActivity.class);
    }

    @Override
    @Before
    protected void setUp() throws Exception {
        super.setUp();
        Log.v(TAG, ">> Method: setUp()");

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
        Log.v(TAG, "<< Method: setUp()");
    }

    @Test
    public void testWrongValues() {
        Log.v(TAG, ">> Method: testWrongValues()");
        for (String text : mViewWrongValues) {
            for (String param : mImageWrongValues) {

                clearAll();

                fillEditTextFields(text, text, param, param);

                onView(withId(mCalculate.getId())).perform(click());

                compare(mMainActivity.getResources().getString(WRONG_VALUES_RES_ID));

                pause();
            }
        }
        Log.v(TAG, "<< Method: testWrongValues()");
    }

    @Test
    public void testCriticalValues() {
        Log.v(TAG, ">> Method: testCriticalValues()");
        for (String text : mViewCriticalValues) {
            for (String param : mImageCriticalValues) {

                clearAll();

                fillEditTextFields(text, text, param, param);

                onView(withId(mCalculate.getId())).perform(click());

                compare(mMainActivity.getResources().getString(ERROR_RES_ID));

                pause();
            }
        }
        Log.v(TAG, "<< Method: testCriticalValues()");
    }

    @Test
    public void testValidValues() {
        Log.v(TAG, ">> Method: testValidValues()");

        clearAll();

        fillEditTextFields("100", "200", "1000", "5000");

        onView(withId(mCalculate.getId())).perform(click());

        compare("8");

        pause();
        Log.v(TAG, "<< Method: testValidValues()");
    }


    private void clearAll() {
        Log.v(TAG, ">> Method: clearAll()");
        for (final TextView view : mViews) {
            getInstrumentation().runOnMainSync(new Runnable() {
                @Override
                public void run() {
                    view.setText("");
                }
            });
        }
        Log.v(TAG, "<< Method: clearAll()");
    }

    private void fillEditTextFields(String viewHeight, String viewWidth, String imageHeight, String imageWidth) {
        Log.v(TAG, ">> Method: fillEditTextFields()");
        onView(withId(mViewHeight.getId())).perform(typeText(viewHeight));
        onView(withId(mViewWidth.getId())).perform(typeText(viewWidth));
        onView(withId(mImageHeight.getId())).perform(typeText(imageHeight));
        onView(withId(mImageWidth.getId())).perform(typeText(imageWidth), closeSoftKeyboard());
        Log.v(TAG, "<< Method: fillEditTextFields()");
    }

    private void compare(String expectedString) {
        Log.v(TAG, ">> Method: compare()");
        String obtainedString = mResult.getText().toString();
        assertEquals(expectedString, obtainedString);
        Log.v(TAG, "<< Method: compare()");
    }

    private void pause() {
        Log.v(TAG, ">> Method: pause()");
        try {
            Thread.sleep(100);
            Log.v(TAG, "<< Method: pause()");
        } catch (InterruptedException e) {
            Log.e(TAG, "Interrupted error");
            Log.v(TAG, "<< Method: pause()");
        }
    }
}