package com.example.android.miwok;

/**
 * Created by alonz on 14/09/2017.
 * {@link Word}
 */

public class Word {

    private String mDefaultTranslation;
    private String mMiwokTranslation;



    public Word(String defaultTranslation, String miwokTranslation){
        mDefaultTranslation=defaultTranslation;
        mMiwokTranslation=miwokTranslation;
    }

    public String getDefaultTranslation(){
        return mDefaultTranslation;
    }
    public String getMiwokTranslation(){
        return mMiwokTranslation;
    }


}
