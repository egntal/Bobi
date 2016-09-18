package com.skyapps.firstgame;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends Activity {
    private BoardGame boardGame;
    private Button btnPause;
    private Button btnPlay;
    private Dialog alert;
    private RelativeLayout background;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager am = getApplicationContext().getAssets();
        Typeface typeface = Typeface.createFromAsset(am, String.format(Locale.US, "fonts/%s", "abc.ttf"));

        boardGame = new BoardGame(this);

        SharedPreferences sp = getSharedPreferences("TAL", Context.MODE_APPEND);
        sp.edit().putInt("points", 0).commit();

        final MediaPlayer player = MediaPlayer.create(this,R.raw.music);
        player.setLooping(true);
        player.setVolume(100,100);
        player.start();

        if (sp.getInt("points",0) > sp.getInt("TopPoints",0)){
            sp.edit().putInt("TopPoints", sp.getInt("points",0)).commit();
            Toast.makeText(MainActivity.this, "New Record !!", Toast.LENGTH_SHORT).show();
        }

        final Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        int width = size.x;
        int height = size.y;

        background = (RelativeLayout) findViewById(R.id.background);
        background.addView(boardGame);

        btnPause = new Button(this);
        android.app.ActionBar.LayoutParams layout = new android.app.ActionBar.LayoutParams(200,200);
        btnPause.setLayoutParams(layout);
        background.addView(btnPause);
        btnPause.setBackgroundResource(R.drawable.pause);

        alert = new Dialog(this);
        alert.setContentView(R.layout.pause_layout);
        alert.setCanceledOnTouchOutside(false);

        TextView txt = (TextView) alert.findViewById(R.id.title);
        txt.setTypeface(typeface);

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert.show();
                boardGame.play=false;
                player.pause();
            }
        });

        btnPlay = (Button) alert.findViewById(R.id.btnOk);
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boardGame.play=true;
                alert.dismiss();
                player.start();

            }
        });









    }

}
