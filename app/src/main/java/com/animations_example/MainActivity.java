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
import android.widget.ImageView;
import android.widget.TextView;

import com.transitionseverywhere.ChangeText;
import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.Slide;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

public class MainActivity extends AppCompatActivity {
    private ImageView iv_plus;
    private TextView text, tv_scale_fade,tv_slide;
    Button button, button_scale_fade,btn_slide, btn_recolor, btn_change_text;
    ViewGroup transitionsContainer;

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
    }
}
