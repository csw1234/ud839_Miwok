package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by alonz on 15/09/2017.
 */

public class WordAdapter<T> extends ArrayAdapter<Word> {
    public WordAdapter(Activity context, ArrayList<Word> wordAdapter){
        super(context,0,wordAdapter);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent,false);
        }
        Word currentWord = getItem(position);

        TextView nameTextView = (TextView) listItemView.findViewById(R.id.textView);
        nameTextView.setText(currentWord.getMiwokTranslation());

        TextView nameTextView2 = (TextView) listItemView.findViewById(R.id.textView2);
        nameTextView2.setText(currentWord.getDefaultTranslation());

        return listItemView;


    }
}
