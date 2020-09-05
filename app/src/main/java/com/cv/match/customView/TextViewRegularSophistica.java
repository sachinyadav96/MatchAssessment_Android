package com.cv.match.customView;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;


public class TextViewRegularSophistica extends AppCompatTextView {

    public TextViewRegularSophistica(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewRegularSophistica(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewRegularSophistica(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "Sophistica_large.ttf");
        setTypeface(tf);
    }
}
