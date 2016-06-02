package com.traineepath.volodymyrvashchenko.trainee_path_task_10.test;

import android.util.Log;

import static com.traineepath.volodymyrvashchenko.trainee_path_task_10.utils.BitmapUtils.inSampleSize;


import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class InSampleSizeUnitTest {
    private static final String TAG = InSampleSizeUnitTest.class.getSimpleName();

    @Test
    public void testSampleSizeWithNormalValues() {
        Log.v(TAG, ">> Method: testSampleSizeWithNormalValues()");
        runTest(234, 353, 2325, 1652, 4);
        Log.v(TAG, "<< Method: testSampleSizeWithNormalValues()");
    }

    @Test
    public void testSampleSizeDoesNotDependsOnOrder() {
        Log.v(TAG, ">> Method: testSampleSizeDoesNotDependsOnOrder()");
        assertEquals(inSampleSize(100, 200, 300, 400),
                inSampleSize(200, 100, 400, 300));
        Log.v(TAG, "<< Method: testSampleSizeDoesNotDependsOnOrder()");
    }

    @Test
    public void testSampleSizeWithMinimumPositiveValues() {
        Log.v(TAG, ">> Method: testSampleSizeWithMinimumPositiveValues()");
        runTest(1, 1, 1, 1, 1);
        Log.v(TAG, "<< Method: testSampleSizeWithMinimumPositiveValues()");
    }

    @Test
    public void testSampleSizeWithMaximumValues() {
        Log.v(TAG, ">> Method: testSampleSizeWithMaximumValues()");
        runTest(Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 1);
        Log.v(TAG, ">> Method: testSampleSizeWithMaximumValues()");
    }

    @Test
    public void testSampleSizeWithMinimumAndMaximumPositiveValues() {
        Log.v(TAG, ">> Method: testSampleSizeWithMinimumAndMaximumPositiveValues()");
        runTest(Integer.MAX_VALUE, Integer.MAX_VALUE, 1, 1, 1);
        Log.v(TAG, "<< Method: testSampleSizeWithMinimumAndMaximumPositiveValues()");
    }

    @Test
    public void testSampleSizeWithZeroValues() {
        Log.v(TAG, ">> Method: testSampleSizeWithZeroValues()");
        runTest(0, 0, 0, 0, -1);
        Log.v(TAG, "<< Method: testSampleSizeWithZeroValues()");
    }

    @Test
    public void testSampleSizeWithMinimumValues() {
        Log.v(TAG, ">> Method: testSampleSizeWithMinimumValues()");
        runTest(Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, -1);
        Log.v(TAG, "<< Method: testSampleSizeWithMinimumValues()");
    }

    @Test
    public void testSampleSizeWithFirstNegativeValues() {
        Log.v(TAG, ">> Method: testSampleSizeWithFirstNegativeValues()");
        runTest(Integer.MIN_VALUE, 200, 300, 400, -1);
        Log.v(TAG, "<< Method: testSampleSizeWithFirstNegativeValues()");
    }

    @Test
    public void testSampleSizeWithSecondNegativeValues() {
        Log.v(TAG, ">> Method: testSampleSizeWithSecondNegativeValues()");
        runTest(100, Integer.MIN_VALUE, 300, 400, -1);
        Log.v(TAG, "<< Method: testSampleSizeWithSecondNegativeValues()");
    }

    @Test
    public void testSampleSizeWithThirdNegativeValues() {
        Log.v(TAG, ">> Method: testSampleSizeWithThirdNegativeValues()");
        runTest(100, 200, Integer.MIN_VALUE, 400, -1);
        Log.v(TAG, "<< Method: testSampleSizeWithThirdNegativeValues()");
    }

    @Test
    public void testSampleSizeWithFourthNegativeValues() {
        Log.v(TAG, ">> Method: testSampleSizeWithFourthNegativeValues()");
        runTest(100, 200, 300, Integer.MIN_VALUE, -1);
        Log.v(TAG, "<< Method: testSampleSizeWithFourthNegativeValues()");
    }

    private void runTest(int viewHeight, int viewWidth, int imageHeight, int imageWidth, int expected) {
        Log.v(TAG, ">> Method: runTest()");
        int actual = inSampleSize(
                viewHeight,
                viewWidth,
                imageHeight,
                imageWidth
        );
        assertEquals(expected, actual);
        Log.v(TAG, "<< Method: runTest()");
    }
}
