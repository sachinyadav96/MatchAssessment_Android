package com.cv.match.customView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


public class TextViewBold extends AppCompatTextView {

    public TextViewBold(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewBold(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewBold(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "roboto-bold.ttf");
        setTypeface(tf);
    }
}
