package com.cv.match.Ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cv.match.R;
import com.cv.match.Util.PreferencesServices;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class InfoDetailsDescriptionActivity extends Activity implements View.OnClickListener {
    public ProgressDialog pDialog;
    private TextView Txt_TeamAway;
    private TextView Txt_League;
    private TextView txt_Match;
    private TextView txt_Tour_Name;
    private TextView txt_Venue;
    private TextView txtDateTime;
    private TextView txt_TeamHome;
    private TextView txt_Series;
    private TextView txt_Toss;
    private TextView txt_Referee;
    private TextView txt_Umpires;
    private TextView txt_mom;
    private TextView txt_Status;
    private TextView txt_Result;
    private TextView txt_Winning;
    private TextView txt_Margin;
    private TextView txt_Equation;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        PreferencesServices.init( this );
        pDialog = new ProgressDialog( context );
        pDialog.setCancelable( false );
        setContentView( R.layout.activty_match_details );
        txt_TeamHome = findViewById( R.id.Txt_TeamHome );
        Txt_TeamAway = findViewById( R.id.Txt_TeamAway );
        Txt_League = findViewById( R.id.Txt_League );
        txt_Match = findViewById( R.id.txt_Match );
        txt_Tour_Name = findViewById( R.id.txt_Tour_Name );
        txt_Venue = findViewById( R.id.txt_Venue );
        txtDateTime = findViewById( R.id.txtDateTime );
        LinearLayout ll_back = findViewById( R.id.ll_back );
        txt_Series = findViewById( R.id.txt_Series );
        txt_Toss = findViewById( R.id.txt_Toss );
        txt_Referee = findViewById( R.id.txt_Referee );
        txt_Umpires = findViewById( R.id.txt_Umpires );
        txt_mom = findViewById( R.id.txt_mom );
        txt_Status = findViewById( R.id.txt_Status );
        txt_Result = findViewById( R.id.txt_Result );
        txt_Winning = findViewById( R.id.txt_Winning );
        txt_Margin = findViewById( R.id.txt_Margin );
        txt_Equation = findViewById( R.id.txt_Equation );

        ll_back.setOnClickListener( this );
        getInfoData( PreferencesServices.getInstance().GetMatchDetail() );

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_back) {
            finish();
        }
    }

    @SuppressLint("SetTextI18n")
    private void getInfoData(String MatchDetails) {
        showpDialog();
        try {
            JSONObject object = new JSONObject( MatchDetails );
            txt_TeamHome.setText( object.getString( "Team_Home" ) );
            Txt_TeamAway.setText( object.getString( "Team_Away" ) );

            JSONObject objectMatch = new JSONObject( object.getString( "Match" ) );
            Txt_League.setText( objectMatch.getString( "League" ) );
            txtDateTime.setText( objectMatch.getString( "Date" ) + " - " + objectMatch.getString(
                    "Time" ) );

            JSONObject objectSeries = new JSONObject( object.getString( "Series" ) );
            txt_Match.setText( objectSeries.getString( "Name" ) );
            txt_Tour_Name.setText( objectSeries.getString( "Tour_Name" ) );
            txt_Series.setText( objectSeries.getString( "Status" ) );

            JSONObject objectVenue = new JSONObject( object.getString( "Venue" ) );
            txt_Venue.setText( objectVenue.getString( "Name" ) );

            JSONObject objectOfficials = new JSONObject( object.getString( "Officials" ) );
            txt_Referee.setText( objectOfficials.getString( "Referee" ) );
            txt_Umpires.setText( objectOfficials.getString( "Umpires" ) );


            txt_Toss.setText( object.getString( "Tosswonby" ) );
            txt_mom.setText( object.getString( "Player_Match" ) );
            txt_Status.setText( object.getString( "Status" ) );
            txt_Result.setText( object.getString( "Result" ) );
            txt_Winning.setText( object.getString( "Winningteam" ) );
            txt_Margin.setText( object.getString( "Winmargin" ) );
            txt_Equation.setText( object.getString( "Equation" ) );


        } catch (JSONException e1) {
            e1.printStackTrace();

        }

        hidepDialog();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity( new Intent( getApplicationContext(),
                MainActivity.class ) );

    }
}
