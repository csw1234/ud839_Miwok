package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.numbers;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        ArrayList<Word> colors = new ArrayList<Word>();

        colors.add(new Word("red","weṭeṭṭi"));
        colors.add(new Word("green","chokokki"));
        colors.add(new Word("brown","ṭakaakki"));
        colors.add(new Word("gray","ṭopoppi"));
        colors.add(new Word("black","kululli"));
        colors.add(new Word("white","kelelli"));
        colors.add(new Word("dusty yellow","ṭopiisә"));
        colors.add(new Word("mustard chiwiiṭә","kawinta"));
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
        WordAdapter<Word> itemAdapter = new WordAdapter<Word>(this, colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

    }
}
