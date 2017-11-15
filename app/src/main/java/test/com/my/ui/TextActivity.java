package test.com.my.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import test.com.my.manager.PeriscopeLayout;
import test.com.my.R;
import test.com.my.service.Music;
import test.com.my.view.LoginView;
import test.com.my.view.LoveView;


public class TextActivity extends Activity implements View.OnClickListener{
    private boolean flag = true;
    private TextView time,font,right,btnStart,btnStop;
    private LoginView mOwlView;
    private EditText et_password;
    private PeriscopeLayout periscopeLayout;
    private EditText et_name;

    private LoveView mLoveView;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String obj = (String) msg.obj;
                time.setText(obj);
                font.setText("我喜欢你已经");
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FullTransparency();
        setContentView(R.layout.activity_text);
        mOwlView= (LoginView) findViewById(R.id.owl_view);
        et_name= (EditText) findViewById(R.id.et_name);
        et_name.setInputType(InputType.TYPE_NULL);
        periscopeLayout = (PeriscopeLayout) findViewById(R.id.periscope);
        et_password= (EditText) findViewById(R.id.et_password);
        et_password.setInputType(InputType.TYPE_NULL);
        right= (TextView) findViewById(R.id.rightView);
        btnStop= (TextView) findViewById(R.id.btnStop);
        btnStart= (TextView) findViewById(R.id.btnStart);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        et_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mOwlView.open();

                }else{
                    mOwlView.close();
                    Toast.makeText(TextActivity.this, "我喜欢你", Toast.LENGTH_SHORT).show();
                }
            }
        });
        et_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                periscopeLayout.addHeart();
            }
        });
        mLoveView = (LoveView) findViewById(R.id.id_mylove);
        mLoveView.setMaxRate(19);
        mLoveView.setMinRate(16);
        mLoveView.setStepRate(0.09f);

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(TextActivity.this,MainActivity.class));

            }
        });
        mLoveView.setOnClickListener(new LoveView.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoveView loveView = (LoveView) view;
                loveView.setColor(0x99ff0000);
            }
        });
        time = (TextView) findViewById(R.id.time);
        font = (TextView) findViewById(R.id.font);
        new Thread() {
            @Override
            public void run() {
                try {
                    flag = true;
                    while (flag) {
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        Date d1 = df.parse("2017-9-2 00:00:00");
                        Date d2 = new Date(System.currentTimeMillis());
                        System.out.println(df.format(d2));
                        long diff = d2.getTime() - d1.getTime();
                        long days = diff / (1000 * 60 * 60 * 24);
                        long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
                        long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
                        long seconds = (diff / 1000) % 60;
                        String s = "" + days + "天" + hours + "小时" + minutes + "分" + seconds + "秒";
                        Message msg = Message.obtain();
                        msg.obj = s;
                        msg.what = 1;
                        handler.sendMessage(msg);
                        SystemClock.sleep(1000);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        flag = false;
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnStart){
            //播放背景音乐
            Intent intent = new Intent(this, Music.class);
            startService(intent);
        }else if(v.getId() == R.id.btnStop){
            //停止播放音乐
            Intent intent = new Intent(this, Music.class);
            stopService(intent);
        }

    }
    public void FullTransparency() {
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
