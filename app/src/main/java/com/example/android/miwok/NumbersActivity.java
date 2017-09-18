package com.example.android.miwok;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.R.id.item_touch_helper_previous_elevation;
import static com.example.android.miwok.R.id.numbers;
import static com.example.android.miwok.R.id.select_dialog_listview;

public class NumbersActivity extends AppCompatActivity {

    private AudioManager am;

    MediaPlayer.OnCompletionListener mOnCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };

    final ArrayList<Word> numbers = new ArrayList<Word>();
    WordAdapter<Word> itemAdapter;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);
        am= (AudioManager) getSystemService(AUDIO_SERVICE);

        itemAdapter = new WordAdapter<Word>(this, numbers, R.color.category_numbers);
            // just a test ReportCard
        //ReportCard alon = new ReportCard("alon", 33, 45, 67);
        numbers.add(new Word("one","lutti",R.drawable.number_one,R.raw.number_one));
        numbers.add(new Word("two","otiiko", R.drawable.number_two,R.raw.number_two));
        numbers.add(new Word("three","tolookosu", R.drawable.number_three,R.raw.number_three));
        numbers.add(new Word("four","oyyisa", R.drawable.number_four,R.raw.number_four));
        numbers.add(new Word("five","massokka", R.drawable.number_five,R.raw.number_five));
        numbers.add(new Word("six","temmokka", R.drawable.number_six,R.raw.number_six));
        numbers.add(new Word("seven","kenekaku", R.drawable.number_seven,R.raw.number_seven));
        numbers.add(new Word("eight","kawinta", R.drawable.number_eight,R.raw.number_eight));
        numbers.add(new Word("nine","wo'e", R.drawable.number_nine,R.raw.number_nine));
        numbers.add(new Word("ten","na'aacha", R.drawable.number_ten,R.raw.number_ten));


        //numbers.add(new Word(alon.getName(),alon.getReport()));


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
        final ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(itemAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                releaseMediaPlayer();
                Word word = numbers.get(i);
                int requestResult = am.requestAudioFocus(mAudioFocusListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if (requestResult == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = mediaPlayer.create(NumbersActivity.this, word.getmSongResourceID());
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
   // public void addNum(View view){
    //    numbers.add(new Word("123","lutt444i"));
   //     itemAdapter.notifyDataSetChanged();
   // }

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