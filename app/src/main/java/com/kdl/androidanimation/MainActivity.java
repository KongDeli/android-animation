package com.kdl.androidanimation;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayList<String> mStrings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.lv_main);

        mStrings = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            mStrings.add("item" + i);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_item);
        LayoutAnimationController controller = new LayoutAnimationController(animation);
        controller.setDelay(0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_RANDOM);
        mListView.setLayoutAnimation(controller);

        mListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mStrings));
        new Thread(new Runnable() {
            @Override
            public void run() {
                SystemClock.sleep(3000);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                        MainActivity.this.startActivity(intent);
                        MainActivity.this.overridePendingTransition(R.anim.anim_item, R.anim.anim_item2);
                    }
                });
            }
        }).start();
    }
}
