package com.traineepath.volodymyrvashchenko.trainee_path_task_10;

import android.test.suitebuilder.annotation.SmallTest;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.runners.MockitoJUnitRunner;

import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

@SmallTest
@RunWith(MockitoJUnitRunner.class)
public class InSampleSizeTest {

    MainActivity mActivity;

    private Random mRandom;

    private int[] mPositiveValues = {1, 100, 1000, 5000, 10_000, 100_000, 33, 555, 7777, 123456};

    private int[] mNegativeValues = {-1000, -100 -1, 0};

    @Before
    public void init() {
        mActivity = new MainActivity();
        mRandom = new Random();
    }

    @Test
    public void inSampleSizePositiveTest() {
        for (int i = 0; i < 10; i++) {
            try {
                int result = transferValuesToTestedMethod(mPositiveValues);
                assertTrue(result >= 1);
            } catch (Exception e) {
                fail();
            }
        }
    }

    @Test
    public void inSampleSizeNegativeTest() {
        for (int i = 0; i < 10; i++) {
            try {
                int result = transferValuesToTestedMethod(mNegativeValues);
                assertTrue(result == -1);
            } catch (Exception e) {
                assertTrue(true);
            }
        }
    }

    private int transferValuesToTestedMethod(int[] values) {
        return mActivity.inSampleSize(
                values[mRandom.nextInt(values.length)],
                values[mRandom.nextInt(values.length)],
                values[mRandom.nextInt(values.length)],
                values[mRandom.nextInt(values.length)]
        );
    }
}
