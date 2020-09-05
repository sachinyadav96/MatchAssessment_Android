package com.cv.match.Ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.cv.match.R;


public class SplashActivity extends Activity {


    private TextView Text_AppName;
    private ImageView Img_App_logo;
    private Animation slide_up;
    private Runnable csRunnable1 = new Runnable() {
        @Override
        public void run() {
            Img_App_logo.startAnimation( slide_up );
            Text_AppName.startAnimation( slide_up );
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        this.requestWindowFeature( Window.FEATURE_NO_TITLE );
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN );
        setContentView( R.layout.activity_splash );

        init();
    }

    private void init() {
        Text_AppName = findViewById( R.id.Text_AppName );
        Img_App_logo = findViewById( R.id.Img_App_logo );
        Handler handler1 = new Handler();
        handler1.postDelayed( csRunnable1, 0 );

        slide_up = AnimationUtils.loadAnimation( getApplicationContext(), R.anim.slide_up );
        slide_up.setAnimationListener( new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Img_App_logo.setVisibility( View.VISIBLE );
                Text_AppName.setVisibility( View.VISIBLE );
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                new Handler().postDelayed( () -> {
                    Img_App_logo.setVisibility( View.GONE );
                    Text_AppName.setVisibility( View.GONE );
                    showMainFragment();
                }, 1000 );
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        } );

    }

    private void showMainFragment() {
        startActivity( new Intent( getApplicationContext(), MainActivity.class ) );
        finish();
    }

}