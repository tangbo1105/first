package com.bwf.simple;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.BitmapCallback;

import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {
    private ImageView mImg;
    private SimpleDraweeView mFrescoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final String url = "http://www.iyi8.com/uploadfile/2014/0518/20140518112113790.jpg";
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showOkhttpImg(url);
            }
        });
        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFrescoImg(url);
            }
        });
    }

    private void showFrescoImg(String url) {
        mFrescoImg = (SimpleDraweeView) findViewById(R.id.fresoImg);
        mFrescoImg.setImageURI(url);
    }

    private void showOkhttpImg(String url) {
        mImg = (ImageView) findViewById(R.id.img);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5000, TimeUnit.MILLISECONDS)
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .build();
        OkHttpUtils.initClient(okHttpClient);
        OkHttpUtils.get().url(url).build().execute(new BitmapCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(Bitmap response, int id) {
                mImg.setImageBitmap(response);
            }
        });
    }
}
