package com.lan.john.gradienttest.wiget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.lan.john.gradienttest.R;

import java.util.Random;

/**
 * Created by john on 2017/3/16.
 */

public class GradientBottomPaintView extends View {

    private Paint paintTop;
    private Paint paintMid;
    private Paint paintBottom;

    /**
     * lineCountTop：顶部线数量
     * lineCountMid：中部线数量
     * lineCountBottom：底部线数量
     */
    private int lineCountTop = 20;
    private int lineCountMid = 20;
    private int lineCountBottom = 20;

    /**
     * 中间线
     * startPointMid:起始点位
     * endPointMid：终止点位
     * midXArrayRandom：x随机星线浮动范围
     * trackHeightMax：轨道宽度最大值
     * trackHeightMin：轨道宽度最小值
     * midYArrayRandom：trackHeightMax+trackHeightMin控制
     */
    private Point startPointMid = new Point(300, 150);
    private Point endPointMid = new Point(0, 150);
    private int[] midXArrayRandom;
    private int[] midYArrayRandom;
    private int trackHeightMax = 80;
    private int trackHeightMin = 20;

    private Point startPointTop = new Point(200, 0);
    private Point endPointTop = new Point(0, 200);
    private int[] topXArrayRandom;
    private int[] topYArrayRandom;
    private int topWidth = 80;

    private Point startPointBottom = new Point(200, 400);
    private Point endPointBottom = new Point(0, 200);
    private int[] bottomXArrayRandom;
    private int[] bottomYArrayRandom;
    private int[] bottomXsArrayRandom;    //起始位置
    private int bottomWidth = 80;
    private int bottomTrackLength = 130;

    /**
     * 线条颜色
     */
    private int mLineColor;

    public GradientBottomPaintView(Context context) {
        super(context);
        initPaint();

    }

    public GradientBottomPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPaint(context,attrs);
    }

    public GradientBottomPaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint(context,attrs);
    }

    private void initPaint(Context context, AttributeSet attrs) {
        //获取自定义属性。
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.GradientPaintView);
        mLineColor = typedArray.getColor(R.styleable.GradientPaintView_lineColor, Color.WHITE);

        paintTop = new Paint();
        paintMid = new Paint();
        paintBottom = new Paint();
        paintTop.setStrokeWidth(3);
        paintMid.setStrokeWidth(3);
        paintBottom.setStrokeWidth(3);
    }
    private void initPaint() {
        paintTop = new Paint();
        paintMid = new Paint();
        paintBottom = new Paint();
        paintTop.setStrokeWidth(3);
        paintMid.setStrokeWidth(3);
        paintBottom.setStrokeWidth(3);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int screenWidth = getScreenWidth();
        canvas.translate(-700, 0);
        canvas.rotate(270); // 画布旋转（默认以原点，顺时针）
        drawTopLines(canvas);
        drawMidLines(canvas);
        drawBottomLines(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec),
                measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result=75;
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;

    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        if (mode == MeasureSpec.EXACTLY) {
            result = size;
        } else {
            result = 75;//根据自己的需要更改
            if (mode == MeasureSpec.AT_MOST) {
                result = Math.min(result, size);
            }
        }
        return result;

    }
    /**
     * 画上部线条
     *
     * @param canvas
     */
    private void drawBottomLines(Canvas canvas) {
        float x1 = startPointBottom.getX();
        float y1 = startPointBottom.getY();
        float x2 = endPointBottom.getX();
        float y2 = endPointBottom.getY();
        int abs1 = (int) Math.abs(x1);
        int abs2 = (int) Math.abs(x2);

        if (null == bottomXArrayRandom) {
            bottomXArrayRandom = getRandomArray((int)y2, (int)y2+bottomWidth, lineCountBottom);
        }

        if (null == bottomYArrayRandom) {
            bottomYArrayRandom = getRandomArray(0, bottomTrackLength, lineCountBottom);
        }

        if (null == bottomXsArrayRandom) {
            bottomXsArrayRandom = getRandomArray(0, (int)x1, lineCountBottom);
        }

        for (int i = 0; i < bottomXArrayRandom.length; i++) {
            int randomXCount = bottomXArrayRandom[i];//235  269
//            int randomYCount = bottomYArrayRandom[i];//44  91
            int randomXsCount = bottomXsArrayRandom[i];//74  3
            int randomYCount = randomXsCount+bottomYArrayRandom[i];//44  91

          //  drawStarLine(canvas, randomYCount, randomYCount+randomXCount, randomYCount-40, randomXCount+randomYCount, paintBottom);
            drawStarLine(canvas, randomYCount, randomYCount+randomXCount, randomXsCount, randomXCount+randomXsCount, paintBottom);
        }
        //drawStarLine(canvas, 44, 279, 74, 309, paintBottom);
        //drawStarLine(canvas, 81, 360, 3, 272, paintBottom);
//        drawStarLine(canvas, 200, 400, 100, 300, paintBottom);
//        drawStarLine(canvas, 200, 400, 50, 250, paintBottom);
//        drawStarLine(canvas, 100, 400, 0, 300, paintBottom);
//        drawStarLine(canvas, 250, 500, 0, 250, paintBottom);
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
            topXArrayRandom = getRandomArray(abs1 - topWidth, abs1, lineCountTop);
        }
        if (null == topYArrayRandom) {
            if (abs1 > abs2) {
                topYArrayRandom = getRandomArray(abs2, abs1, lineCountTop);
            } else {
                topYArrayRandom = getRandomArray(abs1, abs2, lineCountTop);
            }
        }
        double d1 = (x1 - y1) / x1;
        for (int i = 0; i < topXArrayRandom.length; i++) {
            int randomXCount = topXArrayRandom[i];
            int randomYCount = topYArrayRandom[i];
            drawStarLine(canvas, randomXCount, y1, randomYCount, randomXCount-(int)(randomYCount*d1), paintTop);
        }

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
                midXArrayRandom = getRandomArray(abs2, abs1, lineCountMid);
            } else {
                midXArrayRandom = getRandomArray(abs1, abs2, lineCountMid);
            }
        }
        if (null == midYArrayRandom) {
            midYArrayRandom = getRandomArray(trackHeightMin, trackHeightMax, lineCountMid);
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

    /**
     * 得到屏幕宽度
     *
     * @return
     */
    private int getScreenWidth() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
