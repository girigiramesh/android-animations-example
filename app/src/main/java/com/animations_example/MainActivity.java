package com.animations_example;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.transitionseverywhere.Fade;
import com.transitionseverywhere.Recolor;
import com.transitionseverywhere.Rotate;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

public class MainActivity extends AppCompatActivity {
    private ImageView iv_plus;
    private TextView text, tv_scale_fade;
    Button button, button_scale_fade, btn_recolor;
    ViewGroup transitionsContainer;
    boolean visible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
//  -------- auto transition sample --------
        text = (TextView) transitionsContainer.findViewById(R.id.text);
        button = (Button) transitionsContainer.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

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

            @Override
            public void onClick(View view) {
                visible = !visible;
                TransitionSet set = new TransitionSet()
                        .addTransition(new Scale(0.1f))
                        .addTransition(new Fade())
                        .setInterpolator(visible ? new LinearOutSlowInInterpolator() : new FastOutLinearInInterpolator());
                TransitionManager.beginDelayedTransition(transitionsContainer, set);
                tv_scale_fade.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
            }
        });
//  ---------- recolor sample -----------
        btn_recolor = (Button) findViewById(R.id.btn_recolor);
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
        iv_plus = (ImageView) findViewById(R.id.iv_plus);
        iv_plus.setOnClickListener(new View.OnClickListener() {
            boolean isRotated;

            @Override
            public void onClick(View view) {
                isRotated = !isRotated;
                TransitionManager.beginDelayedTransition(transitionsContainer, new Rotate());
                iv_plus.setRotation(isRotated ? 135 : 0);
            }
        });

    }
}
