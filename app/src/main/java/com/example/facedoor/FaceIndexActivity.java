package com.example.facedoor;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.facedoor.ui.SlideUnlockView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by fyc on 2017/11/2.
 */

public class FaceIndexActivity extends Activity implements DialogInterface.OnClickListener  {
    @Bind(R.id.slideUnlockView)
    SlideUnlockView slideUnlockView;
    @Bind(R.id.afi_click_layout)
    RelativeLayout clickLayout;
    private Vibrator vibrator;
    private EditText editText;
    private static final String PASS_WORD = "123";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_index);
        ButterKnife.bind(this);
        initViewsAndEvents();
    }

    public void initViewsAndEvents() {
        // 获取系统振动器服务
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        // 设置滑动解锁-解锁的监听
        slideUnlockView.setOnUnLockListener(new SlideUnlockView.OnUnLockListener() {
            @Override
            public void setUnLocked(boolean unLock) {
                // 如果是true，证明解锁
                if (unLock) {
                    // 启动震动器 100ms
                    vibrator.vibrate(100);
                    // 当解锁的时候，执行逻辑操作，在这里仅仅是将图片进行展示
                    Intent intent = new Intent(FaceIndexActivity.this, VideoDetect.class);
                    startActivity(intent);

                }
            }
        });
        clickLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaceIndexActivity.this, VideoDetect.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // 重置一下滑动解锁的控件
        slideUnlockView.reset();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            editText = new EditText(FaceIndexActivity.this);
            new AlertDialog.Builder(FaceIndexActivity.this).setTitle("请输入密码").setIcon(R.drawable.ic_launcher)
                    .setView(editText).setPositiveButton("确定", FaceIndexActivity.this).setNegativeButton("取消", null).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (editText.getText().toString().equals(PASS_WORD)) {
            Intent intent=new Intent(FaceIndexActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(FaceIndexActivity.this, "密码错", Toast.LENGTH_SHORT).show();
        }
    }
}