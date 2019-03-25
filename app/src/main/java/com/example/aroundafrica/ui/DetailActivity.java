package com.example.aroundafrica.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.aroundafrica.R;
import com.example.aroundafrica.data.Photo;

public class DetailActivity extends AppCompatActivity {

    public static final String PHOTO_EXTRA = "extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView imageView = findViewById(R.id.imageView2);
        TextView textView = findViewById(R.id.textView2);

        //Get the intent that started this activity
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(PHOTO_EXTRA)) {
            Photo mPhoto = intent.getParcelableExtra(PHOTO_EXTRA);
            //Populate UI
            Glide.with(this).load(mPhoto.getUrl()).placeholder(R.mipmap.ic_launcher).into(imageView);
            textView.setText(mPhoto.getTitle());
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
    }
}
