package com.example.john.sunset;

import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

/**
 * Created by john on 2016/10/4.
 */
public class SunsetFragment extends Fragment {
    private View mView;
    private FrameLayout sky;
    private ImageView sun;

    private int mBlueSkyColor;
    private int mSunsetSkyColor;
    private int mnightSkyColor;

    public static SunsetFragment getInstace() {
        return new SunsetFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sunset, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mView = view;
        sky = (FrameLayout) view.findViewById(R.id.sky);
        sun = (ImageView) view.findViewById(R.id.sun);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAnimation();
            }
        });

        mBlueSkyColor = getResources().getColor(R.color.blue_sky);
        mSunsetSkyColor = getResources().getColor(R.color.sunset_sky);
        mnightSkyColor = getResources().getColor(R.color.night_sky);
    }

    private void startAnimation() {
        float sunYStart = sun.getTop();
        float sunYEnd = sky.getHeight();

        ObjectAnimator heightAnimator = ObjectAnimator.ofFloat(sun, "y", sunYStart, sunYEnd)
                .setDuration(3000);
        heightAnimator.setInterpolator(new AccelerateInterpolator());

        ObjectAnimator skyColorAnimator = ObjectAnimator.ofInt(sky, "backgroundColor", mBlueSkyColor, mSunsetSkyColor)
                .setDuration(3000);
        skyColorAnimator.setEvaluator(new ArgbEvaluator());
        ObjectAnimator nightAnimator = ObjectAnimator.ofInt(sky, "backgroundColor", mSunsetSkyColor, mnightSkyColor)
                .setDuration(1500);
        nightAnimator.setEvaluator(new ArgbEvaluator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(heightAnimator).with(skyColorAnimator).before(nightAnimator);
        animatorSet.start();
    }

}
