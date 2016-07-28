package com.samay.upmarqueeviewdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.samay.upmarqueeview.UpMarqueeViewByScroller;
import com.samay.upmarqueeview.UpMarqueeViewByViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private UpMarqueeViewByScroller upMarqueeViewByScroller;
    private ArrayList<String> data;
    private UpMarqueeViewByViewFlipper upMarqueeViewByViewFlipper;
    private List<View> views=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initViewOne();
    }


    @Override
    protected void onStart() {
        super.onStart();
        upMarqueeViewByScroller.startAnimation();
        upMarqueeViewByViewFlipper.startFlipping();
    }

    @Override
    protected void onStop() {
        super.onStop();
        upMarqueeViewByScroller.stopAnimation();
    }

    private void initView(){
        upMarqueeViewByScroller= (UpMarqueeViewByScroller) findViewById(R.id.upmarqueeview);
        upMarqueeViewByViewFlipper= (UpMarqueeViewByViewFlipper) findViewById(R.id.upmarqueeviewbyflipper);
    }

    private void initData() {
        data = new ArrayList<>();
        data.add("家人给2岁孩子喝这个，孩子智力倒退10岁!!!");
        data.add("iPhone8最感人变化成真，必须买买买买!!!!");
        data.add("简直是白菜价！日本玩家33万甩卖15万张游戏王卡");
        data.add("iPhone7价格曝光了！看完感觉我的腰子有点疼...");
        data.add("主人内疚逃命时没带够，回废墟狂挖30小时！");
    }

    private void setView(){
        for(int i=0;i<data.size();i++){
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_view, null,false);
            TextView contentView= (TextView) moreView.findViewById(R.id.content);
            contentView.setText(data.get(i));
            upMarqueeViewByScroller.addView(moreView);
        }
        for(int i=0;i<data.size();i++){
            LinearLayout moreViewOne = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_view, null);
            TextView contentView= (TextView) moreViewOne.findViewById(R.id.content);
            contentView.setText(data.get(i));
            upMarqueeViewByViewFlipper.addView(moreViewOne);
        }
    }

    private void initViewOne() {
        setView();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
