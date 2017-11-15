package test.com.my.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;


import test.com.my.view.LoadingView;
import test.com.my.R;
import test.com.my.render.LoadingRenderer;


public class SceneryActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_scenery);
        LoadingView lv = (LoadingView) findViewById(R.id.lv_view);
        lv.loadingRenderer.setOnListener(new LoadingRenderer.OnListener() {
            @Override
            public void Long() {
                startActivity(new Intent(SceneryActivity.this,TextActivity.class));
                finish();
            }
        });
    }
}
