package com.lan.john.gradienttest.wiget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

/**
 * Created by john on 2017/3/16.
 */

public class GradientPaintView extends View {

    private Paint paintTop;
    private Paint paintMid;
    private Paint paintBottom;


    /**
     * 中间线
     * startPointMid:起始点位
     * endPointMid：终止点位
     * midXArrayRandom：x随机星线浮动范围
     * trackHeightMax：轨道宽度最大值
     * trackHeightMin：轨道宽度最小值
     * midYArrayRandom：trackHeightMax+trackHeightMin控制
     */
    private Point startPointMid = new Point(600, 600);
    private Point endPointMid = new Point(0, 600);
    private int[] midXArrayRandom;
    private int[] midYArrayRandom;
    private int countStart = 20;
    private int trackHeightMax = 140;
    private int trackHeightMin = 20;

    private Point startPointTop = new Point(600, 0);
    private Point endPointTop = new Point(0, 600);
    private int[] topXArrayRandom;
    private int[] topYArrayRandom;
    private int topWidth = 100;

    public GradientPaintView(Context context) {
        super(context);

    }

    private void initPaint() {
        paintTop = new Paint();
        paintMid = new Paint();
        paintBottom = new Paint();
        paintTop.setStrokeWidth(3);
        paintMid.setStrokeWidth(3);
        paintBottom.setStrokeWidth(3);
    }

    public GradientPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientPaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initPaint();
        drawTopLines(canvas);
        drawMidLines(canvas);

    }

    /**
     * 画上部线条
     *
     * @param canvas
     */
    private void drawTopLines(Canvas canvas) {
        float x1 = startPointTop.getX();
        float y1 = startPointTop.getY();
        float x2 = endPointTop.getX();
        int abs1 = (int) Math.abs(x1);
        int abs2 = (int) Math.abs(x2);

        if (null == topXArrayRandom) {
            topXArrayRandom = getRandomArray(abs1 - topWidth, abs1, countStart);
        }
        if (null == topYArrayRandom) {
            if (abs1 > abs2) {
                topYArrayRandom = getRandomArray(abs2, abs1, countStart);
            } else {
                topYArrayRandom = getRandomArray(abs1, abs2, countStart);
            }
        }

        for (int i = 0; i < topXArrayRandom.length; i++) {
            int randomXCount = topXArrayRandom[i];
            int randomYCount = topYArrayRandom[i];
            drawStarLine(canvas, randomXCount, 0, randomYCount, randomXCount - randomYCount, paintTop);
        }
        //            drawStarLine(canvas, 600, 0, 0, 600, paintTop);
//            drawStarLine(canvas, 600, 0, 0, 600, paintTop);
//            drawStarLine(canvas, 500, 0, 0, 500, paintTop);
//            drawStarLine(canvas, 520, 0, 400, 120, paintTop);
//            drawStarLine(canvas, 540, 0, 0, 540, paintTop);
//            drawStarLine(canvas, 560, 0, 0, 560, paintTop);
    }

    /**
     * 画中间线条
     *
     * @param canvas
     */
    private void drawMidLines(Canvas canvas) {
        float midsX1 = startPointMid.getX();
        float midsY1 = startPointMid.getY();
        float mideX1 = endPointMid.getX();
        if (null == midXArrayRandom) {

            int abs1 = (int) Math.abs(midsX1);
            int abs2 = (int) Math.abs(mideX1);
            if (abs1 > abs2) {
                midXArrayRandom = getRandomArray(abs2, abs1, countStart);
            } else {
                midXArrayRandom = getRandomArray(abs1, abs2, countStart);
            }
        }
        if (null == midYArrayRandom) {
            midYArrayRandom = getRandomArray(trackHeightMin, trackHeightMax, countStart);
        }
        for (int i = 0; i < midXArrayRandom.length; i++) {
            int randomXCount = midXArrayRandom[i];
            int randomYCount = midYArrayRandom[i];
            drawStarLine(canvas, midsX1, midsY1 + randomYCount, midsX1 - randomXCount, midsY1 + randomYCount, paintMid);
        }
    }

    /**
     * 画星投影线
     *
     * @param canvas 画布
     * @param startX x起始位置
     * @param startY y起始位置
     * @param stopX  x起终止位置
     * @param stopY  y终止位置
     */
    private void drawStarLine(Canvas canvas, float startX, float startY, float stopX, float stopY, Paint paint) {

        LinearGradient gradient2 = new LinearGradient(startX, startY, stopX, stopY, Color.argb(0, 0, 0, 0), Color.WHITE, Shader.TileMode.CLAMP);
        paint.setShader(gradient2);
        canvas.drawLine(startX, startY, stopX, stopY, paint);
    }


    /**
     * 获取随机数
     *
     * @return
     */
    private int getRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max) % (max - min + 1) + min;
    }

    /**
     * 获取随机数组
     *
     * @param min    数组最小值
     * @param max    数组的最大值
     * @param length 数组长度
     * @return
     */
    private int[] getRandomArray(int min, int max, int length) {
        int[] randomResult = new int[length];
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int randomCount = random.nextInt(max) % (max - min + 1) + min;
            randomResult[i] = randomCount;
        }
        return randomResult;
    }
}
