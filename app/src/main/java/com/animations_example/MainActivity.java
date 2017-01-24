package com.animations_example;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.transitionseverywhere.ArcMotion;
import com.transitionseverywhere.ChangeBounds;
import com.transitionseverywhere.ChangeImageTransform;
import com.transitionseverywhere.ChangeText;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

public class MainActivity extends AppCompatActivity {
    private ImageView iv_plus, imageView;
    private TextView text, tv_scale_fade, tv_slide, tv_slide_left;
    Button button, button_scale_fade, btn_slide, btn_recolor, btn_change_text, btn_slide_left;
    ViewGroup transitionsContainer;
    View button_path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
//  -------- auto transition sample --------
        text = (TextView) transitionsContainer.findViewById(R.id.text);
        button = (Button) transitionsContainer.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionsContainer);
                visible = !visible;
                text.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });
//  ------- scale and fade sample ---------
        tv_scale_fade = (TextView) transitionsContainer.findViewById(R.id.tv_scale_fade);
        button_scale_fade = (Button) transitionsContainer.findViewById(R.id.button_scale_fade);
        button_scale_fade.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View view) {
                visible = !visible;
                TransitionSet set = new TransitionSet()
                        .addTransition(new Scale(0.7f))
                        .addTransition(new Fade())
                        .setInterpolator(visible ? new LinearOutSlowInInterpolator() : new FastOutLinearInInterpolator());
                TransitionManager.beginDelayedTransition(transitionsContainer, set);
                tv_scale_fade.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
            }
        });
//  ------------ slide right sample -------------
        tv_slide = (TextView) transitionsContainer.findViewById(R.id.tv_slide);
        btn_slide = (Button) transitionsContainer.findViewById(R.id.btn_slide);
        btn_slide.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View view) {
                visible = !visible;
                TransitionManager.beginDelayedTransition(transitionsContainer, new Slide(Gravity.RIGHT));
                tv_slide.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });
//  ------------ slide Left sample -------------
        tv_slide_left = (TextView) transitionsContainer.findViewById(R.id.tv_slide_left);
        btn_slide_left = (Button) transitionsContainer.findViewById(R.id.btn_slide_left);
        btn_slide_left.setOnClickListener(new View.OnClickListener() {
            boolean visible;

            @Override
            public void onClick(View view) {
                visible = !visible;
                TransitionManager.beginDelayedTransition(transitionsContainer, new Slide(Gravity.LEFT));
                tv_slide_left.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });
//  ---------- recolor sample -----------
        btn_recolor = (Button) transitionsContainer.findViewById(R.id.btn_recolor);
        btn_recolor.setOnClickListener(new View.OnClickListener() {
            boolean isColorsInverted;
            boolean mColorsInverted;

            @Override
            public void onClick(View view) {
                isColorsInverted = !isColorsInverted;
                TransitionManager.beginDelayedTransition(transitionsContainer, new Recolor());
                btn_recolor.setTextColor(getResources().getColor(!isColorsInverted ? R.color.colorPrimary : R.color.colorAccent));
                mColorsInverted = !mColorsInverted;
                btn_recolor.setBackgroundDrawable(new ColorDrawable(getResources().getColor(!mColorsInverted ? R.color.colorAccent : R.color.colorPrimary)));
            }
        });
//  ----------- isRotated sample ----------
        iv_plus = (ImageView) transitionsContainer.findViewById(R.id.iv_plus);
        iv_plus.setOnClickListener(new View.OnClickListener() {
            boolean isRotated;

            @Override
            public void onClick(View view) {
                isRotated = !isRotated;
                TransitionManager.beginDelayedTransition(transitionsContainer, new Rotate());
                iv_plus.setRotation(isRotated ? 360 : 0);
            }
        });
//  ----------- changeText sample -----------
        btn_change_text = (Button) transitionsContainer.findViewById(R.id.btn_change_text);
        btn_change_text.setOnClickListener(new View.OnClickListener() {
            boolean isSecondText;

            @Override
            public void onClick(View view) {
                isSecondText = !isSecondText;
                TransitionManager.beginDelayedTransition(transitionsContainer, new ChangeText().setChangeBehavior(ChangeText.CHANGE_BEHAVIOR_OUT_IN));
                btn_change_text.setText(isSecondText ? "Hi,I am Text.Tap on me!" : "Thanks! Another tap?");
            }
        });
//  ---------- Path (Curved) motion -------------
        button_path = transitionsContainer.findViewById(R.id.button_path);
        button_path.setOnClickListener(new View.OnClickListener() {
            boolean mToRightAnimation;

            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(transitionsContainer, new ChangeBounds().setPathMotion(new ArcMotion()).setDuration(500));
                mToRightAnimation = !mToRightAnimation;
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) button_path.getLayoutParams();
                params.gravity = mToRightAnimation ? (Gravity.RIGHT | Gravity.BOTTOM) : (Gravity.LEFT | Gravity.TOP);
                button_path.setLayoutParams(params);
            }
        });
//  ------------ Change Image TransForm ---------------
        imageView = (ImageView) transitionsContainer.findViewById(R.id.image);
        imageView.setOnClickListener(new View.OnClickListener() {
            boolean mExpanded;

            @Override
            public void onClick(View v) {
                mExpanded = !mExpanded;
                TransitionManager.beginDelayedTransition(transitionsContainer, new TransitionSet()
                        .addTransition(new ChangeBounds())
                        .addTransition(new ChangeImageTransform()));
                ViewGroup.LayoutParams params = imageView.getLayoutParams();
                params.height = mExpanded ? ViewGroup.LayoutParams.MATCH_PARENT : ViewGroup.LayoutParams.WRAP_CONTENT;
                imageView.setLayoutParams(params);
                imageView.setScaleType(mExpanded ? ImageView.ScaleType.CENTER_CROP : ImageView.ScaleType.FIT_CENTER);
            }
        });
    }
}
