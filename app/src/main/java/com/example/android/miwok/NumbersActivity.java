package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.numbers;
import static com.example.android.miwok.R.id.select_dialog_listview;

public class NumbersActivity extends AppCompatActivity {
    ArrayList<Word> numbers = new ArrayList<Word>();
    WordAdapter<Word> itemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        itemAdapter = new WordAdapter<Word>(this, numbers);

        ReportCard alon = new ReportCard("alon", 33, 45, 67);
        numbers.add(new Word("one","lutti"));
        numbers.add(new Word("two","otiiko"));
        numbers.add(new Word("three","tolookosu"));
        numbers.add(new Word("four","oyyisa"));
        numbers.add(new Word("five","massokka"));
        numbers.add(new Word("six","temmokka"));
        numbers.add(new Word("seven","kenekaku"));
        numbers.add(new Word("eight","kawinta"));
        numbers.add(new Word("nine","wo'e"));
        numbers.add(new Word("ten","na'aacha"));
        numbers.add(new Word(alon.getName(),alon.getReport()));
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
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);


    }

    public void addNum(View view){
        numbers.add(new Word("123","lutt444i"));
        itemAdapter.notifyDataSetChanged();
    }

}