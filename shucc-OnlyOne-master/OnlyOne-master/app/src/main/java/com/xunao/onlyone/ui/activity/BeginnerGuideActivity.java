package com.xunao.onlyone.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.xunao.onlyone.R;

/**
 * Created by chenchao on 16/6/3.
 * cc@cchao.org
 * 新手指南
 */
public class BeginnerGuideActivity extends AppCompatActivity {

    public static void launch(Context context) {
        context.startActivity(new Intent(context, BeginnerGuideActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginner_guide_activity);

        this.findViewById(R.id.img_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
