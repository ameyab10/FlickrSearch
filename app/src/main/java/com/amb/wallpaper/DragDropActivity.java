package com.amb.wallpaper;

import android.content.ClipData;
import android.content.ClipDescription;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amb.wallpaper.databinding.ActivityDragDropBinding;

import static com.amb.wallpaper.R.*;

public class DragDropActivity extends AppCompatActivity {

    private ActivityDragDropBinding mDragDropBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDragDropBinding = DataBindingUtil.setContentView(this, layout.activity_drag_drop);
        registerDragListeners();
    }

    private void registerDragListeners() {

        mDragDropBinding.imageview1.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                return true;
            }
        });

        mDragDropBinding.container2.setOnDragListener(new View.OnDragListener() {
            @Override
            public boolean onDrag(View v, DragEvent event) {
                if (v == mDragDropBinding.container2) {
                    if (event.getAction() == DragEvent.ACTION_DROP) {
                        ImageView iv = new ImageView(DragDropActivity.this);
                        iv.setLayoutParams(new LinearLayout.LayoutParams(200, 200));
                        iv.setImageDrawable(getResources().getDrawable(android.R.drawable.btn_star_big_on));
                        if (v instanceof ViewGroup) {
                            ((ViewGroup) v).addView(iv);
                        }
                    }
                    return true;
                }
                return false;
            }
        });



        mDragDropBinding.imageview1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ClipData.Item item = new ClipData.Item("test");
                ClipData dragData = new ClipData("test", new String[] {"text"} , item);
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(v);
                v.startDragAndDrop(dragData, shadowBuilder, null, 0);
                return false;
            }
        });
    }

}
