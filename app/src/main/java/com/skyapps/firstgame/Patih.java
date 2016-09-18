package com.skyapps.firstgame;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import org.w3c.dom.Text;

public class Patih extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patih);

        ImageView imageview = (ImageView) findViewById(R.id.img);
        Ion.with(imageview).placeholder(R.raw.patih).load("android.resource://" + getPackageName() + "/" + R.raw.patih);

        MediaPlayer player = MediaPlayer.create(this,R.raw.music_patih);
        player.setLooping(false);
        player.setVolume(100,100);
        player.start();


        TextView txt = (TextView) findViewById(R.id.txt);
        Typeface custom = Typeface.createFromAsset(getAssets(),"fonts/abc.ttf");
        txt.setTypeface(custom);

        Thread t = new Thread(){
            public void run(){
                try {
                    sleep(4800);
                } catch(Exception e){}
                finally {
                    startActivity(new Intent(Patih.this,MainActivity.class)); finish();
                }
            }
        }; t.start();

    }
}
