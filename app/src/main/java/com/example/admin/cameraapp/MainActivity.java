package com.example.admin.cameraapp;

import android.app.SearchManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.graphics.SurfaceTexture;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private ImageView imageView;
    private Button photoButton;
    private SeekBar seekBar;
    private int progress = 50;
    private TextView seekBarNumberTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = (ImageView) findViewById(R.id.ivPhoto);
        photoButton = (Button) findViewById(R.id.btnCapture);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBarNumberTextView = (TextView) findViewById(R.id.tvSeekBarNumber);


        seekBar.setMax( 100 );
        seekBar.setProgress( progress );
        seekBarNumberTextView.setText( "" + progress );
        //seekBarNumberTextView.setTextSize( progress );

        seekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                progress = i;
                seekBarNumberTextView.setText( "" + progress );
                //seekBarNumberTextView.setTextSize( progress );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void takePhoto(View view) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //called by the camera after a photo is taken.

        //*
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }//*/

        //go to next activity.
    }

    public void implicitIntent(View view) {
        Intent sendIntent = new Intent();

        switch ( view.getId() ) {
            case R.id.btnSendMessage:
                sendIntent.setAction( Intent.ACTION_SEND );
                sendIntent.putExtra( Intent.EXTRA_TEXT, "This is a message." );
                sendIntent.setType( "text/plain" );
                startActivity( sendIntent );
                break;
            case R.id.btnSearchWeb:
                sendIntent.setAction(Intent.ACTION_WEB_SEARCH);
                sendIntent.putExtra(SearchManager.QUERY, "Movies");
                startActivity( sendIntent );
                break;
            case R.id.btnOther:
                //sendIntent.setAction(Intent.ACTION_APP_ERROR);
                sendIntent.setAction(Intent.ACTION_SEARCH);
                sendIntent.putExtra(SearchManager.QUERY, "Jarrett");
                startActivity( sendIntent );
                break;
        }
    }
}