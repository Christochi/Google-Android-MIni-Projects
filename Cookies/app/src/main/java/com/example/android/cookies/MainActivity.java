package com.example.android.cookies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Called when the cookie should be eaten.
     */
    public void eatCookie(View view) {
        // TODO: Find a reference to the ImageView in the layout. Change the image.
        displayImage(R.drawable.after_cookie);
        // TODO: Find a reference to the TextView in the layout. Change the text.
        displayMessage("I'm so full");
    }


    private void displayMessage(String message)
    {
        TextView setTextMessage = (TextView) findViewById(R.id.status_text_view);
        setTextMessage.setText(message);
    }

    private void displayImage(int androidCookieImageView)
    {
        ImageView setImageIcon = (ImageView) findViewById(R.id.android_cookie_image_view);
        setImageIcon.setImageResource(androidCookieImageView);
    }
}