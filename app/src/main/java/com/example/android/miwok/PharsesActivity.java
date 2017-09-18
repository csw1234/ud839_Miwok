package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


public class PharsesActivity extends AppCompatActivity {

    private AudioManager am;

    private MediaPlayer mediaPlayer;
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

        final ArrayList<Word> pharses = new ArrayList<Word>();

        pharses.add(new Word("Where are you going?","minto wuksus", R.raw.phrase_where_are_you_going));
        pharses.add(new Word("What is your name?","tinnә oyaase'nә",R.raw.phrase_what_is_your_name));
        pharses.add(new Word("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        pharses.add(new Word("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        pharses.add(new Word("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        pharses.add(new Word("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        pharses.add(new Word("Yes, I’m coming.","hәә’ әәnәm",R.raw.phrase_yes_im_coming));
        pharses.add(new Word("I’m coming.","әәnәm",R.raw.phrase_im_coming));
        pharses.add(new Word("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        pharses.add(new Word("Come here.","әnni'nem",R.raw.phrase_come_here));
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
        WordAdapter<Word> itemAdapter = new WordAdapter<Word>(this, pharses,R.color.category_phrases);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Word word = pharses.get(i);
                int requestResult = am.requestAudioFocus(mAudioFocusListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = mediaPlayer.create(PharsesActivity.this, word.getmSongResourceID());
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
