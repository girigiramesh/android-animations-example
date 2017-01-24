package com.animations_example;

import android.os.Bundle;
import android.support.v4.view.animation.FastOutLinearInInterpolator;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.transitionseverywhere.Fade;
import com.transitionseverywhere.TransitionManager;
import com.transitionseverywhere.TransitionSet;
import com.transitionseverywhere.extra.Scale;

public class MainActivity extends AppCompatActivity {
    private TextView text, tv_scale_fade;
    Button button, button_scale_fade;
    ViewGroup transitionsContainer;
    boolean visible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        transitionsContainer = (ViewGroup) findViewById(R.id.transitions_container);
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

        tv_scale_fade = (TextView) transitionsContainer.findViewById(R.id.tv_scale_fade);
        button_scale_fade = (Button) transitionsContainer.findViewById(R.id.button_scale_fade);
        button_scale_fade.setOnClickListener(new View.OnClickListener() {

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
    }
}
