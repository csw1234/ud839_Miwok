package com.example.android.miwok;

import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.security.AccessController;

import static java.security.AccessController.getContext;

/**
 * Created by alonz on 14/09/2017.
 * {@link Word}
 */

public class Word extends AppCompatActivity {

    private String mDefaultTranslation;
    private String mMiwokTranslation;
    private int mImageResourceId;
    private int mSongResourceID;
    private boolean validImage=false;


    /**
     *
     *
     * @param defaultTranslation store the Default tranlation of the object (English, Hebrew, etc)
     * @param miwokTranslation store the Miwok translation of the object
     */




    public Word(String defaultTranslation, String miwokTranslation, int songResourceID){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mSongResourceID=songResourceID;
    }

    /**
     *
     * @param defaultTranslation
     * @param miwokTranslation
     * @param imageResourceId
     */

    public Word(String defaultTranslation, String miwokTranslation, int imageResourceId, int songResourceID){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
        mImageResourceId=imageResourceId;
        mSongResourceID=songResourceID;
        validImage=true;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public String getMiwokTranslation()
    {
        return mMiwokTranslation;
    }
    public int getImageResourceID(){
        return mImageResourceId;
    }
    public boolean getValidImage(){
        return validImage;
    }
    public int getmSongResourceID(){return mSongResourceID;}




}
