package com.example.android.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.numbers;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_numbers);

        ArrayList<String> numbers = new ArrayList<String>();

        numbers.add("one"); numbers.add("two"); numbers.add("three");
        numbers.add("four"); numbers.add("five"); numbers.add("six");
        numbers.add("seven"); numbers.add("eight"); numbers.add("nine");
        numbers.add("ten");
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
        ArrayAdapter<String> itemAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, numbers);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

    }
}