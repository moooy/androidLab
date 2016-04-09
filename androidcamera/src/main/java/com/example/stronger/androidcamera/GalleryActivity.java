package com.example.stronger.androidcamera;

import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;

public class GalleryActivity extends AppCompatActivity {

    private static final int GALLERY_OPEN = 1;
    private ImageView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        view = (ImageView) findViewById(R.id.gallery_image);
    }

    public void onOpenGalleryClick(View view) {
        //第一种：Intent的Action_PICK是指从数据中选择一个项目，而后面的Uri指定的是从选择的地址
        Intent intent = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        //第二种：
        Intent secondeIntent = new Intent(Intent.ACTION_GET_CONTENT);
        secondeIntent.setType("image/*");

        // TODO: 2016/4/9 实现对图片的裁剪
        /**
         * intent.putExtra("crop", "true");  //滑动选中图片区域
         intent.putExtra("aspectX", 1);  //裁剪框比例1:1
         intent.putExtra("aspectY", 1);
         intent.putExtra("outputX", 300);  //输出图片大小
         intent.putExtra("outputY", 300);
         intent.putExtra("return-data", true);  //有返回值
         */

        startActivityForResult(secondeIntent, GALLERY_OPEN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        ContentResolver resolver = getContentResolver();
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == GALLERY_OPEN) {
                Uri uri = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(resolver,uri);
                    // TODO: 2016/4/9 为防止原始图片过大导致内存溢出，先缩小原图显示，然后释放原始Bitmap占用的内存
                    view.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
