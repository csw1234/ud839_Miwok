package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.os.Build.VERSION_CODES.M;

/**
 * Created by alonz on 15/09/2017.
 */

public class WordAdapter<T> extends ArrayAdapter<Word> {
    private int mcolor;

    public WordAdapter(Activity context, ArrayList<Word> wordAdapter, int color){
        super(context,0,wordAdapter);
        mcolor=color;
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

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.imageView);

        if (currentWord.getValidImage()==true) {
            imageView.setImageResource(currentWord.getImageResourceID());
            imageView.setVisibility(View.VISIBLE);
        }else{
                imageView.setVisibility(View.GONE);
        }

        View textBox = listItemView.findViewById(R.id.textBox);
        int color = ContextCompat.getColor(getContext(), mcolor);
        textBox.setBackgroundColor(color);

        return listItemView;


    }
}
