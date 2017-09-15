package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.family;

public class PharsesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> pharses = new ArrayList<Word>();

        pharses.add(new Word("Where are you going?","minto wuksus"));
        pharses.add(new Word("What is your name?","tinnә oyaase'nә"));
        pharses.add(new Word("My name is...","oyaaset..."));
        pharses.add(new Word("How are you feeling?","michәksәs?"));
        pharses.add(new Word("I’m feeling good.","kuchi achit"));
        pharses.add(new Word("Are you coming?","әәnәs'aa?"));
        pharses.add(new Word("Yes, I’m coming.","hәә’ әәnәm"));
        pharses.add(new Word("I’m coming.","әәnәm"));
        pharses.add(new Word("Let’s go.","yoowutis"));
        pharses.add(new Word("Come here.","әnni'nem"));
        // Find the root view of the whole layout
        //LinearLayout rootView = (LinearLayout)findViewById(R.id.rootNumbers);
        //Example of a While loop
        /**
         //while (index<numbers.size()){
         //    TextView wordView = new TextView(this);
         //    wordView.setText(numbers.get(index));
         //    rootView.addView(wordView);
         //    index++;
         **/
        //  Example of For loop
        //for (int index = 0; index<numbers.size();index++){
        //    //Create TextView
        //    TextView wordView = new TextView(this);
        //Set the text from the array into the TextView object
        //    wordView.setText(numbers.get(index));
        //and add the View as a chile to the rootView
        //    rootView.addView(wordView);
        WordAdapter<Word> itemAdapter = new WordAdapter<Word>(this, pharses);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

    }


}
