package com.cv.match.Util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;


public class PreferencesServices {
    public static final String PREF_Match_Detail = "Match_detail";
    private static final String PREF_Notes = "Notes";
    private static final String PREF_Teams = "Teams";
    private static final String PREF_Innings = "Innings";
    private static final String PREF_Nuggets = "Nuggets";

    private static final String PREFS_NAME = "sports_interactive";
    @SuppressLint("StaticFieldLeak")
    private static PreferencesServices mSingleton = new PreferencesServices();
    @SuppressLint("StaticFieldLeak")
    private static Context mContext;

    private PreferencesServices() {
    }

    public static PreferencesServices instance() {
        return mSingleton;
    }

    public static PreferencesServices getInstance() {
        return mSingleton;
    }

    public static void init(Context context) {
        mContext = context;
    }

    private SharedPreferences getPrefs() {
        return mContext.getSharedPreferences( PREFS_NAME, Context.MODE_PRIVATE );
    }


    public String GetNotes() {
        return getPrefs().getString( PREF_Notes, "" );
    }

    public void SetNotes(String note) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString( PREF_Notes, note );
        editor.apply();
    }

    public String GetMatchDetail() {
        return getPrefs().getString( PREF_Match_Detail, "" );
    }

    public void SetMatchDetail(String m_details) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString( PREF_Match_Detail, m_details );
        editor.apply();
    }

    public String GetTeams() {
        return getPrefs().getString( PREF_Teams, "" );
    }

    public void SetTeams(String team) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString( PREF_Teams, team );
        editor.apply();
    }

    public String GetInnings() {
        return getPrefs().getString( PREF_Innings, "" );
    }

    public void SetInnings(String innings) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString( PREF_Innings, innings );
        editor.apply();
    }

    public String GetNuggets() {
        return getPrefs().getString( PREF_Nuggets, "" );
    }

    public void SetNuggets(String nug) {
        SharedPreferences.Editor editor = getPrefs().edit();
        editor.putString( PREF_Nuggets, nug );
        editor.apply();
    }

}


