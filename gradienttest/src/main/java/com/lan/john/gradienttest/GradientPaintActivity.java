package com.lan.john.gradienttest;

import android.app.Activity;
import android.os.Bundle;

import com.lan.john.gradienttest.wiget.GradientPaintView;

public class GradientPaintActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gradient_paint);

        GradientPaintView gd_view = (GradientPaintView)findViewById(R.id.gd_view);
        gd_view.setLineCount(20,20,20);

    }
}
