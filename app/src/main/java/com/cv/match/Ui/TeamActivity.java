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
import android.widget.Toast;

import com.cv.match.R;
import com.cv.match.Util.PreferencesServices;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class TeamActivity extends Activity implements View.OnClickListener {
    public ProgressDialog pDialog;
    TabLayout tab_layout;
    private TextView txt11;
    private TextView txt10;
    private TextView txt9;
    private TextView txt8;
    private TextView txt7;
    private TextView txt6;
    private TextView txt5;
    private TextView txt4;
    private TextView txt3;
    private TextView txt2;
    private TextView txt1;
    private TextView txtHeader;
    private Context context = this;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        requestWindowFeature( Window.FEATURE_NO_TITLE );
        PreferencesServices.init( this );
        pDialog = new ProgressDialog( context );
        pDialog.setCancelable( false );
        setContentView( R.layout.activty_team );

        txtHeader = findViewById( R.id.txtHeader );
        tab_layout = findViewById( R.id.tab_layout );
        txt1 = findViewById( R.id.txt1 );
        txt2 = findViewById( R.id.txt2 );
        txt3 = findViewById( R.id.txt3 );
        txt4 = findViewById( R.id.txt4 );
        txt5 = findViewById( R.id.txt5 );
        txt6 = findViewById( R.id.txt6 );
        txt7 = findViewById( R.id.txt7 );
        txt8 = findViewById( R.id.txt8 );
        txt9 = findViewById( R.id.txt9 );
        txt10 = findViewById( R.id.txt10 );
        txt11 = findViewById( R.id.txt11 );


        tab_layout.addOnTabSelectedListener( new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        getTeamData( PreferencesServices.getInstance().GetTeams(), "1" );

                        Toast.makeText( context, "india", Toast.LENGTH_SHORT ).show();
                        break;
                    case 1:
                        getTeamData( PreferencesServices.getInstance().GetTeams(), "2" );

                        Toast.makeText( context, "NZ", Toast.LENGTH_SHORT ).show();
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        } );

        LinearLayout ll_back = findViewById( R.id.ll_back );

        ll_back.setOnClickListener( this );
        getTeamData( PreferencesServices.getInstance().GetTeams(), "1" );

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.ll_back) {
            finish();
        }
    }

    @SuppressLint("SetTextI18n")
    private void getTeamData(String TEAMS, String type) {
        showpDialog();
        try {


            JSONObject object = new JSONObject( TEAMS );
            JSONObject objectTeamA = new JSONObject( object.getString( "4" ) );
            JSONObject objectTeamB = new JSONObject( object.getString( "5" ) );
            txtHeader.setText( objectTeamA.getString( "Name_Short" ) + " VS " + objectTeamB.getString( "Name_Short" ) );
            if (type.equalsIgnoreCase( "1" )) {

                JSONObject objectPlayers = new JSONObject( objectTeamA.getString( "Players" ) );
                JSONObject object1 = new JSONObject( objectPlayers.getString( "3852" ) );
                JSONObject object2 = new JSONObject( objectPlayers.getString( "3722" ) );
                JSONObject object3 = new JSONObject( objectPlayers.getString( "66818" ) );
                JSONObject object4 = new JSONObject( objectPlayers.getString( "4176" ) );
                JSONObject object5 = new JSONObject( objectPlayers.getString( "3632" ) );
                JSONObject object6 = new JSONObject( objectPlayers.getString( "4532" ) );
                JSONObject object7 = new JSONObject( objectPlayers.getString( "63751" ) );
                JSONObject object8 = new JSONObject( objectPlayers.getString( "63187" ) );
                JSONObject object9 = new JSONObject( objectPlayers.getString( "9844" ) );
                JSONObject object10 = new JSONObject( objectPlayers.getString( "5132" ) );
                JSONObject object11 = new JSONObject( objectPlayers.getString( "65867" ) );

                txt1.setText( object1.getString( "Name_Full" )+ "(C)");
                txt2.setText( object2.getString( "Name_Full" ) );
                txt3.setText( object3.getString( "Name_Full" ) );
                txt4.setText( object4.getString( "Name_Full" ) );
                txt5.setText( object5.getString( "Name_Full" )+"(wk)" );
                txt6.setText( object6.getString( "Name_Full" ) );
                txt7.setText( object7.getString( "Name_Full" ) );
                txt8.setText( object8.getString( "Name_Full" ) );
                txt9.setText( object9.getString( "Name_Full" ) );
                txt10.setText( object10.getString( "Name_Full" ) );
                txt11.setText( object11.getString( "Name_Full" ) );
            } else {

                JSONObject objectPlayers = new JSONObject( objectTeamB.getString( "Players" ) );
                JSONObject object1 = new JSONObject( objectPlayers.getString( "4964" ) );
                JSONObject object2 = new JSONObject( objectPlayers.getString( "57594" ) );
                JSONObject object3 = new JSONObject( objectPlayers.getString( "4330" ) );
                JSONObject object4 = new JSONObject( objectPlayers.getString( "3752" ) );
                JSONObject object5 = new JSONObject( objectPlayers.getString( "10167" ) );
                JSONObject object6 = new JSONObject( objectPlayers.getString( "10172" ) );
                JSONObject object7 = new JSONObject( objectPlayers.getString( "57903" ) );
                JSONObject object8 = new JSONObject( objectPlayers.getString( "11703" ) );
                JSONObject object9 = new JSONObject( objectPlayers.getString( "11706" ) );
                JSONObject object10 = new JSONObject( objectPlayers.getString( "60544" ) );
                JSONObject object11 = new JSONObject( objectPlayers.getString( "4338" ) );

                txt1.setText( object1.getString( "Name_Full" ) );
                txt2.setText( object2.getString( "Name_Full" ) );
                boolean cap=object3.getBoolean( "Iscaptain" );
                if (cap){
                    txt3.setText( object3.getString( "Name_Full" )+"(C)" );
                }else {
                    txt3.setText( object3.getString( "Name_Full" ));
                }
                txt4.setText( object4.getString( "Name_Full" ) );
                txt5.setText( object5.getString( "Name_Full" ) +"(wk)");
                txt6.setText( object6.getString( "Name_Full" ) );
                txt7.setText( object7.getString( "Name_Full" ) );
                txt8.setText( object8.getString( "Name_Full" ) );
                txt9.setText( object9.getString( "Name_Full" ) );
                txt10.setText( object10.getString( "Name_Full" ) );
                txt11.setText( object11.getString( "Name_Full" ) );
            }


          /*  JSONObject objectOfficials = new JSONObject( object.getString( "Officials" ) );
            txt_Referee.setText( objectOfficials.getString( "Referee" ) );
            txt_Umpires.setText( objectOfficials.getString( "Umpires" ) );
*/


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
