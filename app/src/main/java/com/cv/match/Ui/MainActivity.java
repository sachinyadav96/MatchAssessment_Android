package com.cv.match.Ui;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.cardview.widget.CardView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.cv.match.R;
import com.cv.match.Util.Constant;
import com.cv.match.Util.PreferencesServices;
import com.cv.match.customView.AdvanceDrawerLayout;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class MainActivity extends Activity implements View.OnClickListener {
    private NavigationView nav_view_notification;
    private boolean doubleBackToExitPressedOnce;
    private AdvanceDrawerLayout drawer;
    private ProgressDialog pDialog;
    private TextView TeamA;
    private TextView ScoreA;
    private TextView TeamB;
    private TextView ScoreB;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                Toast.makeText( this, "HOME", Toast.LENGTH_SHORT ).show();
                return true;

            case R.id.navigation_commentary:
                Toast.makeText( this, "commentary", Toast.LENGTH_SHORT ).show();
                return true;

            case R.id.navigation_info:
                Toast.makeText( this, "info", Toast.LENGTH_SHORT ).show();
                startActivity( new Intent( getApplicationContext(),
                        InfoDetailsDescriptionActivity.class ) );
                return true;

            case R.id.navigation_scorecard:
                Toast.makeText( this, "scorecard", Toast.LENGTH_SHORT ).show();
                return true;
        }
        return false;
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        PreferencesServices.init( this );
        setContentView( R.layout.activity_main );
        init();


    }

    private void init() {
        BottomNavigationView navigation = findViewById( R.id.navigation );
        navigation.setOnNavigationItemSelectedListener( mOnNavigationItemSelectedListener );
        pDialog = new ProgressDialog( MainActivity.this );
        pDialog.setCancelable( false );

        TeamA = findViewById( R.id.TeamA );
        ScoreB = findViewById( R.id.ScoreB );
        TeamB = findViewById( R.id.TeamB );
        ScoreA = findViewById( R.id.ScoreA );
        TextView matchInfo = findViewById( R.id.MatchInfo );
        TextView players = findViewById( R.id.Players );
        CardView card_view = findViewById( R.id.card_view );

        drawer = findViewById( R.id.drawer_layout );
        ImageView menu = findViewById( R.id.menu );
        LinearLayout ll_Team = findViewById( R.id.ll_Team );
        LinearLayout ll_menu = findViewById( R.id.ll_menu );
        LinearLayout ll_Exit = findViewById( R.id.ll_Exit );
        nav_view_notification = findViewById( R.id.nav_view_notification );
        LinearLayout ll_MatchInfo = findViewById( R.id.ll_MatchInfo );

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle( this, drawer,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close );
        drawer.addDrawerListener( toggle );
        toggle.syncState();
        drawer.setViewScale( Gravity.END, 0.9f );
        drawer.setRadius( Gravity.END, 35 );
        drawer.setViewElevation( Gravity.END, 20 );

        menu.setOnClickListener( this );
        ll_menu.setOnClickListener( this );
        ll_Exit.setOnClickListener( this );
        ll_MatchInfo.setOnClickListener( this );
        ll_Team.setOnClickListener( this );
        card_view.setOnClickListener( this );
        matchInfo.setOnClickListener( this );
        players.setOnClickListener( this );


        GetData();


        GetAPIDetails();
    }

    @SuppressLint("SetTextI18n")
    private void GetData() {
        try {
            JSONObject objectTeamName =
                    new JSONObject( PreferencesServices.getInstance().GetTeams() );
            JSONObject objectTeamA = new JSONObject( objectTeamName.getString( "4" ) );
            JSONObject objectTeamB = new JSONObject( objectTeamName.getString( "5" ) );
            TeamA.setText( objectTeamA.getString( "Name_Full" ) + " (" + objectTeamA.getString(
                    "Name_Short" ) + ")" );
            TeamB.setText( objectTeamB.getString( "Name_Full" ) + " (" + objectTeamB.getString(
                    "Name_Short" ) + ")" );
            ScoreA.setText( "Total Run - 93" );
            ScoreB.setText( "Total Run - 92" );
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ll_menu) {
            drawer.openDrawer( nav_view_notification );
        } else if (v.getId() == R.id.menu) {
            drawer.openDrawer( nav_view_notification );
        } else if (v.getId() == R.id.menu) {
            drawer.openDrawer( nav_view_notification );
        } else if (v.getId() == R.id.ll_Exit) {
            drawer.closeDrawer( nav_view_notification );
            finishAffinity();
        } else if (v.getId() == R.id.ll_MatchInfo) {
            drawer.closeDrawer( nav_view_notification );
            startActivity( new Intent( getApplicationContext(),
                    InfoDetailsDescriptionActivity.class ) );
        } else if (v.getId() == R.id.MatchInfo) {
            startActivity( new Intent( getApplicationContext(),
                    InfoDetailsDescriptionActivity.class ) );
        } else if (v.getId() == R.id.card_view) {
            startActivity( new Intent( getApplicationContext(),
                    TeamActivity.class ) );
        } else if (v.getId() == R.id.ll_Team) {
            drawer.closeDrawer( nav_view_notification );
            startActivity( new Intent( getApplicationContext(),
                    TeamActivity.class ) );

        } else if (v.getId() == R.id.Players) {
            startActivity( new Intent( getApplicationContext(),
                    TeamActivity.class ) );

        }
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce)
            finish();
        Toast.makeText( this, R.string.back_double_press, Toast.LENGTH_SHORT ).show();
        doubleBackToExitPressedOnce = true;
        new Handler().postDelayed( () -> doubleBackToExitPressedOnce = false, 2000 );

    }


    private void GetAPIDetails() {
        String url = Constant.MAIN_URL;
         showpDialog();

        @SuppressLint("SetTextI18n") StringRequest stringRequest =
                new StringRequest( Request.Method.GET, url, response -> {
                    String msg;
                    try {
                        JSONObject object = new JSONObject( response );
                        PreferencesServices.getInstance().SetMatchDetail( object.getString(
                                "Matchdetail" ) );
                        PreferencesServices.getInstance().SetTeams( object.getString( "Teams" ) );
                        PreferencesServices.getInstance().SetNotes( object.getString( "Notes" ) );
                        PreferencesServices.getInstance().SetInnings( object.getString( "Innings" ) );
                        PreferencesServices.getInstance().SetNuggets( object.getString( "Nuggets" ) );

                        JSONObject objectTeamName = new JSONObject( object.getString( "Teams" ) );
                        JSONObject objectTeamA = new JSONObject( objectTeamName.getString( "4" ) );
                        JSONObject objectTeamB = new JSONObject( objectTeamName.getString( "5" ) );
                        TeamA.setText( objectTeamA.getString( "Name_Full" ) + " (" + objectTeamA.getString( "Name_Short" ) + ")" );
                        TeamB.setText( objectTeamB.getString( "Name_Full" ) + " (" + objectTeamB.getString( "Name_Short" ) + ")" );
                        ScoreA.setText( "Total Run - 93" );
                        ScoreB.setText( "Total Run - 92" );

                    } catch (JSONException e1) {
                        e1.printStackTrace();
                        msg = e1.toString();
                        Toast.makeText( MainActivity.this, msg, Toast.LENGTH_LONG ).show();
                    }
                    hidepDialog();

                }, error -> {
                    hidepDialog();
                    String message = null;
                    if (error instanceof NetworkError) {
                        message = "Cannot connect to Internet...Please check your connection!";
                    } else if (error instanceof ServerError) {
                        message = "The server could not be found. Please try again after some " +
                                "time!!";
                    } else if (error instanceof AuthFailureError) {
                        message = "Authentication Failure Error.";
                    } else if (error instanceof ParseError) {
                        message = "Parsing error! Please try again after some time!!";
                    } else if (error instanceof TimeoutError) {
                        message = "Connection TimeOut! Please check your internet connection.";
                    }
                    Toast.makeText( MainActivity.this, message, Toast.LENGTH_SHORT ).show();
                } );
        stringRequest.setRetryPolicy( new DefaultRetryPolicy( 30000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT ) );
        RequestQueue requestQueue = Volley.newRequestQueue( MainActivity.this );
        requestQueue.add( stringRequest );
    }

    private void showpDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
            pDialog.setContentView( R.layout.progress_bar );
            Objects.requireNonNull( pDialog.getWindow() ).setBackgroundDrawableResource( android.R.color.transparent );
        }
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }
}