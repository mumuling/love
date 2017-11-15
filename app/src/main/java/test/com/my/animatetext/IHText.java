package test.com.my.animatetext;
import android.graphics.Canvas;
import android.util.AttributeSet;

import test.com.my.view.HTextView;


public interface IHText {
    void init(HTextView hTextView, AttributeSet attrs, int defStyle);
    void animateText(CharSequence text);
    void onDraw(Canvas canvas);
    void reset(CharSequence text);
}
