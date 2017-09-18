package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;
import static com.example.android.miwok.R.id.numbers;

public class FamilyActivity extends AppCompatActivity {

    private AudioManager am;

    private MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        am= (AudioManager) getSystemService(AUDIO_SERVICE);

        final ArrayList<Word> family = new ArrayList<Word>();

        family.add(new Word("father","әpә", R.drawable.family_father,R.raw.family_father));
        family.add(new Word("mother","әṭa", R.drawable.family_mother,R.raw.family_mother));
        family.add(new Word("son","angsi", R.drawable.family_son,R.raw.family_son));
        family.add(new Word("daughter","tune", R.drawable.family_daughter,R.raw.family_daughter));
        family.add(new Word("brother","taachi", R.drawable.family_older_brother,R.raw.family_older_brother));
        family.add(new Word("younger brother ","chalitti", R.drawable.family_younger_brother,R.raw.family_younger_brother));
        family.add(new Word("older sister","teṭe", R.drawable.family_older_sister,R.raw.family_older_sister));
        family.add(new Word("younger sister","kolliti", R.drawable.family_younger_sister,R.raw.family_younger_sister));
        family.add(new Word("grandmother","ama", R.drawable.family_grandmother,R.raw.family_grandmother));
        family.add(new Word("grandfather","paapa", R.drawable.family_grandfather,R.raw.family_grandfather));
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
        WordAdapter<Word> itemAdapter = new WordAdapter<Word>(this, family, R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Word word = family.get(i);
                int requestResult = am.requestAudioFocus(mAudioFocusListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = mediaPlayer.create(FamilyActivity.this, word.getmSongResourceID());
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
