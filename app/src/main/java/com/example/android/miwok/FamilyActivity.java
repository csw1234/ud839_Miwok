package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.numbers;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Word> family = new ArrayList<Word>();

        family.add(new Word("father","әpә"));
        family.add(new Word("mother","әṭa"));
        family.add(new Word("son","angsi"));
        family.add(new Word("daughter","tune"));
        family.add(new Word("brother","taachi"));
        family.add(new Word("younger brother ","chalitti"));
        family.add(new Word("older sister","teṭe"));
        family.add(new Word("younger sister","kolliti"));
        family.add(new Word("grandmother","ama"));
        family.add(new Word("grandfather","paapa"));
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
        WordAdapter<Word> itemAdapter = new WordAdapter<Word>(this, family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

    }

}
