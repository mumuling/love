package test.com.my.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import test.com.my.manager.FontManager;
import test.com.my.view.HTextView;
import test.com.my.manager.HTextViewType;
import test.com.my.R;


public class MainActivity extends AppCompatActivity {

    String[] sentences = new String[]{"在时间的洪流随波逐流的", "两个无辜又无知的生命的人", "你们被时间裹挟", "你们被命运驱赶", "然后你们在无知之幕前相聚", "拉起手去面对那个未知的未来",  "所爱隔山海", "山海不可平"};
    private int mCounter = 10;
    private HTextView hTextView;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        FullTransparency();
        setContentView(R.layout.activity_main);
        hTextView = (HTextView) findViewById(R.id.text2);

        radioGroup = (RadioGroup) findViewById(R.id.typeGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.scale:
                        hTextView.setTextColor(Color.BLACK);
                        hTextView.setBackgroundColor(Color.WHITE);
                        hTextView.setTypeface(FontManager.getInstance(getAssets()).getFont("fonts/Lato-Black.ttf"));
                        hTextView.setAnimateType(HTextViewType.SCALE);
                        break;
                    case R.id.evaporate:
                        hTextView.setTextColor(Color.BLACK);
                        hTextView.setBackgroundColor(Color.WHITE);
                        hTextView.setTypeface(FontManager.getInstance(getAssets()).getFont("fonts/PoiretOne-Regular.ttf"));
                        hTextView.setAnimateType(HTextViewType.EVAPORATE);
                        break;
                    case R.id.fall:
                        hTextView.setTextColor(Color.BLACK);
                        hTextView.setBackgroundColor(Color.WHITE);
                        hTextView.setTypeface(FontManager.getInstance(getAssets()).getFont("fonts/Mirza-Regular.ttf"));
                        hTextView.setAnimateType(HTextViewType.FALL);
                        break;
                    case R.id.sparkle:
                        hTextView.setTextColor(Color.WHITE);
                        hTextView.setBackgroundColor(Color.BLACK);
                        hTextView.setTypeface(null);
                        hTextView.setAnimateType(HTextViewType.SPARKLE);
                        break;
                    case R.id.anvil:
                        hTextView.setTextColor(Color.WHITE);
                        hTextView.setBackgroundColor(Color.BLACK);
                        hTextView.setTypeface(null);
                        hTextView.setAnimateType(HTextViewType.ANVIL);
                        break;
                    case R.id.line:
                        hTextView.setTextColor(Color.WHITE);
                        hTextView.setBackgroundColor(Color.BLACK);
                        hTextView.setTypeface(null);
                        hTextView.setAnimateType(HTextViewType.LINE);
                        break;
                    case R.id.typer:
                        hTextView.setTextColor(Color.WHITE);
                        hTextView.setBackgroundColor(Color.BLACK);
                        hTextView.setTypeface(null);
                        hTextView.setAnimateType(HTextViewType.TYPER);
                        break;
                    case R.id.rainbow:
                        hTextView.setTextColor(Color.WHITE);
                        hTextView.setBackgroundColor(Color.BLACK);
                        hTextView.setTypeface(null);
                        hTextView.setAnimateType(HTextViewType.RAINBOW);
                        break;
                }

                onClick(radioGroup.findViewById(checkedId));
            }
        });
    }

    public void onClick(View v) {
        mCounter = mCounter >= sentences.length - 1 ? 0 : mCounter + 1;
        hTextView.animateText(sentences[mCounter]);
    }
    /**
     * 5.0以上的手机设置全透明状态栏
     */
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
