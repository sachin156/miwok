package com.example.android.miwok;

import android.media.MediaPlayer;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_colors);


        final ArrayList<Word> words = new ArrayList<Word>();
        words.add(new Word("red","weṭeṭṭi",R.drawable.color_red,R.raw.color_red));
                words.add(new Word("mustard yellow", "chiwiiṭә",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
               words.add(new Word("dusty yellow", "ṭopiisә",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
                words.add(new Word("green", "chokokki",R.drawable.color_green,R.raw.color_green));
                words.add(new Word("brown", "ṭakaakki",R.drawable.color_brown,R.raw.color_brown));
                words.add(new Word("gray", "ṭopoppi",R.drawable.color_gray,R.raw.color_gray));
                words.add(new Word("black", "kululli",R.drawable.color_black,R.raw.color_black));
                words.add(new Word("white", "kelelli",R.drawable.color_white,R.raw.color_white));


        WordAdapter itemsAdapter = new WordAdapter(this,words,R.color.category_colors);
        ListView listView = (ListView) findViewById(R.id.activity_colors);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                releaseMediaPlayer();
                Word word=words.get(position);
                MediaPlayer mediaPlayer=MediaPlayer.create(ColorsActivity.this,word.getSong());
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
