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

    private Point currentPointTop;
    private Point currentPointMid;
    private Point currentPointBottom;
    //中间线
    private Point startPointMid = new Point(600,300);
    private Point endPointMid = new Point(0,300);
    private int lineLengthMid = 40;


    public GradientPaintView(Context context) {
        super(context);

    }

    private void initPaint() {
        paintTop = new Paint();
        paintMid = new Paint();
        paintBottom = new Paint();
        paintTop.setStrokeWidth(3);
        paintMid.setStrokeWidth(3);
        paintBottom.setStrokeWidth(5);
    }

    public GradientPaintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientPaintView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void startTopAnimation() {

        Point startPoint = new Point(600, 0);
        Point endPoint = new Point(0, 300);
        ValueAnimator anim = ValueAnimator
                .ofObject(new PointEvaluator(), startPoint, endPoint);

        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPointTop = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setDuration(3000);
        anim.start();
    }

    private void startMidAnimation() {

        ValueAnimator anim = ValueAnimator
                .ofObject(new PointEvaluator(), startPointMid, endPointMid);

        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPointMid = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });
        anim.setDuration(4000);
        anim.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        initPaint();

        if (currentPointTop == null) {
            currentPointTop = new Point(600, 0);
            drawStarLine(canvas,600, 0, 0, 300);
            startTopAnimation();
        } else {
            float x = currentPointTop.getX();
            float y = currentPointTop.getY();
            drawStarLine(canvas,x, y, x -80, y+20);
            drawStarLine(canvas,x-40, y+20, x -120, y+40);
            drawStarLine(canvas,x-120, y+40, x -160, y+60);
        }

        if (currentPointMid == null) {
            currentPointMid = new Point(600, 300);
            drawStarLine(canvas,400, 300, 600, 300);
            startMidAnimation();
        } else {
            float x = currentPointMid.getX();
            float y = currentPointMid.getY();
            // x:600 - 0  y:300 - 300 x浮动：300 [0，600] y浮动
//            int midLineCount = 1;
//            for (int i=0;i<midLineCount;i++){
//                //random = [0,600]随机数
//                int randomX = getRandom((int)endPointMid.getX(), (int)startPointMid.getX());
//
//                int randomY = getRandom(0, (int)startPointMid.getY());
//                drawStarLine(canvas,randomX-40, randomY, randomX, randomY);
//
//            }
//            int randomX = getRandom((int)endPointMid.getX(), (int)startPointMid.getX());
//
//            int random = getRandom(20, 60);
            drawStarLine(canvas,x, 300+20, x-80, 300+20);
            drawStarLine(canvas,x-80, 300-20, x-160, 300-20);
            drawStarLine(canvas,x-120, 300, x-180, 300);
            drawStarLine(canvas,x-220, 300+10, x-280, 300+10);
            drawStarLine(canvas,x-10, 300+30, x-300, 300+30);

        }


    }

    /**
     *  画星投影线
     *
     * @param canvas 画布
     * @param startX x起始位置
     * @param startY y起始位置
     * @param stopX x起终止位置
     * @param stopY y终止位置
     */
    private void drawStarLine(Canvas canvas, float startX, float startY, float stopX, float stopY) {

        LinearGradient gradient2 = new LinearGradient(startX, startY,stopX , stopY, Color.argb(0, 0, 0, 0), Color.WHITE, Shader.TileMode.CLAMP);
        paintMid.setShader(gradient2);
        canvas.drawLine(startX, startY, stopX, stopY, paintMid);
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 获取随机数
     *
     * @return
     */
    private int getRandom(int min,int max){
        Random random = new Random();
        return random.nextInt(max)%(max-min+1) + min;
    }
}
