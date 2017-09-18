package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.list;
import static com.example.android.miwok.R.id.media_actions;
import static com.example.android.miwok.R.id.numbers;

public class ColorsActivity extends AppCompatActivity {

    private AudioManager am;

    MediaPlayer mediaPlayer;
    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        am= (AudioManager) getSystemService(AUDIO_SERVICE);

        final ArrayList<Word> colors = new ArrayList<Word>();

        colors.add(new Word("red","weṭeṭṭi", R.drawable.color_red,R.raw.color_red));
        colors.add(new Word("green","chokokki", R.drawable.color_green,R.raw.color_green));
        colors.add(new Word("brown","ṭakaakki", R.drawable.color_brown,R.raw.color_brown));
        colors.add(new Word("gray","ṭopoppi", R.drawable.color_gray,R.raw.color_gray));
        colors.add(new Word("black","kululli", R.drawable.color_black,R.raw.color_black));
        colors.add(new Word("white","kelelli", R.drawable.color_white,R.raw.color_white));
        colors.add(new Word("dusty yellow","ṭopiisә", R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        colors.add(new Word("mustard chiwiiṭә","kawinta", R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
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
        WordAdapter<Word> itemAdapter = new WordAdapter<Word>(this, colors, R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Word word = colors.get(i);
                int requestResult = am.requestAudioFocus(mAudioFocusListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = mediaPlayer.create(ColorsActivity.this, word.getmSongResourceID());
                    mediaPlayer.start();

                    mediaPlayer.setOnCompletionListener(mOnCompletionListener);
                }

            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private void releaseMediaPlayer(){
        //If the media player is not null, then it may be currently playing a sound
        if (mediaPlayer != null){
            //Regardless of the current state of the media player, release its resources
            //because we no longer need it.
            mediaPlayer.release();
            am.abandonAudioFocus(mAudioFocusListener);

            //Set the media player back to null. for our code, we've decided that
            //setting the media player to null is an easy way to tell that the media player
            //is not configured to play an audio fle at the moment
            mediaPlayer = null;
        }
    }
    private AudioManager.OnAudioFocusChangeListener mAudioFocusListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int i) {
            switch (i){
                case AudioManager.AUDIOFOCUS_GAIN:
                    mediaPlayer.start();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS:
                    mediaPlayer.stop();
                    releaseMediaPlayer();
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    break;
                case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                    mediaPlayer.pause();
                    mediaPlayer.seekTo(0);
                    break;
            }
        }
    };
}
