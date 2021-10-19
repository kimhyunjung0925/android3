package com.koreait.com;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

public class ImageViewActivity extends AppCompatActivity {

    private ImageView ivImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        ivImg = findViewById(R.id.ivImg);

        String imgUrl = "https://cdn.mkhealth.co.kr/news/photo/202102/52163_52859_5928.jpg";
        Glide.with(this).load(imgUrl).into(ivImg);
    }
}