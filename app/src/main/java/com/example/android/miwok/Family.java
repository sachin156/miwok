package com.example.android.miwok;

import android.app.Activity;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class Family extends AppCompatActivity {
    private MediaPlayer mediaPlayer;
    private MediaPlayer.OnCompletionListener mcompleteListener=new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_family);
       final ArrayList<Word> words = new ArrayList<Word>();
             words.add(new Word("father", "әpә",R.drawable.family_father,R.raw.family_father));
                words.add(new Word("mother", "әṭa",R.drawable.family_mother,R.raw.family_mother));
                words.add(new Word("son", "angsi",R.drawable.family_son,R.raw.family_son));
                words.add(new Word("daughter", "tune",R.drawable.family_daughter,R.raw.family_daughter));
                words.add(new Word("older brother", "taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
                words.add(new Word("younger brother", "chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
                words.add(new Word("older sister", "teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
                words.add(new Word("younger sister", "kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
                words.add(new Word("grandmother ", "ama",R.drawable.family_grandmother,R.raw.family_grandmother));
                words.add(new Word("grandfather", "paapa",R.drawable.family_grandfather,R.raw.family_grandfather));


        int index = 0;
        WordAdapter itemsAdapter = new WordAdapter(this,words,R.color.category_family);
        ListView listView = (ListView) findViewById(R.id.activity_family);

        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                releaseMediaPlayer();
                Word word = words.get(position);
                MediaPlayer mediaPlayer = MediaPlayer.create(Family.this, word.getSong());
                mediaPlayer.start();
                mediaPlayer.setOnCompletionListener(mcompleteListener);

            }
        });
    }
    @Override
    protected void onStop() {
        super.onStop();
        // When the activity is stopped, release the media player resources because we won't
        // be playing any more sounds.
        releaseMediaPlayer();
    }
    private void releaseMediaPlayer()
    {
        if(mediaPlayer!=null)
        {
            mediaPlayer.release();
            mediaPlayer=null;
        }
    }
}
